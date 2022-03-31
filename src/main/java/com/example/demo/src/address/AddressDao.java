package com.example.demo.src.address;


import com.example.demo.src.address.model.GetUserAddressRes;
import com.example.demo.src.address.model.PatchAddressReq;
import com.example.demo.src.address.model.PostaddressReq;
import com.example.demo.src.address.model.PostaddressRes;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AddressDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    /**
     * 배송지 존재여부 체크 SQL 처리
     * */
    public int checkaddress(PostaddressReq postaddressReq) {
        String checkaddressQuery = "select exists(select Idx from Address where name = ? and phoneNumber=? and address =? and addressDesc=? and userIdx= ?);";
        String checkNameParams = postaddressReq.getName();
        String checkphoneNumberParams = postaddressReq.getPhoneNumber();
        String checkaddressParams = postaddressReq.getAddress();
        String checkaddressDescParams = postaddressReq.getAddressDesc();
        int checkUserIdxParams = postaddressReq.getUserIdx();
        return this.jdbcTemplate.queryForObject(checkaddressQuery,
                int.class,
                checkNameParams, checkphoneNumberParams, checkaddressParams, checkaddressDescParams, checkUserIdxParams);
    }


    /**
     * 유저가 입력한 배송지 정보 Addess 테이블에 조회후 출력 하는 SQL 처리
     * */
    public PostaddressRes getaddress(PostaddressReq postaddressReq) {
        String getaddressQuery = "select name,phoneNumber,address,addressDesc,userIdx,status from Address where name = ? and phoneNumber=? and address =? and addressDesc=? and userIdx= ?";
        String checkNameParams = postaddressReq.getName();
        String checkphoneNumberParams = postaddressReq.getPhoneNumber();
        String checkaddressParams = postaddressReq.getAddress();
        String checkaddressDescParams = postaddressReq.getAddressDesc();
        int checkUserIdxParams = postaddressReq.getUserIdx();
        return this.jdbcTemplate.queryForObject(getaddressQuery,
                (rs, rowNum) -> new PostaddressRes(
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("addressDesc"),
                        rs.getInt("userIdx"),
                        rs.getInt("status")),
                checkNameParams, checkphoneNumberParams, checkaddressParams, checkaddressDescParams, checkUserIdxParams);
    }


    /**
     * 기존에 삭제한 유저 주소를 다시 생성할경우 , 상태값 활성화한다.
     * */

    public int statusChangeAddress(PostaddressReq postaddressReq) {

        String statusChangeAddressQuery = "update Address set status = 1 where name = ? and phoneNumber=? and address =? and addressDesc=? and userIdx= ?";
        String checkNameParams = postaddressReq.getName();
        String checkphoneNumberParams = postaddressReq.getPhoneNumber();
        String checkaddressParams = postaddressReq.getAddress();
        String checkaddressDescParams = postaddressReq.getAddressDesc();
        int checkUserIdxParams = postaddressReq.getUserIdx();
        Object[] statusChangeAddressParams = new Object[]{checkNameParams,checkphoneNumberParams,checkaddressParams,checkaddressDescParams,checkUserIdxParams};
        return this.jdbcTemplate.update(statusChangeAddressQuery, statusChangeAddressParams);


    }


    /**
     *
     * 배송지 생성 SQL 처리
     * */
    public int createAddress(PostaddressReq postaddressReq) {
        String createAddressQuery ="insert into Address (name,phoneNumber,address,addressDesc,defaultAddress,userIdx) values (?,?,?,?,?,?)";
        String checkNameParams = postaddressReq.getName();
        String checkphoneNumberParams = postaddressReq.getPhoneNumber();
        String checkaddressParams = postaddressReq.getAddress();
        String checkaddressDescParams = postaddressReq.getAddressDesc();
        int checkDefaultAddress = postaddressReq.getDefaultAddress();
        int checkUserIdxParams = postaddressReq.getUserIdx();
        Object[] createAddressParams = new Object[]{checkNameParams,checkphoneNumberParams,checkaddressParams,checkaddressDescParams,checkDefaultAddress,checkUserIdxParams};
        return this.jdbcTemplate.update(createAddressQuery, createAddressParams);
    }


    /**
     * 배송지 정보 수정 SQL 처리
     * */
    public int patchaddressInfo(PatchAddressReq patchAddressReq, int addressIdx) {
        String patchaddressQuery = "update Address\n" +
                "set name = ? , phoneNumber=? , address=? , addressDesc=? , defaultAddress =?\n" +
                "where Idx = ? and userIdx = ? ";
        Object[] patchaddressParams = new Object[]{patchAddressReq.getName(), patchAddressReq.getPhoneNumber(), patchAddressReq.getAddress(),patchAddressReq.getAddressDesc(),patchAddressReq.getDefaultAddress(),addressIdx,patchAddressReq.getUserIdx()};

        return this.jdbcTemplate.update(patchaddressQuery, patchaddressParams);

    }

    /**
     * 배송지 정보 삭제 SQL 처리
     * */
    public int deleteaddress(int addressIdx, int userIdx) {
        String patchaddressQuery = "update Address\n" +
                "set status = 2\n" +
                "where Idx = ? and userIdx = ?";
        Object[] patchaddressParams = new Object[]{addressIdx,userIdx};
        return this.jdbcTemplate.update(patchaddressQuery, patchaddressParams);
    }


    /**
     * 유저가 등록한 주소지 조회 SQL 처리
     * */
    public List<GetUserAddressRes> getaddressInfo(int userIdx) {
        String getaddressQuery = "select userIdx,Idx,name,phoneNumber,address,addressDesc,defaultAddress from Address where userIdx =? and status =1;";
        int userParams = userIdx;
        return this.jdbcTemplate.query(getaddressQuery,
                (rs, rowNum) -> new GetUserAddressRes(
                        rs.getInt("userIdx"),
                        rs.getInt("Idx"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("addressDesc"),
                        rs.getInt("defaultAddress")),
                userParams);

    }
}

