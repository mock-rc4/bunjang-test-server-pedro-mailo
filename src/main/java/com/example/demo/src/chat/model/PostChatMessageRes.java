package com.example.demo.src.chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostChatMessageRes {

    private int userIdx;
    private int chatRoomIdx;
    private int messageIdx;
    private String message;
}
