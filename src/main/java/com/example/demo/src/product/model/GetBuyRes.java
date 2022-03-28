package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetBuyRes {
    private int PYIdx;     // 결제정보 인덱스
    private int PIdx;     // 상품 인덱스
    private String imageUrl;    // 상품 사진
    private String productName; // 제품 제목
    private int price;          // 상품 가격
    private String buyerName;   // 구매자 닉네임
    private int paymentMethod;  // 번개페이 여부
    private String updateTime;    // 결제정보 업데이트 시각
}
