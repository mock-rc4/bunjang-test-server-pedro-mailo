package com.example.demo.src.follow;


import com.example.demo.config.BaseException;

import com.example.demo.src.follow.model.PostFollowInfoReq;
import com.example.demo.src.follow.model.PostFollowInfoRes;
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

    public PostFollowInfoRes postfollowInfo(PostFollowInfoReq postFollowInfoReq) throws BaseException{
        try{
            if (followProvider.checkfollowInfo(postFollowInfoReq.getUserIdx(),postFollowInfoReq.getFollowingIdx()) == 1) {
                PostFavoriteInfoRes getfollowinfo = followDao.getfollowinfo(postFollowInfoReq);

                if(getfollowinfo.getStatus() ==1){
                    followDao.deletefollowInfo(postFollowInfoReq);
                    //return new PostFavoriteInfoRes(getfav.getProductIdx(),getfav.getUserIdx(),getfav.getStatus());
                }
                if(getfollowinfo.getStatus() ==2){
                    followDao.statusChangefollowInfo(postFollowInfoReq);
                    //return new PostFavoriteInfoRes(getfav.getProductIdx(),getfav.getUserIdx(),getfav.getStatus());
                }
            }
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
