package com.example.demo.src.review;

import com.example.demo.src.review.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 특정 상점에 대한 후기 조회
     **/
    public List<GetReviewRes> getReviewsByuserIdx(int userIdx){
        String getReviewQuery = "select U.Idx UIdx, U.profileImage, U.shopName,  case when 60 > timestampdiff(MINUTE , R.createAt, current_timestamp)\n" +
                "                                        then concat(timestampdiff(MINUTE, R.createAt, current_timestamp), '분 전')\n" +
                "                                        when 24 >= timestampdiff(HOUR, R.createAt, current_timestamp)\n" +
                "                                        then concat(timestampdiff(HOUR, R.createAt, current_timestamp), '시간 전')\n" +
                "                                        when 31 >= timestampdiff(DAY , R.createAt, current_timestamp)\n" +
                "                                        then concat(timestampdiff(Day , R.createAt, current_timestamp), '일 전')\n" +
                "                                        else concat(timestampdiff(MONTH , R.createAt, current_timestamp), '달 전')\n" +
                "                                        end createAt,\n" +
                "       R.reviewRate,PY.paymentMethod,\n" +
                "       R.reviewDesc,\n" +
                "       group_concat(distinct RI.imageUrl) imageUrl,\n" +
                "       P.productName,\n" +
                "       P.Idx PIdx\n" +
                "\n" +
                "from Review R\n" +
                "join User U on U.Idx = R.userIdx\n" +
                "join Payment PY on R.paymentIdx = PY.Idx\n" +
                "join ReviewImage RI on R.Idx = RI.reviewIdx\n" +
                "join Product P on PY.productIdx = P.Idx\n" +
                "where P.userIdx = ?\n" +
                "group by P.Idx";
        int getUserIdxParm = userIdx;
        return this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getInt("UIdx"),
                        rs.getString("profileImage"),
                        rs.getString("shopName"),
                        rs.getString("createAt"),
                        rs.getInt("reviewRate"),
                        rs.getInt("paymentMethod"),
                        rs.getString("reviewDesc"),
                        rs.getString("imageUrl"),
                        rs.getString("productName"),
                        rs.getInt("PIdx")
                ), getUserIdxParm);
    }








}/**reviewDao 끝나는 괄호*/
