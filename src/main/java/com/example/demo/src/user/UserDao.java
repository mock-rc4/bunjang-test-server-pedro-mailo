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



    /**
     * 회원가입 API SQL 처리
     */
    public int createUser(PostUserReq postUserReq) {
        String createUserQuery = "insert into User (shopName, phoneNumber, userName,userBirth,userPwd) VALUES (?,?,?,?,?);";
        this.jdbcTemplate.update(createUserQuery, postUserReq.getShopName(), postUserReq.getPhoneNumber(), postUserReq.getUserName(), postUserReq.getUserBirth(), postUserReq.getUserPwd());
        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }


    /**
     * 동일 핸드폰번호 존재 유무 SQL 처리
     */
    public int checkPhone(String phoneNumber) {
        String checkPhoneQuery = "select exists(select phoneNumber from User where phoneNumber = ? and status = 1)";
        String checkPhoneParams = phoneNumber;
        return this.jdbcTemplate.queryForObject(checkPhoneQuery,
                int.class,
                checkPhoneParams);
    }

    /**
     * 동일 상점명 존재유무 SQL 처리
     */
    public int checkShopName(String shopName) {
        String checkShopNameQuery = "select exists(select shopName from User where shopName = ? and status = 1)";
        String checkShopNameParams = shopName;
        return this.jdbcTemplate.queryForObject(checkShopNameQuery,
                int.class,
                checkShopNameParams);
    }


    /**
     * 상점명 변경 SQL 처리
     */
    public int modifyshopName(PatchShopNameReq patchShopNameReq) {
        String modifyshopNameQuery = "update User set shopName = ? where Idx = ? ";
        Object[] modifyshopNameParams = new Object[]{patchShopNameReq.getShopName(), patchShopNameReq.getUserIdx()};
        return this.jdbcTemplate.update(modifyshopNameQuery, modifyshopNameParams);
    }


    /**
     * 생년월일 변경 SQL 처리
     */
    public int modifyUserBirth(PatchUserBirthReq patchUserBirthReq) {
        String modifyUserBirthQuery = "update User set userBirth = ? where Idx = ? ";
        Object[] modifyUserBirthParams = new Object[]{patchUserBirthReq.getUserBirth(), patchUserBirthReq.getUserIdx()};
        return this.jdbcTemplate.update(modifyUserBirthQuery, modifyUserBirthParams);
    }


    /**
     * 성별 번경 SQL 처리
     */
    public int modifyUserSex(PatchUserSexReq patchUserSexReq) {
        String modifyUserSexQuery = "update User set userSex = ? where Idx = ? ";
        Object[] modifyUserSexParams = new Object[]{patchUserSexReq.getUserSex(), patchUserSexReq.getUserIdx()};
        return this.jdbcTemplate.update(modifyUserSexQuery, modifyUserSexParams);

    }

    /**
     * 핸드폰번호 변경 SQL 처리
     */
    public int modifyUserPhone(PatchUserPhoneReq patchUserPhoneReq) {
        String modifyUserPhoneQuery = "update User set phoneNumber = ? where Idx = ? ";
        Object[] modifyUserPhoneParams = new Object[]{patchUserPhoneReq.getPhoneNumber(), patchUserPhoneReq.getUserIdx()};
        return this.jdbcTemplate.update(modifyUserPhoneQuery, modifyUserPhoneParams);

    }


    /**
     * 로그인 API 관련 SQL 처리
     */
    public User getUserInfo(PostLoginReq postLoginReq) {
        String getPwdQuery = "select Idx, phoneNumber,userBirth,userName,userPwd,shopName from User where phoneNumber = ? and status = 1 ";
        String getPhoneParams = postLoginReq.getPhoneNumber();
        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> new User(
                        rs.getInt("Idx"),
                        rs.getString("phoneNumber"),
                        rs.getString("userBirth"),
                        rs.getString("userName"),
                        rs.getString("userPwd"),
                        rs.getString("shopName")
                ),
                getPhoneParams
        );

    }


    /**
     * 유저 메인페이지 중, 유저 개인정보 나타내는 SQL 처리
     */
    public List<GetUserInfoRes> UserInfo(int userIdx) {
        String GetUserInfoResQuery = "select U.shopName                                       userShopName,\n" +
                "       U.profileImage                                   userProfileImage,\n" +
                "       favCnt                            as userFavCount,\n" +
                "       count(R.userIdx)                              as userReviewCount,\n" +
                "       F2.followcnt                                  as userFollowingCount,\n" +
                "       F3.followercnt                                as userFollwerCount,\n" +
                "       case\n" +
                "           when avg(distinct FORRATE.reviewRate) is null then 0\n" +
                "           else avg(distinct FORRATE.reviewRate) end as `reviewrate`,\n" +
                "       count(distinct FORRATE.reviewRate)               reviewCount\n" +
                "from User U\n" +
                "         left join (select count(userIdx) followcnt, userIdx from Follow where status = 1 group by userIdx) F2\n" +
                "                   on F2.userIdx = U.Idx\n" +
                "         left join (select count(followingIdx) followercnt, followingIdx\n" +
                "                    from Follow\n" +
                "                    where status = 1\n" +
                "                    group by followingIdx) F3 on F3.followingIdx = U.Idx\n" +
                "         left join (select count(userIdx) favCnt, userIdx\n" +
                "                    from Favorite\n" +
                "                    where status = 1\n" +
                "                    group by userIdx) F on U.Idx = F.userIdx\n" +
                "         left join Review R on U.Idx = R.userIdx\n" +
                "\n" +
                "         left join ((select P2.Idx, P2.userIdx, PYR.productIdx, PYR.reviewRate\n" +
                "                     from Product P2\n" +
                "                              join (select PY.productIdx, R.reviewRate\n" +
                "                                    from Payment PY\n" +
                "                                             join Review R on PY.Idx = R.paymentIdx) as PYR\n" +
                "                                   on PYR.productIdx = P2.Idx) as FORRATE) on U.Idx = FORRATE.userIdx\n" +
                "\n" +
                "where U.Idx = ?;";
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

    /**
     * 유저 메인페이지 조회 API 중, 유저의 판매진행상태 별 상품 갯수 조회하는 SQL 처리
     **/
    public List<GetUserProductCountRes> ProductCount(int userIdx, int progress) {
        String UserProductCntResQuery = "select count(*) as count from Product where userIdx = ? and status = 1 and progress = ? ;";
        int UserProductCntParams = userIdx;
        int UserProductCntParams2 = progress;
        return this.jdbcTemplate.query(UserProductCntResQuery,
                (rs, rowNum) -> new GetUserProductCountRes(
                        rs.getInt("count")),
                UserProductCntParams, UserProductCntParams2);

    }


    /**
     * 유저 메인페이지 조회 API 중, 판매진행상태별 유저 상품 상세 내역 조회 SQL 처리
     */
    public List<GetUserProductListRes> ProductDetail(int userIdx, int progress) {
        String UserProductCntResQuery = "select P.Idx Idx, P.productName ProductName, P.price ProductPrice , P.saftyPay SaftyPay, PI.imageUrl ProductImage,\n" +
                "       case\n" +
                "           when 24 >= timestampdiff(HOUR, P.updateAt, current_timestamp)\n" +
                "               then concat(timestampdiff(HOUR, P.updateAt, current_timestamp), '시간 전')\n" +
                "           else concat(timestampdiff(DAY, P.updateAt, current_timestamp), '일 전') end Posteddate\n" +
                "from Product P\n" +
                "left join(select * from ProductImage group by productIdx) PI on P.Idx = PI.productIdx\n" +
                "where userIdx=? and P.status = 1 and progress =?;";
        int UserProductCntParams = userIdx;
        int UserProductCntParams2 = progress;
        return this.jdbcTemplate.query(UserProductCntResQuery,
                (rs, rowNum) -> new GetUserProductListRes(
                        rs.getString("Idx"),
                        rs.getString("ProductName"),
                        rs.getInt("ProductPrice"),
                        rs.getInt("SaftyPay"),
                        rs.getString("ProductImage"),
                        rs.getString("Posteddate")
                ),
                UserProductCntParams, UserProductCntParams2);
    }

    /**
     * 상점명 검색 API 위한 상점명을 검색키워드로 사용한 SQL 문
     */

    public List<GetSearchByUserNameRes> SearchByUserName(String shopName) {
        String GetSearchByUserNameQuery = "select U.Idx userIdx, U.profileImage profileImage , U.shopName shopName, case when followercnt is null then 0 else followercnt end followercnt,case when productCnt is null then 0 else productCnt end  productCnt\n" +
                "from User U\n" +
                "left join (select count(userIdx) followercnt,followingIdx from Follow group by followingIdx)F on U.Idx = F.followingIdx\n" +
                "left join (select count(distinct userIdx) productCnt,userIdx from Product)P on U.Idx=P.userIdx\n" +
                "where U.shopName like concat('%',?,'%')";
        String GetSearchByUserNameParams = shopName;
        return this.jdbcTemplate.query(GetSearchByUserNameQuery,
                (rs, rowNum) -> new GetSearchByUserNameRes(
                        rs.getInt("userIdx"),
                        rs.getString("profileImage"),
                        rs.getString("shopName"),
                        rs.getInt("followercnt"),
                        rs.getInt("productCnt")),
                GetSearchByUserNameParams);
    }


    /**
     * 회원탈퇴 API 위한 , 회원 상태변경 SQL 문
     */
    public int deleteUserInfo(DeleteUserReq deleteUserReq) {
        String deleteUserInfoQuery = "update User set status = 2, closingReason = ? where Idx = ? ";
        Object[] deleteUserInfoParams = new Object[]{deleteUserReq.getClosingReason(), deleteUserReq.getUserIdx()};

        return this.jdbcTemplate.update(deleteUserInfoQuery, deleteUserInfoParams);
    }


    /**
     * 유저 상점설정정보 조회 SQL 처리
     */
    public List<GerUserSettingRes> GerUserSetting(int userIdx) {
        String GerUserSettingQuery = "select shopName, shopAddress , case when avaTimeStart=avaTimeEnd then '24시간'else concat(avaTimeStart,'~',avaTimeEnd)  end avaTime, shopIntro,shopPolicy,preCaution from User where Idx = ?;";
        int userParams = userIdx;
        return this.jdbcTemplate.query(GerUserSettingQuery,
                (rs, rowNum) -> new GerUserSettingRes(
                        rs.getString("shopName"),
                        rs.getString("shopAddress"),
                        rs.getString("avaTime"),
                        rs.getString("shopIntro"),
                        rs.getString("shopPolicy"),
                        rs.getString("preCaution")),
                userParams);

    }

    /**
     * 유저 상점설정정보 수정 SQL 처리
     * */
    public int patchUserSetting(PatchUserSettingReq patchUserSettingReq, int userIdx) {
        String patchUserSettingQuery = "update User set shopName=? , shopAddress =? , avaTimeStart =? , avaTimeEnd = ? , shopIntro = ? , shopPolicy =? , preCaution =? where Idx = ?;";
        Object[] patchUserSettingParams = new Object[]{patchUserSettingReq.getShopName(), patchUserSettingReq.getShopAddress(), patchUserSettingReq.getAvaTimeStart(), patchUserSettingReq.getAvaTimeEnd(), patchUserSettingReq.getShopIntro(), patchUserSettingReq.getShopPolicy(), patchUserSettingReq.getPreCaution(), userIdx};

        return this.jdbcTemplate.update(patchUserSettingQuery, patchUserSettingParams);
    }

}
