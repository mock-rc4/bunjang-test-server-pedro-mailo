package com.example.demo.src.favortie.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostFavoriteInfoReq {
    private int userIdx;
    private int productIdx;
}
