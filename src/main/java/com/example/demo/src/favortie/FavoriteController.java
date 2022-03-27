package com.example.demo.src.favortie;


import com.example.demo.src.favortie.model.*;
import com.example.demo.src.favortie.FavoriteProvider;
import com.example.demo.src.favortie.FavoriteService;

import com.example.demo.src.product.model.GetProductDetailRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;


@RestController
@RequestMapping("/app/favs")
public class FavoriteController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final FavoriteProvider favoriteProvider;
    @Autowired
    private final FavoriteService favoriteService;
    @Autowired
    private final JwtService jwtService;

    public FavoriteController(FavoriteProvider favoriteProvider, FavoriteService favoriteService, JwtService jwtService){
        this.favoriteProvider = favoriteProvider;
        this.favoriteService = favoriteService;
        this.jwtService = jwtService;
    }



    @ResponseBody
    @PostMapping ("")
    public BaseResponse<PostFavoriteInfoRes> PostFavoriteInfo(@RequestBody PostFavoriteInfoReq postFavoriteInfoReq){
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(postFavoriteInfoReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostFavoriteInfoRes postFavoriteInfoRes = favoriteService.PostFavoriteInfo(postFavoriteInfoReq);

            return new BaseResponse<>(postFavoriteInfoRes);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping ("")
    public BaseResponse<List<GetUserFavoriteListRes>> getFavoriteInfo(){
        try{
            int userIdx = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인

            List<GetUserFavoriteListRes> getUserFavoriteListRes = favoriteProvider.getFavoriteInfo(userIdx);

            return new BaseResponse<>(getUserFavoriteListRes);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
