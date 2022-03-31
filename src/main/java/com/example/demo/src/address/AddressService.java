package com.example.demo.src.address;

import com.example.demo.config.BaseException;
import com.example.demo.src.address.model.PatchAddressReq;
import com.example.demo.src.address.model.PostaddressReq;
import com.example.demo.src.address.model.PostaddressRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class AddressService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AddressDao addressDao;
    private final AddressProvider addressProvider;
    private final JwtService jwtService;

    @Autowired
    public AddressService(AddressDao addressDao, AddressProvider addressProvider, JwtService jwtService) {
        this.addressDao = addressDao;
        this.addressProvider = addressProvider;
        this.jwtService = jwtService;

    }


    /**
     * 배송지 등록시 기존에 동일한 주소지 등록했는지 여부 확인후, 맞을경우 삭제처리된 주소지를 다시 활성화한다
     * */
    public PostaddressRes postaddress(PostaddressReq postaddressReq) throws BaseException {

        if (addressProvider.checkaddress(postaddressReq) == 1) {
            PostaddressRes getaddressInfo = addressDao.getaddress(postaddressReq);
            if (getaddressInfo.getStatus() == 2) {
                addressDao.statusChangeAddress(postaddressReq);
            }
            if (getaddressInfo.getStatus() == 1) {
                // 이미등록된 정보입니다
                throw new BaseException(POST_USERS_EXISTS_PHONE);
            }
        }
        else {
            addressDao.createAddress(postaddressReq);
        }
        PostaddressRes getaddressInfo = addressDao.getaddress(postaddressReq);
        return new PostaddressRes(getaddressInfo.getName(), getaddressInfo.getPhoneNumber(), getaddressInfo.getAddress(), getaddressInfo.getAddressDesc(), getaddressInfo.getUserIdx(), getaddressInfo.getStatus());

    }


    /**
     * 유저 배송지 정보 수정
     * */
    public void patchaddressInfo(PatchAddressReq patchAddressReq,int addressIdx) throws BaseException {
        int j =  addressDao.patchaddressInfo(patchAddressReq,addressIdx);
    }


    /**
     *
     * 유저 배송지 삭제
     * */
    public void deleteaddressInfo(int addressIdx,int userIdx) throws BaseException {

        int j = addressDao.deleteaddress(addressIdx,userIdx);
    }
}
