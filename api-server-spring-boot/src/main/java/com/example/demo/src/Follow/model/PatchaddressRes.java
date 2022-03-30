package com.example.demo.src.Follow.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchaddressRes {
    private String name;
    private String phoneNumber;
    private String address;
    private String addressDesc;
    private int defaultAddress;
    private int userIdx;
}
