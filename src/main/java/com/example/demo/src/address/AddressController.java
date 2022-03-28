package com.example.demo.src.address;

import com.example.demo.src.Follow.model.GetUserAddressRes;
import com.example.demo.src.Follow.model.PatchaddressRes;
import com.example.demo.src.address.model.*;
import static com.example.demo.config.BaseResponseStatus.*;

import com.example.demo.src.favortie.FavoriteProvider;
import com.example.demo.src.favortie.FavoriteService;
import com.example.demo.src.favortie.model.PostFavoriteInfoReq;
import com.example.demo.src.favortie.model.PostFavoriteInfoRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/address")
public class AddressController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final AddressProvider addressProvider;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final JwtService jwtService;

    public AddressController(AddressProvider addressProvider, AddressService addressService, JwtService jwtService){
        this.addressProvider = addressProvider;
        this.addressService = addressService;
        this.jwtService = jwtService;
    }


    @ResponseBody
    @PostMapping ("")
    public BaseResponse<PostaddressRes> postaddressInfo(@RequestBody PostaddressReq postaddressReq){
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(postaddressReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostaddressRes postaddressRes = addressService.postaddress(postaddressReq);

            return new BaseResponse<>(postaddressRes);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @PatchMapping ("{addressIdx}")
    public BaseResponse<String> patchaddressInfo(@RequestBody PatchAddressReq patchAddressReq, @PathVariable("addressIdx") int addressIdx){
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(patchAddressReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            addressService.patchaddressInfo(patchAddressReq,addressIdx);
            String result = "주소 변경 완료";
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    @ResponseBody
    @PatchMapping ("{addressIdx}/delete")
    public BaseResponse<String> deleteaddressInfo(@PathVariable("addressIdx") int addressIdx){
        try{
            int userIdx = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            addressService.deleteaddressInfo(addressIdx,userIdx);
            String result = "주소 삭제 완료";
            return new BaseResponse<>(result);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping ("")
    public BaseResponse<List<GetUserAddressRes>> getaddressInfo() {
        try{
            int userIdx = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인

            String result = "주소조회 완료";
            List<GetUserAddressRes> getUserAddressResList = addressProvider.getaddressInfo(userIdx);
            return new BaseResponse<>(getUserAddressResList);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
