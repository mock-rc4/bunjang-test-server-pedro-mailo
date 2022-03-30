package com.example.demo.src.chat;

import com.example.demo.src.address.AddressProvider;
import com.example.demo.src.address.AddressService;
import com.example.demo.src.address.model.PostaddressReq;
import com.example.demo.src.address.model.PostaddressRes;
import com.example.demo.src.chat.model.*;

import static com.example.demo.config.BaseResponseStatus.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/chats")
public class Chatcontroller {


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ChatProvider chatProvider;
    @Autowired
    private final ChatService chatService;
    @Autowired
    private final JwtService jwtService;

    public Chatcontroller(ChatProvider chatProvider, ChatService chatService, JwtService jwtService){
        this.chatProvider = chatProvider;
        this.chatService = chatService;
        this.jwtService = jwtService;
    }

    @ResponseBody
    @PostMapping ("/{userIdx}")
    public BaseResponse<PostChatRes> postChatInfo(@RequestBody PostChatReq postaddressReq,@PathVariable("userIdx") int userIdx){
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostChatRes postChatRes = chatService.postChatInfo(postaddressReq,userIdx);

            return new BaseResponse<>(postChatRes);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
//
}
