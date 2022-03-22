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
        String checkPhoneQuery = "select exists(select phoneNumber from User where phoneNumber = ?)";
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

    public User getPwd(PostLoginReq postLoginReq) {
        String getPwdQuery = "select Idx, phoneNumber,userBirth,userName,userPwd from User where phoneNumber = ? and userName= ? and userBirth = ?";
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
                getPhoneParams,getNameParams,getBirthParams
        );

    }


//    public User userLogin(PostLoginReq postLoginReq) {
//        String getUserByPhoneNumberQuery = "select userInfoIdx, phoneNumber, password, nickname, profileImageUrl from UserInfo where phoneNumber = ?";
//        String getUserByPhoneNumberParams = postLoginReq.getPhoneNumber();
//
//        return this.jdbcTemplate.queryForObject(getUserByPhoneNumberQuery,
//                (rs, rowNum) -> new User(
//                        rs.getInt("Idx"),
//                        rs.getString("phoneNumber"),
//                        rs.getString("userBirth"),
//                        rs.getString("userName"),
//                        rs.getString("userPwd")
//                getUserByPhoneNumberParams
//        );
//
//    }


    public User userJoin(PostLoginReq postLoginReq) {
        String createUserQuery = "insert into User (phoneNumber, userName,userBirth,userPwd) VALUES (?,?,?,?);";
        Object[] createUserParams = new Object[]{postLoginReq.getPhoneNumber(), postLoginReq.getUserName(), postLoginReq.getUserBirth(), postLoginReq.getUserPwd()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertUserIdQuery = "select last_insert_id()";
        int lastInsertUserId = this.jdbcTemplate.queryForObject(lastInsertUserIdQuery, int.class);

//        String createUserRegionQuery = "insert into Region (userInfoId, regionNameCity, regionNameGu, regionNameTown) VALUES (?,?,?,?)";
//        Object[] createUserRegionParams = new Object[]{lastInsertUserId, postUserLoginReq.getRegionNameCity(), postUserLoginReq.getRegionNameGu(), postUserLoginReq.getRegionNameTown()};
//        this.jdbcTemplate.update(createUserRegionQuery,createUserRegionParams);

        // String lastInsertIdQuery = "select last_insert_id()";
        // return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
        // return lastInsertUserId;
        String getPwdQuery = "select Idx, phoneNumber,userBirth,userName,userPwd from User where phoneNumber = ? and userName= ? and userBirth = ?";
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
                getPhoneParams,getNameParams,getBirthParams
        );
    }
}
