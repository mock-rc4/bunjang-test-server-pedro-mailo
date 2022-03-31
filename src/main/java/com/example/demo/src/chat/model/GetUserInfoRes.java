package com.example.demo.src.chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserInfoRes {

    private int userIdx;
    private String userShopName;
    private String userProfileImage;
    private int reviewRateCnt;
    private float reviewRateAvg;
    private int producCnt;
    private int procutCompelteCnt;

}
