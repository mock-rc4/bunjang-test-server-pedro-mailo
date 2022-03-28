package com.example.demo.src.home.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetHomeAdRes {
    private int Idx;    // 광고 인덱스
    private String adImageUrl;
    private String adTittle;
    private String adDesc;
    private String adLink;
}
