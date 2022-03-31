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

// 후기 존재하는지 확인
    public int checkReviewAlready(int userIdx, int paymentIdx){
        return reviewDao.checkReviewAlready(userIdx, paymentIdx);
    }


// 후기 등록
    public List<String> createReview(PostReviewReq postReviewReq, int userIdx,int paymentIdx)throws BaseException{
        try{
            PostCreateNewReview newReviewRes = reviewDao.createReview(postReviewReq, userIdx, paymentIdx);
            int ReviewIdx = newReviewRes.getReviewIdx();
            PostReviewRes ReviewDetail = newReviewRes.getReviewDetail();

            List newReview = new ArrayList<PostReviewRes>();
            newReview.add(ReviewDetail);

            List newReviewPictureList = reviewDao.createReviewPicture(postReviewReq, ReviewIdx);

            List newReviewDetail = new ArrayList<>(Arrays.asList(newReview, newReviewPictureList));

            return newReviewDetail;

        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

// 후기 수정
    public int editReview(PostReviewReq postReviewReq,int userIdx, int reviewIdx){
        System.out.println("리뷰 삭제 서비스 진입");
        int checkMyReview = reviewDao.checkMyReview(userIdx, reviewIdx);
        System.out.println(checkMyReview);
        System.out.println("리뷰 본인 확인 통과");
        List<String> editImageUrls = postReviewReq.getImageUrl();
        if(checkMyReview == 0){
            System.out.println("내가 쓴 리뷰가 아니었음");
            //throw new BaseException(NOT_MY_REVIEW); // 5001, "본인이 작성한 리뷰가 아닙니다."
            return 0;
        }

        if(editImageUrls != null){ /** 바꿀 사진이 있으면 */
            reviewDao.deleteReviewImage(reviewIdx); // 리뷰 이미지 삭제하고
            reviewDao.createReviewPicture(postReviewReq, reviewIdx);    // 새로운 이미지 집어넣는다
        }
        reviewDao.editReview(postReviewReq, reviewIdx);// 후기 별점, 내용 등 수정

        return 1;
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
