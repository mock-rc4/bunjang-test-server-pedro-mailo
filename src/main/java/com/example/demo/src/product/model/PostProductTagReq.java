package com.example.demo.src.product.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class PostProductTagReq {
    private int productIdx;
    private List<String> tagName;
}
