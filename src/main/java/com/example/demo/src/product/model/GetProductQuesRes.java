package com.example.demo.src.product.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetProductQuesRes {
    private int QIdx;
    private int PIdx;     // 상품 인덱스
    private int UIdx;
    private String profileImage;
    private String shopName;
    private String createAt;
    private String questionDesc;
    private int myQuestion;
}
