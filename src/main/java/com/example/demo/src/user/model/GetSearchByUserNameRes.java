package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetSearchByUserNameRes {
   private int userIdx;
   private String profileImage;
   private String shopName;
   private int followercnt;
   private int productCnt;
}
