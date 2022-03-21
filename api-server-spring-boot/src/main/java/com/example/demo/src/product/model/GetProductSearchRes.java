package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetProductSearchRes {
    private int Idx;    // 제품 인덱스
    private int price;  // 제품 가격
    private String productName; // 제품 제목
    private int saftyPay;   // 번개페이(안전페이) 여부        1/2
    private String imageUrl;
}
