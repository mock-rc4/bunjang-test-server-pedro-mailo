package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ReviewProvider {
    private final ReviewDao reviewDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReviewProvider(ReviewDao reviewDao, JwtService jwtService){
        this.reviewDao = reviewDao;
        this.jwtService = jwtService;
    }


    public List<GetReviewRes> getReviewsByUserIdx(int userIdx)throws BaseException{
        try{
            List<GetReviewRes> getReviewResList = reviewDao.getReviewsByuserIdx(userIdx);
            return getReviewResList;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
















}/** reviewProvider 끝나는 괄호*/
