package com.example.demo.src.user;



import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;
    }

    /**
     * 회원가입  API
    * */
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //해당 코드는 질의 한다. 그리고 닉네임이 동일한것은 존재할수 없기 떄문에 동일한 닉네임 입력시 나오는
        if(userProvider.checkPhone(postUserReq.getPhoneNumber())==1){
            //userProvider.logIn(postLoginReq);
            throw new BaseException(POST_USERS_EXISTS_PHONE);
        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getUserPwd());
            System.out.println("pwd : "+pwd); // 정상적으로 암호화 처리 되었는지 확인
            postUserReq.setUserPwd(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int Idx = userDao.createUser(postUserReq);
            //jwt 발급.
            String jwt = jwtService.createJwt(Idx);
            System.out.println("jwt : "+jwt);
            return new PostUserRes(jwt,Idx,postUserReq.getShopName(), postUserReq.getPhoneNumber(), postUserReq.getUserName(), postUserReq.getUserBirth(), postUserReq.getUserPwd());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 상점명 변경 API
     * */
    public void modifyshopName(PatchShopNameReq patchShopNameReq) throws BaseException {
            if(userProvider.checkShopName(patchShopNameReq.getShopName())==1){
                throw new BaseException(POST_USERS_EXISTS_ShopName);
            }
            int result = userDao.modifyshopName(patchShopNameReq);

            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
    }

    /**
     * 회원 성별 변경 API (1. 남자 , 2. 여자 )
     * */
    public void modifyUserSex(PatchUserSexReq patchUserSexReq) throws BaseException {
        try{
            int result = userDao.modifyUserSex(patchUserSexReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 생년월일 변경 API
     * */
    public void modifyUserBrith(PatchUserBirthReq patchUserBirthReq) throws BaseException {
        try{
            int result = userDao.modifyUserBirth(patchUserBirthReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 유저 핸드폰 번호 변경 API
     * 번개장터는 핸드폰번호 기준으로 회원 조회하기 때문에,
     * 변경할려는 핸드폰 번호가 db 에 존재하는지 확인후 변경 , 존재하는경우  BaseException 처리
     * */
    public void modifyUserPhone(PatchUserPhoneReq patchUserPhoneReq) throws BaseException {
        if (userProvider.checkPhone(patchUserPhoneReq.getPhoneNumber()) == 1) {
            throw new BaseException(POST_USERS_EXISTS_PHONE);
        }
        int result = userDao.modifyUserPhone(patchUserPhoneReq);
        if (result == 0) {
            throw new BaseException(MODIFY_FAIL_USERNAME);

        }
    }

    /**
     * 탈퇴 사유 입력 받아서 회원 상태를 변경 (Status = 2 )
     * */
    public void deleteUserInfo(DeleteUserReq deleteUserReq) throws BaseException {
//        try{
        int result = userDao.deleteUserInfo(deleteUserReq);
        if (result == 0){
            throw new BaseException(DELETE_FAIL_USER);
        }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
    }

    /**
     * 회원 상점정보 변경 API
     * 기존 변경할려는 상점명이 이미 존재하는지 유무 확인해서 변경한다, 존재하는경우  BaseException 처리
     * */
    public void patchUserSetting(PatchUserSettingReq patchUserSettingReq, int userIdx) throws BaseException{
        if(userProvider.checkShopName(patchUserSettingReq.getShopName())==1){
            throw new BaseException(POST_USERS_EXISTS_ShopName);
        }
        int j = userDao.patchUserSetting(patchUserSettingReq,userIdx);
    }
///


}
