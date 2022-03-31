package com.example.demo.src.Follow;


import com.example.demo.config.BaseException;
import com.example.demo.src.Follow.model.*;

import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;



@Service
public class FollowProvider {

    private final FollowDao followDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FollowProvider(FollowDao followDao, JwtService jwtService) {
        this.followDao = followDao;
        this.jwtService = jwtService;
    }



    public int checkfollowInfo(int userIdx, int followingIdx) throws BaseException{
        try{
            return followDao.checkfollowInfo(userIdx,followingIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }


    /**
     * 팔로우 한 유저 정보 조회
     * */
    public List<GetfollowRes> getuserFollowList(int userIdx) throws BaseException{
        //유저가 팔로우한 인수를 세기 위한 List Length
    List length =  followDao.getfollowIdxInfo(userIdx);
    int k = length.size(); //
        List resultDetailListresult = new ArrayList<>(Arrays.asList()); // 유저정보 , 상품 정보 담는 List 선언 하여 리턴

        for (int i =0 ; i<k;i++){
            int q = followDao.getfollowoneIdx(i); // 유저가 팔로우한 유저인덱스 구해서 q에 주입

            // 각각 팔로우한 유저정보와, 등록한 상품정보를 List resultDetailList 에 넣는다
            List FollowList = followDao.FollowList(userIdx,q);
            List FollowListDesc = followDao.FollowListDesc(userIdx,q);
            List resultDetailList = new ArrayList<>(Arrays.asList(FollowList,FollowListDesc));
            resultDetailListresult.add(resultDetailList);
    }
    return resultDetailListresult;
    }



    /**
     * 유저를 팔로잉한 유저 정보 조회
     * */
    public List<GetFollowerRes> getuserFollower(int userIdx) throws BaseException{
        try{
            List<GetFollowerRes> GetFollowerRes = followDao.getuserFollower(userIdx);
            return GetFollowerRes;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
