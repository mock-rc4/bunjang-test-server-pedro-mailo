package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostProductReq {
    //private int userIdx;
    private List<String> imageUrl;
    private List<String> tagName;

    private int categoryIdx;
    private String productName;
    private String productDesc;
    private int productCondition;
    private int saftyPay;
    private int isExchange;
    private int amount;
    private int includeFee;
    private int price;
    private String directtrans;
}
