package com.example.demo.src.address.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostaddressRes {
    private String name;
    private String phoneNumber;
    private String address;
    private String addressDesc;
    private int userIdx;
    private int status;
}
