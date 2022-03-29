package com.example.demo.src.chat;

import com.example.demo.config.BaseException;

import com.example.demo.src.address.AddressDao;
import com.example.demo.src.chat.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


  //
}
