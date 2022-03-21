package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

/** 검색어로 제품 조회 **/
    public List<GetProductSearchRes> getProductSearchRes(String keyword){
        String getProductsQuery = "select P.Idx, P.price, P.productName, P.saftyPay, PI.imageUrl\n" +
                                  "from Product as P\n" +
                                  "left join ProductImage as PI on P.Idx = PI.productIdx\n" +
                                  "where P.productName like concat('%',?,'%')\n" +
                                  "group by P.Idx";
        String GetProductSearchResPrams = keyword;
        return this.jdbcTemplate.query(getProductsQuery,
                (rs,rowNum) -> new GetProductSearchRes(
                        rs.getInt("Idx"),
                        rs.getInt("price"),
                        rs.getString("productName"),
                        rs.getInt("saftyPay"),
                        rs.getString("imageUrl")),
                GetProductSearchResPrams);
    }   // getProductSearchRes() 끝



} /** productDao class 닫는 괄호 **/

/**
private int Idx;    // 제품 인덱스
private int price;  // 제품 가격
private String productName; // 제품 제목
private int saftyPay;
 **/