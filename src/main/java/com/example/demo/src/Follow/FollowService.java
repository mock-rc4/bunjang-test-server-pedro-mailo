package com.example.demo.src.Follow;


import com.example.demo.config.BaseException;

import com.example.demo.src.Follow.model.*;
import com.example.demo.src.favortie.model.PostFavoriteInfoRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class FollowService {


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FollowDao followDao;
    private final FollowProvider followProvider;
    private final JwtService jwtService;

    @Autowired
    public FollowService(FollowDao followDao, FollowProvider followProvider, JwtService jwtService) {
        this.followDao = followDao;
        this.followProvider = followProvider;
        this.jwtService = jwtService;

    }


    /**
     * 팔로우 등록, 삭제 API
     * */
    public PostFollowInfoRes postfollowInfo(PostFollowInfoReq postFollowInfoReq) throws BaseException{
        try{

            // 기존에 팔로우한 기록이 있는지 Follow 테이블에 여부 조회
            if (followProvider.checkfollowInfo(postFollowInfoReq.getUserIdx(),postFollowInfoReq.getFollowingIdx()) == 1) {
                PostFavoriteInfoRes getfollowinfo = followDao.getfollowinfo(postFollowInfoReq);

                // 상태값이 1 인경우, 삭제하는 경우니까, status 를 2 로 변경
                if(getfollowinfo.getStatus() ==1){
                    followDao.deletefollowInfo(postFollowInfoReq);
                }
                // 상태값이 2 인경우, 등록하는 경우니까, status 를 1 로 변경
                if(getfollowinfo.getStatus() ==2){
                    followDao.statusChangefollowInfo(postFollowInfoReq);
                }
            }
            // Follow 테이블에 없는 경우, 추가
            else {
                followDao.createFollowInfo(postFollowInfoReq);
            }
            PostFavoriteInfoRes getfollowinfo = followDao.getfollowinfo(postFollowInfoReq);
            return new PostFollowInfoRes(getfollowinfo.getProductIdx(),getfollowinfo.getUserIdx(),getfollowinfo.getStatus());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }
}
