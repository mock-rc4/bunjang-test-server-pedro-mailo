package com.example.demo.src.Follow;


import com.example.demo.src.Follow.model.*;


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
@RequestMapping("/app/follows")
public class
FollowController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final FollowProvider followProvider;
    @Autowired
    private final FollowService followService;
    @Autowired
    private final JwtService jwtService;


    public FollowController(FollowProvider followProvider, FollowService followService, JwtService jwtService){
        this.followProvider = followProvider;
        this.followService = followService;
        this.jwtService = jwtService;
    }



    /**팔로워 생성, 삭제 API 해당 API Send 할때마다, Follow status를  1, 2 로 변경한다
     * */
    @ResponseBody
    @PostMapping ("")
    public BaseResponse<PostFollowInfoRes> postfollowInfo(@RequestBody PostFollowInfoReq postFollowInfoReq){
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(postFollowInfoReq.getUserIdx() != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostFollowInfoRes postFollowInfoRes = followService.postfollowInfo(postFollowInfoReq);

            return new BaseResponse<>(postFollowInfoRes);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /**
     * 팔로우한 유저정보 조회 API
     * */
    @ResponseBody
    @GetMapping("/follow/{userIdx}") // (GET) 127.0.0.1:9000/app/hotels/:hotelIdx
    public BaseResponse<List<GetfollowRes>> getuserFollowList(@PathVariable("userIdx") int userIdx){
        try{
            int userIdxByJwt = jwtService.getUserIdx();

            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetfollowRes> getUserMainPage = followProvider.getuserFollowList(userIdx);


            return new BaseResponse<>(getUserMainPage);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }




    /**
     * 유저를 팔로잉한 유저들 조회
     * */
    @ResponseBody
    @GetMapping("/followers/{userIdx}") // (GET) 127.0.0.1:9000/app/hotels/:hotelIdx
    public BaseResponse<List<GetFollowerRes>> getuserFollower(@PathVariable("userIdx") int userIdx) {
        try {
            int userIdxByJwt = jwtService.getUserIdx();

            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetFollowerRes> getuserFollower = followProvider.getuserFollower(userIdx);
            return new BaseResponse<>(getuserFollower);
        }
     catch (BaseException exception) {
        return new BaseResponse<>((exception.getStatus()));
    }

    }



}
