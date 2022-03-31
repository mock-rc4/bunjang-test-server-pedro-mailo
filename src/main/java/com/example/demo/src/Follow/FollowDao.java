package com.example.demo.src.Follow;

import com.example.demo.src.Follow.model.*;
import com.example.demo.src.favortie.model.PostFavoriteInfoRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;




@Repository
public class FollowDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    /**
     * 팔로우 한 이력 여부 체크 SQL 처리
     * */
    public int checkfollowInfo(int userIdx, int followingIdx) {
        String checkShopNameQuery = "select exists(select userIdx from Follow where userIdx = ? and followingIdx = ?)";
        int checkUserIdxParams = userIdx;
        int checkFollowingIdxParams = followingIdx;
        return this.jdbcTemplate.queryForObject(checkShopNameQuery,
                int.class,
                checkUserIdxParams, checkFollowingIdxParams);
    }


    /**
     * 현재 팔로우 여부 확인 위한 SQL 처리
     * */
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


    /**
     * 언팔로우 SQL 처리
     * */
    public int deletefollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String deletefollowQuery = "update Follow set status = 2 where userIdx = ? and followingIdx =?";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] deletefollowParams = new Object[]{userParams, followingIdxParams};
        return this.jdbcTemplate.update(deletefollowQuery, deletefollowParams);
    }


    /**
     * 언팔로우 유저 -> 팔로우로 변경 SQL 처리
     * */
    public int statusChangefollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String statusChangefollowQuery = "update Follow set status = 1 where userIdx = ? and followingIdx =?";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] statusChangefollowParams = new Object[]{userParams, followingIdxParams};
        return this.jdbcTemplate.update(statusChangefollowQuery, statusChangefollowParams);
    }


    /**
     * 새로 팔로우 한 유저 SQL 처리
     * */
    public int createFollowInfo(PostFollowInfoReq postFollowInfoReq) {
        String createFollowQuery = "insert into Follow (userIdx,followingIdx) values (?,?)";
        int userParams = postFollowInfoReq.getUserIdx();
        int followingIdxParams = postFollowInfoReq.getFollowingIdx();
        Object[] createFollowParams = new Object[]{userParams, followingIdxParams};
        return this.jdbcTemplate.update(createFollowQuery, createFollowParams);
    }



    /**
     * 팔로우한 유저 정보 조회
     * */
    public List<GetfollowRes> FollowList(int userIdx ,int q) {
        String FollowListQuery = "select F.followingIdx, U.shopName, P.productCnt , F2.followerCnt followCnt\n" +
                "from Follow F\n" +
                "         inner join (select count(*) followerCnt, followingIdx\n" +
                "                     from Follow\n" +
                "                     group by followingIdx) F2 on F2.followingIdx = F.followingIdx\n" +
                "\n" +
                "         left join User U on U.Idx = F.followingIdx\n" +
                "         left join (select userIdx, count(*) productCnt from Product group by userIdx) P on F.followingIdx = P.userIdx\n" +
                "\n" +
                "where F.userIdx = ? and F.followingIdx = ?;";
        int userParams = userIdx;
        int followingIdxParams = q;
        return this.jdbcTemplate.query(FollowListQuery,
                (rs, rowNum) -> new GetfollowRes(
                        rs.getInt("followingIdx"),
                        rs.getString("shopName"),
                        rs.getInt("productCnt"),
                        rs.getInt("followCnt")),
                userParams,followingIdxParams);

    }



    /**
     * 팔로우 한 유저 정보 조회
     * */
    public List<GetfollowDescRes> FollowListDesc(int userIdx, int q) {
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
                "where F.userIdx = ? and F.followingIdx =?;";
        int userParams = userIdx;
        int followintParams =q;
        return this.jdbcTemplate.query(FollowListDescQuery,
                (rs, rowNum) -> new GetfollowDescRes(
                        rs.getInt("price"),
                        rs.getString("imageUrl"),
                        rs.getInt("productIdx")),
                userParams,followintParams);

    }


    /**
     * 팔로잉한 유저 인수 구하기 위한 SQL 처리 . 솔직히 능력부족으로 억지로 만들어낸 클래스
     * */
    public List<FollointIdxRes> getfollowIdxInfo(int userIdx) {
        String FollointIdxResQuery = "select F.followingIdx followingIdx\n" +
                "from Follow F\n" +
                "where F.userIdx = ?";
        int userParams = userIdx;
        return this.jdbcTemplate.query(FollointIdxResQuery,
                (rs, rowNum) -> new FollointIdxRes(
                        rs.getInt("followingIdx")),
                userParams);

    }



    /**
     * 유저를 팔로우한 유저 정보 SQL 처리
     * */
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


    /**
     * 팔로우한 유저 IDX 추출하기위한 SQL 처리
     * */
    public int getfollowoneIdx(int k) {
        String lastInserIdQuery = "select F.followingIdx\n" +
                "from Follow F\n" +
                "where F.userIdx = 1  LIMIT ?,1 ";
        int userParams = k ;
        Object[] statusChangefollowParams = new Object[]{userParams};
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,statusChangefollowParams,int.class);}

}
