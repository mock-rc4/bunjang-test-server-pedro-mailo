package com.example.demo.src.chat;

import com.example.demo.src.chat.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ChatDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int postChatInfo(int userIdx, PostChatReq postChatReq) {
        //1. 채팅방 먼저 생성 1개만 생성 -> 조인 라인 생성 , 나 상대방 각각 2개 쿼리문 생성-> 메시지 생성 1개만
        String CreateChatRoom = "insert into ChatRoom values ()";
        this.jdbcTemplate.update(CreateChatRoom);
        String lastInserIdQuery = "select last_insert_id()";
        int chatIdx = this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
        // 채팅 생성 끝

        // 2. 채팅 참여하는 사람들 생성
        String ChatRoomjoin = "insert into ChatRoomjoinUser(userIdx, chatRoomIdx)  values (?,?);";
        this.jdbcTemplate.update(ChatRoomjoin,userIdx,chatIdx);
        this.jdbcTemplate.update(ChatRoomjoin,postChatReq.getUserIdx(),chatIdx);
        // 채팅 참여하는 사람들 종료

        //3. 채팅 메세지 생성
        String Chatmessage = "insert into ChatMessage(message,userIdx,chatRoomIdx) values (?,?,?);";
        this.jdbcTemplate.update(Chatmessage,postChatReq.getMessage(),userIdx,chatIdx);

        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);
    }

    public int checkUserStatus(int userIdx) {
        String checkUserStatusQuery = "select exists(select status from User where Idx = ? and status =1);";
        int checkUserStatusParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserStatusQuery,
                int.class,
                checkUserStatusParams);
    }

    public int checkExistChatroom(int userIdx, int getUserIdx) {
        String checkExistChatroomQuery = "select exists(select * from ChatRoomjoinUser C inner join (select chatRoomIdx from ChatRoomjoinUser where userIdx = ?)CR on CR.chatRoomIdx = C.chatRoomIdx where C.userIdx =? group by CR.chatRoomIdx) ;";
        int userIdxParams = userIdx;
        int getUserIdxParams = getUserIdx;
        return this.jdbcTemplate.queryForObject(checkExistChatroomQuery,
                int.class,
                userIdxParams,getUserIdxParams);

    }

    public int ChatMessage(int userIdx, int k, String message) {
        String Chatmessage = "insert into ChatMessage(message,userIdx,chatRoomIdx) values (?,?,?);";
        this.jdbcTemplate.update(Chatmessage,message,userIdx,k);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);

    }
}
