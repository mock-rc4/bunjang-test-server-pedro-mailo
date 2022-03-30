package com.example.demo.src.Follow;

import com.example.demo.src.Follow.model.*;
import com.example.demo.src.favortie.model.PostFavoriteInfoRes;
import com.example.demo.src.user.model.GetUserRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;




@Repository
public class FollowDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int checkfollowInfo(int userIdx, int followingIdx) {
        String checkShopNameQuery = "select exists(select userIdx from Follow where userIdx = ? and followingIdx = ?)";
        int checkUserIdxParams = userIdx;
        int checkFollowingIdxParams = followingIdx;
        return this.jdbcTemplate.queryForObject(checkShopNameQuery,
                int.class,
                checkUserIdxParams, checkFollowingIdxParams);
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
                userParams, followingIdxParams);
    }

    public int deletefollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String deletefollowQuery = "update Follow set status = 2 where userIdx = ? and followingIdx =?";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] deletefollowParams = new Object[]{userParams, followingIdxParams};
        return this.jdbcTemplate.update(deletefollowQuery, deletefollowParams);
    }

    public int statusChangefollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String statusChangefollowQuery = "update Follow set status = 1 where userIdx = ? and followingIdx =?";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] statusChangefollowParams = new Object[]{userParams, followingIdxParams};
        return this.jdbcTemplate.update(statusChangefollowQuery, statusChangefollowParams);
    }

    public int createFollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String createFollowQuery = "insert into Follow (userIdx,followingIdx) values (?,?)";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] createFollowParams = new Object[]{userParams, followingIdxParams};
        return this.jdbcTemplate.update(createFollowQuery, createFollowParams);
    }

    public List<GetfollowRes> FollowList(int userIdx) {
        String FollowListQuery = "select F.followingIdx, U.shopName, P.productCnt , F2.followerCnt followCnt\n" +
                "from Follow F\n" +
                "         inner join (select count(*) followerCnt, followingIdx\n" +
                "                     from Follow\n" +
                "                     group by followingIdx) F2 on F2.followingIdx = F.followingIdx\n" +
                "\n" +
                "         left join User U on U.Idx = F.followingIdx\n" +
                "         left join (select userIdx, count(*) productCnt from Product group by userIdx) P on F.followingIdx = P.userIdx\n" +
                "\n" +
                "where F.userIdx = ?;";
        int userParams = userIdx;
        return this.jdbcTemplate.query(FollowListQuery,
                (rs, rowNum) -> new GetfollowRes(
                        rs.getInt("followingIdx"),
                        rs.getString("shopName"),
                        rs.getInt("productCnt"),
                        rs.getInt("followCnt")),
                userParams);

    }


    public List<GetfollowDescRes> FollowListDesc(int userIdx) {
        String FollowListDescQuery = "select F.followingIdx, U.shopName, price, P.imageUrl, P.Idx productIdx\n" +
                "from Follow F\n" +
                "         inner join (select count(*) followerCnt, followingIdx\n" +
                "                     from Follow\n" +
                "                     group by followingIdx) F2 on F2.followingIdx = F.followingIdx\n" +
                "\n" +
                "         left join User U on U.Idx = F.followingIdx\n" +
                "         left join (select Product.Idx, userIdx, price, PI.imageUrl imageUrl\n" +
                "                    from Product\n" +
                "                             left join(select * from ProductImage group by productIdx) PI\n" +
                "                                      on Product.Idx = PI.productIdx) P on F.followingIdx = P.userIdx\n" +
                "\n" +
                "where F.userIdx = ?;";
        int userParams = userIdx;
        return this.jdbcTemplate.query(FollowListDescQuery,
                (rs, rowNum) -> new GetfollowDescRes(
                        rs.getInt("followingIdx"),
                        rs.getString("shopName"),
                        rs.getInt("price"),
                        rs.getString("imageUrl"),
                        rs.getInt("productIdx")),
                userParams);

    }

    public List<FollointIdxRes> getfollowIdxInfo(int userIdx) {
        String FollointIdxResQuery = "select F.followingIdx followingIdx\n" +
                "from Follow F\n" +
                "where F.userIdx = ?";
        int userParams = userIdx;
        System.out.println(jdbcTemplate.query(FollointIdxResQuery,
                (rs, rowNum) -> new FollointIdxRes11(
                        rs.getInt("followingIdx")),
                userParams)); // 캡처한 화면 결과값
        return this.jdbcTemplate.query(FollointIdxResQuery,
                (rs, rowNum) -> new FollointIdxRes(
                        rs.getInt("followingIdx")),
                userParams);

    }


    public List<GetFollowerRes> getuserFollower(int userIdx) {
        String getuserFollowerQuery = "select F.userIdx userIdx, U.shopName shopName, case when P.productCnt is null then 0 else  P.productCnt end productCnt ,case when F2.followerCnt is null then 0 else F2.followerCnt end followCnt, U.profileImage userImage\n" +
                "from Follow F\n" +
                "         left join (select count(*) followerCnt, followingIdx\n" +
                "                     from Follow\n" +
                "                     group by followingIdx) F2 on F2.followingIdx = F.userIdx\n" +
                "\n" +
                "         left join User U on U.Idx = F.userIdx\n" +
                "         left join (select userIdx, count(*) productCnt from Product group by userIdx) P on F.userIdx = P.userIdx\n" +
                "\n" +
                "where F.followingIdx = ? and F.status =1 ";

        int userParams = userIdx;
        return this.jdbcTemplate.query(getuserFollowerQuery,
                (rs, rowNum) -> new GetFollowerRes (
                        rs.getInt("userIdx"),
                        rs.getString("shopName"),
                        rs.getInt("productCnt"),
                        rs.getInt("followCnt"),
                        rs.getString("userImage")),
                userParams);
    }
    @Getter
    @AllArgsConstructor
    public class FollointIdxRes11 {
        int followingIdx;
    }
}
