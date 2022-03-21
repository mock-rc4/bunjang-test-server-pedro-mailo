package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String shopName;
    private String phoneNumber;
    private String userName;
    private String userBirth;
    private String userPwd;
}
