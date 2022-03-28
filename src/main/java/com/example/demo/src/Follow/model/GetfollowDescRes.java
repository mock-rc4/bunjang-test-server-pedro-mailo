package com.example.demo.src.Follow.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class GetfollowDescRes {
    private int followingIdx;
    private String shopName;
    private int price;
    private String imageUrl;
    private int productIdx;
}
