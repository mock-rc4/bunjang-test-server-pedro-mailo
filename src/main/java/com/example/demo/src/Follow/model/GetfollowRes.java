package com.example.demo.src.follow.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor

public class GetfollowRes {
    private int followingIdx;
    private String shopName;
    private int productCnt;
    private int followCnt;
}
