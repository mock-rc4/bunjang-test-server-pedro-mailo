package com.example.demo.src.follow;


import com.example.demo.config.BaseException;
import com.example.demo.src.follow.model.*;

import com.example.demo.src.follow.model.GetFollowerRes;
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

    public List<GetfollowRes> getuserFollowList(int userIdx) throws BaseException{
    List length =  followDao.getfollowIdxInfo(userIdx);
    int k = length.size();
        List resultDetailListresult = new ArrayList<>(Arrays.asList());
    for (int i =0 ; i<k;i++){
        int q = followDao.getfollowoneIdx(i);
        List FollowList = followDao.FollowList(userIdx,q);
        List FollowListDesc = followDao.FollowListDesc(userIdx,q);
        List resultDetailList = new ArrayList<>(Arrays.asList(FollowList,FollowListDesc));
        resultDetailListresult.add(resultDetailList);
    }
    return resultDetailListresult;
//        List<FollointIdxRes> folidx = followDao.getfollowIdxInfo(userIdx);
//
//        System.out.println(folidx);
//        int isi = folidx.size();
//        List FollowList = followDao.FollowList(userIdx,folidx.get(1));
//
//        List FollowListDesc = followDao.FollowListDesc(userIdx);
//        List resultDetailList = new ArrayList<>(FollowList);
//        return resultDetailList;


/**
 * 조건 값을 의 갯수를 length 로 만든 다음에 해당 값을 포문 돌려서 한다. */
    }

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