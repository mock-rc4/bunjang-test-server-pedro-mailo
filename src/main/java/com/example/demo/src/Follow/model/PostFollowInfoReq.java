package com.example.demo.src.Follow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostFollowInfoReq {
    private int userIdx;
    private int followingIdx;
}
