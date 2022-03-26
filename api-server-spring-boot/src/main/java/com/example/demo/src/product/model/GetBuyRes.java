package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetBuyRes {
    private int paymentIdx;     // 결제정보 인덱스
    private int productIdx;     // 상품 인덱스
    private String imageUrl;    // 상품 사진
    private int price;          // 상품 가격
    private String buyerName;   // 구매자 닉네임
    private int paymentMethod;  // 번개페이 여부
    private String updateAt;    // 결제정보 업데이트 시각
}
