package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostProductQuesReq {
    //private int QIdx;
    //private int PIdx;     // 상품 인덱스
    //private int UIdx;
    private String questionDesc;
}
