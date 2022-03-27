package com.example.demo.src.Follow;


import com.example.demo.config.BaseException;
import com.example.demo.src.Follow.*;

import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
