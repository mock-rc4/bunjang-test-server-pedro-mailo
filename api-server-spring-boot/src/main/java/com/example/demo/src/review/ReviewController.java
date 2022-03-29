package com.example.demo.src.review;

import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.product.ProductService;
import com.example.demo.src.review.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;


@RestController
@RequestMapping("/app/reviews")
public class ReviewController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final JwtService jwtService;

    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService, JwtService jwtService){
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
        this.jwtService = jwtService;
    }




    /**
     * userIdx에 따른 후기 조회 -> 특정 상점의 후기 보아보기
     * [GET] reviews/shopIdx/{userIdx}
     */
    @ResponseBody
    @GetMapping("/shopIdx/{userIdx}")
    public BaseResponse<List<GetReviewRes>> getReviewsByUserIdx(@PathVariable("userIdx") int userIdx){
        try {
            List<GetReviewRes> getReviewResList = reviewProvider.getReviewsByUserIdx(userIdx);
            return new BaseResponse<>(getReviewResList);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }






















}/** reviewController 끝나는 괄호*/
