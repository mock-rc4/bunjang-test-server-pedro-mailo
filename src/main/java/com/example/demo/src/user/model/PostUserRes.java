package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserRes {
    private String jwt;
    private int Idx;
     //정상적으로 올라오는 데이터인지!
    private String shopName;
    private String phoneNumber;
    private String userName;
    private String userBirth;
    private String userPwd;
}
