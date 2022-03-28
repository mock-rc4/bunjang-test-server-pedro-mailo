package com.example.demo.src.address;


import com.example.demo.src.Follow.model.GetFollowerRes;
import com.example.demo.src.Follow.model.GetUserAddressRes;
import com.example.demo.src.Follow.model.PatchaddressRes;
import com.example.demo.src.favortie.model.PostFavoriteInfoRes;
import org.springframework.stereotype.Repository;
import com.example.demo.src.address.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AddressDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


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

    public int patchaddressInfo(PatchAddressReq patchAddressReq, int addressIdx) {
        String patchaddressQuery = "update Address\n" +
                "set name = ? , phoneNumber=? , address=? , addressDesc=? , defaultAddress =?\n" +
                "where Idx = ? and userIdx = ? ";
        Object[] patchaddressParams = new Object[]{patchAddressReq.getName(), patchAddressReq.getPhoneNumber(), patchAddressReq.getAddress(),patchAddressReq.getAddressDesc(),patchAddressReq.getDefaultAddress(),addressIdx,patchAddressReq.getUserIdx()};

        return this.jdbcTemplate.update(patchaddressQuery, patchaddressParams);

    }

    public int deleteaddress(int addressIdx, int userIdx) {
        String patchaddressQuery = "update Address\n" +
                "set status = 2\n" +
                "where Idx = ? and userIdx = ?";
        Object[] patchaddressParams = new Object[]{addressIdx,userIdx};
        return this.jdbcTemplate.update(patchaddressQuery, patchaddressParams);
    }

    public List<GetUserAddressRes> getaddressInfo(int userIdx) {
        String getaddressQuery = "select userIdx,name,phoneNumber,address,addressDesc,defaultAddress from Address where userIdx =? and status =1;";
        int userParams = userIdx;
        return this.jdbcTemplate.query(getaddressQuery,
                (rs, rowNum) -> new GetUserAddressRes(
                        rs.getInt("userIdx"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("addressDesc"),
                        rs.getInt("defaultAddress")),
                userParams);

    }
}

