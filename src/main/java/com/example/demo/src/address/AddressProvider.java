package com.example.demo.src.address;

import com.example.demo.config.BaseException;

import com.example.demo.src.Follow.model.GetUserAddressRes;
import com.example.demo.src.address.model.PostaddressReq;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class AddressProvider {
    private final AddressDao addressDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AddressProvider(AddressDao addressDao, JwtService jwtService) {
        this.addressDao = addressDao;
        this.jwtService = jwtService;
    }

    public int checkaddress(PostaddressReq postaddressReq) throws BaseException{
        try{
            return addressDao.checkaddress(postaddressReq);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetUserAddressRes> getaddressInfo(int userIdx) throws BaseException{
        try{
            List<GetUserAddressRes> getUserAddressResList = addressDao.getaddressInfo(userIdx);
            return getUserAddressResList;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }
}
