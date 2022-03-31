package com.example.demo.src.review;

import com.example.demo.src.product.model.PostProductRes;
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


    /**
     * 내가 산 제품인지 확인쿼리
     * payment의 buyerIdx와 jwt로 받은 userIdx 비교
     **/
    public int checkPaymentBuyerIdx(int paymentIdx, int userIdx){
        String checkQuery = "select exists(\n" +
                "    select *\n" +
                "    where PY.buyerIdx = ?\n" +
                "           )\n" +
                "from Payment PY\n" +
                "where PY.idx = ?";
        return this.jdbcTemplate.queryForObject(checkQuery,int.class, userIdx, paymentIdx);
    }


    /**
     * 후기 이미 존재하는지 확인쿼리
     **/
    public int checkReviewAlready(int userIdx, int paymentIdx){
        String checkQuery = "select exists( select *\n" +
                "                where R.userIdx = ?) myReview\n" +
                "                from Review R\n" +
                "                where R.paymentIdx = ?";
        return this.jdbcTemplate.queryForObject(checkQuery,int.class,userIdx,paymentIdx);
    }


    /**
     * 후기 등록
     **/
    public PostCreateNewReview createReview(PostReviewReq postReviewReq, int userIdx,int paymentIdx){
        String createReviewQuery = "insert into Review(createAt, updateAt, reviewRate, reviewDesc, paymentIdx, userIdx)" +
                "values (current_timestamp,current_timestamp,?,?,?,?)";
        this.jdbcTemplate.update(createReviewQuery, postReviewReq.getReviewRate(), postReviewReq.getReviewDesc(), paymentIdx, userIdx);
        String lastInsertQuery = "select last_insert_id()";

        PostReviewRes postReviewRes;
        postReviewRes = new PostReviewRes(postReviewReq.getReviewRate(), postReviewReq.getReviewDesc(), paymentIdx, userIdx);

        PostCreateNewReview ReviewIdxandRes;
        ReviewIdxandRes = new PostCreateNewReview(this.jdbcTemplate.queryForObject(lastInsertQuery, int.class), postReviewRes);
        //return this.jdbcTemplate.queryForObject(lastInsertQuery,int.class);

        return ReviewIdxandRes;
    }

    /**
     * 후기 사진 등록
     **/
    public List<String> createReviewPicture(PostReviewReq postReviewReq, int reviewIdx){
        List<String> pic = postReviewReq.getImageUrl();
        List newPictureList = new ArrayList();
        if(pic != null){
            for(String newpic:pic){
                String createReviewPicture = "insert into ReviewImage(imageUrl, reviewIdx) values(?,?)";
                this.jdbcTemplate.update(createReviewPicture,newpic, reviewIdx);
                newPictureList.add(newpic);
            }
        }
        return newPictureList;
    }


    /**
     * 후기 수정 쿼리
     **/
    public void editReview(PostReviewReq postReviewReq, int reviewIdx){
        System.out.println("리뷰 수정 다오 진입");
        String editReviewQuery = "update Review R\n" +
                "set R.reviewRate = ?, R.reviewDesc = ?\n" +
                "where R.Idx = ?";
        this.jdbcTemplate.update(editReviewQuery,postReviewReq.getReviewRate(), postReviewReq.getReviewDesc(), reviewIdx);
    }








    /**
     * 내가 쓴 후기인지 확인 쿼리
     **/
    public int checkMyReview(int userIdx,int reviewIdx){
        System.out.println("내가 쓴 후기인지 확인 dao 진입");
        String checkReviewUserIdxquery ="select case when exists(select * from Review R\n" +
                "                        where R.userIdx=? and R.Idx = ?) then 1\n" +
                "            else 0 end checkMyReview";
        if(this.jdbcTemplate.queryForObject(checkReviewUserIdxquery,int.class, userIdx, reviewIdx) == null){
            return 3;
        }
        int checkResult = this.jdbcTemplate.queryForObject(checkReviewUserIdxquery,int.class, userIdx, reviewIdx);
        System.out.println(checkResult);

        return this.jdbcTemplate.queryForObject(checkReviewUserIdxquery,int.class, userIdx, reviewIdx);
    }


    /**
     * 후기 이미지 삭제 쿼리
     **/
    public void deleteReviewImage(int reviewIdx){
        System.out.println("후기 이미지 삭제 dao 진입");
        String deleteReviewImagequery = "delete from ReviewImage where reviewIdx = ?";
        this.jdbcTemplate.update(deleteReviewImagequery,reviewIdx);
    }


    /**
     * 후기 삭제 쿼리
     **/
    public void deleteReview(int reviewIdx) {
        System.out.println("후기 삭제 dao 진입");
        String deleteReviewquery = "delete from Review where Idx = ?";
        this.jdbcTemplate.update(deleteReviewquery,reviewIdx);
    }






}/**reviewDao 끝나는 괄호*/
