package com.example.demo.src.review.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewRes {
    //private String createAt;
    private int reviewRate;
    private String reviewDesc;
    private int paymentIdx;
    private  int userIdx;
}
