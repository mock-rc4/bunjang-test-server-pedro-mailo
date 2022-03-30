package com.example.demo.src.favortie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class GetUserFavoriteListRes {
    private String shopName;
    private int Idx;
    private String ProductName;
    private String ProductImage;
    private int price;
    private String Posteddate;
    private int safePay;
    private int mylike;
}
