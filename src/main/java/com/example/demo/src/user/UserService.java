package com.example.demo.src.user;



import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;
    }

    //POST
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //해당 코드는 질의 한다.
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

    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        try{
            int result = userDao.modifyUserName(patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }



///


}
