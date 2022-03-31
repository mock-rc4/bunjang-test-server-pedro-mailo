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



    /**
     * 채팅방 생성 SQL 처리
     * */
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


    /**
     * 탈퇴한 유저 여부 확인 SQL 처리
     * */
    public int checkUserStatus(int userIdx) {
        String checkUserStatusQuery = "select exists(select status from User where Idx = ? and status =1);";
        int checkUserStatusParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserStatusQuery,
                int.class,
                checkUserStatusParams);
    }


    /**
     * 채팅방 생성시 이미 존재하는 채팅방인지 확인 SQL 처리
     *
     * */
    public int checkExistChatroom(int userIdx, int getUserIdx) {
        String checkExistChatroomQuery = "select exists(select * from ChatRoomjoinUser C inner join (select chatRoomIdx from ChatRoomjoinUser where userIdx = ?)CR on CR.chatRoomIdx = C.chatRoomIdx where C.userIdx =? group by CR.chatRoomIdx) ;";
        int userIdxParams = userIdx;
        int getUserIdxParams = getUserIdx;
        return this.jdbcTemplate.queryForObject(checkExistChatroomQuery,
                int.class,
                userIdxParams,getUserIdxParams);

    }


    /**
     * 메세지 전송 SQL 처리
     * */
    public int ChatMessage(int userIdx, int chatRoomIdx,PostChatMessageRep postChatMessageRep) {
        String Chatmessage = "insert into ChatMessage(message,userIdx,chatRoomIdx) values (?,?,?);";
        this.jdbcTemplate.update(Chatmessage,postChatMessageRep.getMessage(),userIdx,chatRoomIdx);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class);

    }


    /**
     * 채팅방 입장후 채팅하는 유저 정보 조회
     * */
    public List<GetUserInfoRes> UserInfo(int userIdx, int chatRoomIdx) {
        String GetUserInfoResQuery = "select C.userIdx,\n" +
                "       U.userShopName,\n" +
                "       U.userProfileImage,\n" +
                "       U.reviewRateCnt,\n" +
                "       U.reviewRateAvg,\n" +
                "       U.producCnt,\n" +
                "       U.procutCompelteCnt\n" +
                "from ChatRoomjoinUser C\n" +
                "         inner join (select U.shopName                                                                     userShopName,\n" +
                "                            U.Idx,\n" +
                "                            U.profileImage                                                                 userProfileImage,\n" +
                "                            FORRATE.reviewRateAvg,\n" +
                "                            FORRATE.reviewRateCnt,\n" +
                "                            case when P.procutCnt is Null then 0 else P.procutCnt end                   as producCnt,\n" +
                "                            case\n" +
                "                                when P2.procutCompelteCnt is Null then 0\n" +
                "                                else P2.procutCompelteCnt end                                           as procutCompelteCnt\n" +
                "                     from User U\n" +
                "                              left join Favorite F on U.Idx = F.userIdx\n" +
                "                              left join Review R on U.Idx = R.userIdx\n" +
                "                              left join (select count(*) procutCnt, userIdx\n" +
                "                                         from Product\n" +
                "                                         where status = 1\n" +
                "                                         group by userIdx) P\n" +
                "                                        on P.userIdx = U.Idx\n" +
                "                              left join (select count(*) procutCompelteCnt, userIdx\n" +
                "                                         from Product\n" +
                "                                         where status = 1\n" +
                "                                           and progress = 3\n" +
                "                                         group by userIdx) P2 on P.userIdx = U.Idx\n" +
                "                              left join (select avg(distinct reviewRate)   reviewRateAvg,\n" +
                "                                                count(distinct reviewRate) reviewRateCnt,\n" +
                "                                                P2.Idx,\n" +
                "                                                P2.userIdx,\n" +
                "                                                PYR.productIdx,\n" +
                "                                                PYR.reviewRate\n" +
                "                                         from Product P2\n" +
                "                                                  join (select PY.productIdx, R.reviewRate\n" +
                "                                                        from Payment PY\n" +
                "                                                                 join Review R on PY.Idx = R.paymentIdx) as PYR\n" +
                "                                                       on PYR.productIdx = P2.Idx) as FORRATE on U.Idx = FORRATE.userIdx\n" +
                ") U on C.userIdx = U.Idx\n" +
                "where C.chatRoomIdx = ?\n" +
                "  and userIdx != ?\n" +
                "group by userIdx;";
        int chatRoomIdxResParams = chatRoomIdx;
        int GetUserInfoResParams = userIdx;

        return this.jdbcTemplate.query(GetUserInfoResQuery,
                (rs, rowNum) -> new GetUserInfoRes(
                        rs.getInt("userIdx"),
                        rs.getString("userShopName"),
                        rs.getString("userProfileImage"),
                        rs.getInt("reviewRateCnt"),
                        rs.getFloat("reviewRateAvg"),
                        rs.getInt("producCnt"),
                        rs.getInt("procutCompelteCnt")),
                GetUserInfoResParams,chatRoomIdxResParams);

    }


    /**
     * 채팅방 메세지 리스트 조회
     *
     * */
    public List<getMessageRes> chatMessagList(int chatRoomIdx) {
        String GetUserInfoResQuery ="select updateAt, message, userIdx, chatRoomIdx\n" +
                "from ChatMessage\n" +
                "where chatRoomIdx = ?\n" +
                "  and status = 1\n" +
                "order By updateAt";

        int chatRoomIdxResParams = chatRoomIdx;


        return this.jdbcTemplate.query(GetUserInfoResQuery,
                (rs, rowNum) -> new getMessageRes(
                        rs.getString("updateAt"),
                        rs.getString("message"),
                        rs.getInt("userIdx"),
                        rs.getInt("chatRoomIdx")),
                chatRoomIdxResParams);
    }
}
