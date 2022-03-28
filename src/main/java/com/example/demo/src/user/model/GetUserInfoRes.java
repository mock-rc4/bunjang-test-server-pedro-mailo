package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserInfoRes {
    String userShopName;
    String userProfileImage;
    int userFavCount;
    int userReviewCount;
    int userFollowingCount;
    int userFollwerCount;
    Float reviewrate;
}
