package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PatchUserSettingReq {
    private String shopName;
    private String shopAddress;
    private String avaTimeStart;
    private String avaTimeEnd;
    private String shopIntro;
    private String shopPolicy;
    private String preCaution;

}
