package com.example.demo.src.chat.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class getMessageRes {
    private String updateAt;
    private String message;
    private int userIdx;
    private int chatRoomIdx;
}
