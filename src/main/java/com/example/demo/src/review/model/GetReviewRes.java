package com.example.demo.src.review.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    private int UIdx;
    private String profileImage;
    private String shopName;
    private String createAt;
    private int reviewRate;
    private int paymentMethod;
    private String reviewDesc;
    private String imageUrl;
    private String productName;
    private int PIdx;

    //private int ;
    //private String ;
}
