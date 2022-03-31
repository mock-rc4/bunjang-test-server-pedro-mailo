package com.example.demo.src.chat;

import com.example.demo.config.BaseException;

import com.example.demo.src.address.AddressDao;
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
public class ChatProvider {
    private final ChatDao chatDao ;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ChatProvider(ChatDao chatDao, JwtService jwtService) {
        this.chatDao = chatDao;
        this.jwtService = jwtService;
    }

    public int checkUserStatus(int userIdx) throws BaseException{
        try{
            return chatDao.checkUserStatus(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkExistChatroom(int userIdx, int getUserIdx) throws BaseException{

        try{
            return chatDao.checkExistChatroom(userIdx,getUserIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<String> getChatInfo(int userIdx, int chatRoomIdx) {
        List UserInfo = chatDao.UserInfo(userIdx,chatRoomIdx);
        List chatMessagList = chatDao.chatMessagList(chatRoomIdx);
        List resultDetailList = new ArrayList<>(Arrays.asList(UserInfo,chatMessagList));
        return resultDetailList;
    }
    //
}
