package com.example.demo.src.product.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostProductTagRes {
    private int Idx;
    private int productIdx;
    private String tagName;
}
