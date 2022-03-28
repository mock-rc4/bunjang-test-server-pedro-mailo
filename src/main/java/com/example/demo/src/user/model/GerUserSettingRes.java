package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class GerUserSettingRes {
    private String shopName;
    private String shopAddress;
    private String avaTime;
    private String shopIntro;
    private String shopPolicy;
    private String preCaution;
}
