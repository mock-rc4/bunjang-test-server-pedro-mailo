package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }



    //3/22 핸드폰 번호 조회 확인
    public int checkPhone(String phoneNumber) throws BaseException{
        try{
            return userDao.checkPhone(phoneNumber);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkShopName(String shopName)throws BaseException {
        try{

            return userDao.checkShopName(shopName);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }


    /**
     * 로그인 API 처리 클래스
     * */
    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
        User user = userDao.getUserInfo(postLoginReq);
        String encryptPwd;
        try {
            encryptPwd=new SHA256().encrypt(postLoginReq.getUserPwd());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        //유저 정보 중 입력한 이름과 실제 등록된 이름이 일치하지 않는 경우 발생
        if(!postLoginReq.getUserName().equals(user.getUserName())){
            throw new BaseException(POST_USERS_INVALID_USERNAME);
        }

        //유저 정보 중 입력한 생년월일과 실제 등록된 생년월일이 일치하지 않는 경우 발생
        if(!postLoginReq.getUserBirth().equals(user.getUserBirth())){
            throw new BaseException(POST_USERS_INVALID_USERBIRTH);
        }

        if(user.getUserPwd().equals(encryptPwd)){
            int userIdx = user.getIdx();
            String jwt = jwtService.createJwt(userIdx);
            System.out.println("jwt:" + jwt);
            return new PostLoginRes(userIdx,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }


    /**
     * 유저 메인페이지 조회하는 클래스, 각각 유저 개인정보, 진행사항별 상품수,상품상세 를 List 로 받아서 리턴
     */
    public List<String> getUserMainPage(int userIdx,int progress) throws BaseException{
        try{
            List UserInfo = userDao.UserInfo(userIdx);
            List productCount = userDao.ProductCount(userIdx,progress);
            List producList = userDao.ProductDetail(userIdx,progress);
            List resultDetailList = new ArrayList<>(Arrays.asList(UserInfo,productCount,producList));
            return resultDetailList;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

    /**
     * 유저 상점명 검색
     * */
    public List<GetSearchByUserNameRes> SearchByUserName(String shopName) throws BaseException{
        try{
            List<GetSearchByUserNameRes> getSearchByUserNameResList = userDao.SearchByUserName(shopName);
            return getSearchByUserNameResList;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 유저 상점설정정보 조회
     * */
    public List<GerUserSettingRes> GetsettingInfo(int userIdx) throws BaseException{

        try{
            List<GerUserSettingRes> getsettingInfoRes = userDao.GerUserSetting(userIdx);
            return getsettingInfoRes;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }


    }


}
