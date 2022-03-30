package com.example.demo.src.chat;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.address.AddressDao;
import com.example.demo.src.address.AddressProvider;
import com.example.demo.src.address.model.PostaddressRes;
import com.example.demo.src.chat.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public PostChatRes postChatInfo(PostChatReq postChatReq, int userIdx) throws BaseException {

        //1. 상태값에 따라 나오는 2면 탈퇴한 사용자 한테 보낸다고 하면 , 탈퇴한 사용자 입니다! , 체크 검증 해서 나오는지
        if (chatProvider.checkUserStatus(postChatReq.getUserIdx()) == 0) {
            throw new BaseException(POST_USERS_INVALID_USER_STATUS);
        }


        // 2. 기존에 있는 채팅방이 있는지 확인 없는 경우 값 처리 해야한다.
        int createChatRommIdx;
        if (chatProvider.checkExistChatroom(userIdx, postChatReq.getUserIdx()) == 0) {
            createChatRommIdx = chatDao.postChatInfo(userIdx, postChatReq);
            return new PostChatRes(userIdx, postChatReq.getUserIdx(), createChatRommIdx);
        }
        // 2. 기존에 있는 채팅방이 있는지 확인 있는  경우
        else {
            throw new BaseException(POST_USERS_EXIST_CHATROOM);
            //
        }
        //1. 채팅방 먼저 생성 -> 조인 라인 생성 -> 메시지 생성

    }


    public PostChatMessageRes postMessage(int userIdx, int chatRoomIdx, PostChatMessageRep postChatMessageRep) throws BaseException {
        int chatMessageIdx = chatDao.ChatMessage(userIdx,chatRoomIdx,postChatMessageRep);
        return new PostChatMessageRes(userIdx,chatRoomIdx,chatMessageIdx,postChatMessageRep.getMessage());
    }


}
