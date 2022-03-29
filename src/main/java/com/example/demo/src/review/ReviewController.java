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
     * 후기 생성
     * [POST] reviews/paymentIdx/{paymentIdx}
     */
    @ResponseBody
    @PostMapping("/paymentIdx/{paymentIdx}")
    public BaseResponse<List<String>> createReview(@RequestBody PostReviewReq postReviewReq, @PathVariable("paymentIdx") int paymentIdx){
        try{
            System.out.println(paymentIdx);
            int userIdxByJwt = jwtService.getUserIdx();

            int checkuserIdx = reviewProvider.checkBuyerIdxandjwt(paymentIdx, userIdxByJwt);
            if(checkuserIdx == 0){
                throw new BaseException(INVALID_USER_JWT);
            }
            else{
                List<String> getReviewDetail = reviewService.createReview(postReviewReq, userIdxByJwt, paymentIdx);
                return new BaseResponse<>(getReviewDetail);
            }
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
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


    /**
     * 후기 삭제
     * [DELETE] reviews/{reviewIdx}/delete
     */
    @ResponseBody
    @DeleteMapping("/{reviewIdx}/delete")
    public BaseResponse<String> deleteReview(@PathVariable("reviewIdx") int reviewIdx){
        try{
            System.out.println("리뷰 삭제 컨트롤러 진입");
            int userIdxByJwt = jwtService.getUserIdx();
            int deleteReview = reviewService.deleteReview(userIdxByJwt, reviewIdx);
            if (deleteReview == 0){
                throw new BaseException(NOT_MY_REVIEW); // 5001, "본인이 작성한 리뷰가 아닙니다."
            }
            else {
                return new BaseResponse<>("상품후기 삭제 성공");
            }
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }



















}/** reviewController 끝나는 괄호*/
