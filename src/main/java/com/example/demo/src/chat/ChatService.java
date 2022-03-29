package com.example.demo.src.chat;

import com.example.demo.config.BaseException;
import com.example.demo.src.address.AddressDao;
import com.example.demo.src.address.AddressProvider;
import com.example.demo.src.address.model.PostaddressRes;
import com.example.demo.src.chat.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ChatService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ChatDao chatDao;
    private final ChatProvider chatProvider;
    private final JwtService jwtService;

    @Autowired
    public ChatService(ChatDao chatDao, ChatProvider chatProvider, JwtService jwtService) {
        this.chatDao = chatDao;
        this.chatProvider = chatProvider;
        this.jwtService = jwtService;
    }

    public PostChatRes postChatInfo(PostChatReq postChatReq, int userIdx) {

        //1. 상태값에 따라 나오는 2면 탈퇴한 사용자[userIdx]
        //1. 채팅방 먼저 생성 -> 조인 라인 생성 -> 메시지 생성
        int createChatRommIdx = chatDao.postChatInfo(userIdx,postChatReq);
        return new PostChatRes(userIdx,postChatReq.getUserIdx(),createChatRommIdx);

    }
}
