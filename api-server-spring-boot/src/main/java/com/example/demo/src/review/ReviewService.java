package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.*;

import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;

    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService){
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService;
    }


// 후기 삭제
    public int deleteReview(int userIdx, int reviewIdx)throws BaseException{
        try{
            System.out.println("리뷰 삭제 서비스 진입");
            int checkMyReview = reviewDao.checkMyReview(userIdx, reviewIdx);
            System.out.println("내가 쓴 리뷰 확인 dao 통과");
            System.out.println(checkMyReview);
            if(checkMyReview == 0){
                System.out.println("내가 쓴 리뷰가 아니었음");
                //throw new BaseException(NOT_MY_REVIEW); // 5001, "본인이 작성한 리뷰가 아닙니다."
                return 0;
            }
            else{
                reviewDao.deleteReviewImage(reviewIdx);
                reviewDao.deleteReview(reviewIdx);
                return 1;
            }
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }





}/** reviewService 끝나는 괄호*/
