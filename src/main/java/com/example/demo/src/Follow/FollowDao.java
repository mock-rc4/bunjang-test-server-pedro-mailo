package com.example.demo.src.Follow;

import com.example.demo.src.Follow.model.*;
import com.example.demo.src.favortie.model.PostFavoriteInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class FollowDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int checkfollowInfo(int userIdx, int followingIdx) {
        String checkShopNameQuery = "select exists(select userIdx from Follow where userIdx = ? and followingIdx = ?)";
        int checkUserIdxParams = userIdx;
        int checkFollowingIdxParams = followingIdx;
        return this.jdbcTemplate.queryForObject(checkShopNameQuery,
                int.class,
                checkUserIdxParams,checkFollowingIdxParams);
    }

    public PostFavoriteInfoRes getfollowinfo(PostFollowInfoReq postFollowInfoReq) {
        String getfollowinfoQuery = "select userIdx,followingIdx,status from Follow where userIdx = ? and followingIdx =?";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        return this.jdbcTemplate.queryForObject(getfollowinfoQuery,
                (rs, rowNum) -> new PostFavoriteInfoRes(
                        rs.getInt("userIdx"),
                        rs.getInt("followingIdx"),
                        rs.getInt("status")),
                userParams,followingIdxParams);
    }

    public int deletefollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String deletefollowQuery = "update Follow set status = 2 where userIdx = ? and followingIdx =?";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] deletefollowParams = new Object[]{userParams,followingIdxParams};
        return this.jdbcTemplate.update(deletefollowQuery, deletefollowParams);
    }

    public int statusChangefollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String statusChangefollowQuery = "update Follow set status = 1 where userIdx = ? and followingIdx =?";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] statusChangefollowParams = new Object[]{userParams,followingIdxParams};
        return this.jdbcTemplate.update(statusChangefollowQuery, statusChangefollowParams);
    }

    public int createFollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String createFollowQuery = "insert into Follow (userIdx,followingIdx) values (?,?)";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] createFollowParams = new Object[]{userParams,followingIdxParams};
        return this.jdbcTemplate.update(createFollowQuery, createFollowParams);
    }
}
