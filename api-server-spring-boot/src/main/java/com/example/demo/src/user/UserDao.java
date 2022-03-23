package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers() {
        String getUsersQuery = "select * from UserInfo";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password"))
        );
    }

    public List<GetUserRes> getUsersByEmail(String email) {
        String getUsersByEmailQuery = "select * from UserInfo where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userIdx) {
        String getUserQuery = "select * from UserInfo where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUserParams);
    }


    public int createUser(PostUserReq postUserReq) {
        System.out.println("CCC6");
        String createUserQuery = "insert into User (shopName, phoneNumber, userName,userBirth,userPwd) VALUES (?,?,?,?,?);";
        System.out.println("CCC8");
        //Object[] createUserParams = new Object[]{postUserReq.getShopName(),postUserReq.getPhoneNumber(),postUserReq.getUserName(),postUserReq.getUserBirth(),postUserReq.getUserPwd()};
        System.out.println("CCC9");
        System.out.println(createUserQuery);
        //System.out.println(createUserParams);
        this.jdbcTemplate.update(createUserQuery, postUserReq.getShopName(), postUserReq.getPhoneNumber(), postUserReq.getUserName(), postUserReq.getUserBirth(), postUserReq.getUserPwd());//// 에러 나는 라인!
        System.out.println("CCC10");
        String lastInserIdQuery = "select last_insert_id()";
        System.out.println("CCC11");
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }

    public int checkEmail(String email) {
        String checkEmailQuery = "select exists(select email from UserInfo where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int checkPhone(String phoneNumber) {
        String checkPhoneQuery = "select exists(select phoneNumber from User where phoneNumber = ? and status = 1)";
        String checkPhoneParams = phoneNumber;
        return this.jdbcTemplate.queryForObject(checkPhoneQuery,
                int.class,
                checkPhoneParams);
    }

    public int modifyUserName(PatchUserReq patchUserReq) {
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery, modifyUserNameParams);
    }

    public User getUserInfo(PostLoginReq postLoginReq) {
        String getPwdQuery = "select Idx, phoneNumber,userBirth,userName,userPwd from User where phoneNumber = ? and status = 1 ";
        String getPhoneParams = postLoginReq.getPhoneNumber();
        String getNameParams = postLoginReq.getUserName();
        String getBirthParams = postLoginReq.getUserBirth();
        //Object[] createUserParams = new Object[]{postLoginReq.getPhoneNumber(),postLoginReq.getUserBirth(),postLoginReq.getUserName(),postLoginReq.getUserPwd()};
        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("Idx"),
                        rs.getString("phoneNumber"),
                        rs.getString("userBirth"),
                        rs.getString("userName"),
                        rs.getString("userPwd")
                ),
                getPhoneParams
        );

    }

    public List<GetUserInfoRes> UserInfo(int userIdx) {
        String GetUserInfoResQuery = "select U.shopName  userShopName,\n" +
                "       U.profileImage userProfileImage,\n" +
                "       count(F.userIdx)       as userFavCount,\n" +
                "       count(R.userIdx)       as userReviewCount,\n" +
                "       count(F2.userIdx)      as userFollowingCount,\n" +
                "       count(F2.followingIdx) as userFollwerCount,\n" +
                "       case when avg(R.reviewRate) is null then 0 else avg(R.reviewRate) end as `reviewrate`\n" +
                "from User U\n" +
                "         left join Favorite F on U.Idx = F.userIdx\n" +
                "         left join Review R on U.Idx = R.userIdx\n" +
                "         left join (select * from Follow where status = 1) F2 on F2.userIdx = U.Idx\n" +
                "where U.Idx = ?;" ;
        int GetUserInfoResParams = userIdx;
        return this.jdbcTemplate.query(GetUserInfoResQuery,
                (rs, rowNum) -> new GetUserInfoRes(
                                rs.getString("userShopName"),
                                rs.getString("userProfileImage"),
                                rs.getInt("userFavCount"),
                                rs.getInt("userReviewCount"),
                                rs.getInt("userFollowingCount"),
                                rs.getInt("userFollwerCount"),
                                rs.getFloat("reviewrate")),
                        GetUserInfoResParams);

    }

    public List<UserProductCountRes> ProductCount(int userIdx, int progress) {
        String UserProductCntResQuery = "select count(*) as count from Product where userIdx = ? and status = 1 and progress = ? ;" ;
        int UserProductCntParams = userIdx;
        int UserProductCntParams2 = progress;
        return this.jdbcTemplate.query(UserProductCntResQuery,
                (rs, rowNum) -> new UserProductCountRes(
                        rs.getInt("count")),
                UserProductCntParams,UserProductCntParams2);

    }

    public List<UserProductListRes> ProductDetail(int userIdx, int progress) {
        String UserProductCntResQuery = "select P.Idx Idx, P.productName ProductName, P.price ProductPrice , P.saftyPay SaftyPay, PI.imageUrl ProductImage,\n" +
                "       case\n" +
                "           when 24 >= timestampdiff(HOUR, P.updateAt, current_timestamp)\n" +
                "               then concat(timestampdiff(HOUR, P.updateAt, current_timestamp), '시간 전')\n" +
                "           else concat(timestampdiff(DAY, P.updateAt, current_timestamp), '일 전') end Posteddate\n" +
                "from Product P\n" +
                "left join(select * from ProductImage group by productIdx) PI on P.Idx = PI.productIdx\n" +
                "where userIdx=? and P.status = 1 and progress =?;" ;
        int UserProductCntParams = userIdx;
        int UserProductCntParams2 = progress;
        return this.jdbcTemplate.query(UserProductCntResQuery,
                (rs, rowNum) -> new UserProductListRes(
                        rs.getString("Idx"),
                        rs.getString("ProductName"),
                        rs.getInt("ProductPrice"),
                        rs.getInt("SaftyPay"),
                        rs.getString("ProductImage"),
                        rs.getString("Posteddate")
                        ),
                UserProductCntParams,UserProductCntParams2);
    }


///

/*로그인 주석*/
///
}
