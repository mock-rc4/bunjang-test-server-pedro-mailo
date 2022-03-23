package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProductListRes {
    private String Idx;
    private String ProductName;
    private int ProductPrice;
    private int SaftyPay;
    private String ProductImage;
    private String Posteddate;

}
