package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class GetProductDetailRes {
    private int PIdx;
    private String imageUrl; //제품 사진
    private int price;       // 제품 가격
    private int saftyPay;    //안전페이 여부
    private String productName; // 제품 제목
    private String createAt;                // 생성 시각
    private int viewCount;      // 조회수
    private int likeCount;      // 게시물을 찜한 사람 수
    private String directtrans; // 직거래 가능위치
    private int productCondition;   //제품의 상태
    private int includeFee;     // 택배비 포함여부
    private int amount;         //수량
    private String productDesc; //제품 설명
    private String tag;         // 테그
    private String categoryName;    // 카테고리명
    private int UIdx;   // 판매자 인덱스
    private String profileImage;    //판매자 프로필 이미지
    private String shopName;    // 판매자 상점명
    private int follower;       // 판매자 팔로워
    private float avgStar;      // 판매자 평균별점
    private int reviewCount;                                // 리뷰 개수
    private int myLike;         // 내가 이 물건 찜한 여부

}

/**
 * private int Idx;    // 제품 인덱스
 *     private int price;  // 제품 가격
 *     private String productName; // 제품 제목
 *     private int saftyPay;   // 번개페이(안전페이) 여부        1/2
 *     private String imageUrl;
 *
 */