package com.example.demo.src.home.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetHomeProductRes {
    private int Idx;    // 게시물 인덷스
    private String myLike;    // 찜 여부
    private String imageUrl;    // 제품 사진
    private int price;  // 제품 가격
    private int saftyPay;   // 안전결제 여부
    private String productName; // 제품 이름
    private String directtrans; // 직거래 가능 지역
    private String creareAt;    // 게시물 등록 시간
    private int productLike;    // 게시물 찜한 사람 수
}
