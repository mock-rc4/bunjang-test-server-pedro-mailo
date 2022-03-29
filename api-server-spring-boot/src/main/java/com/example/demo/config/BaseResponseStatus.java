package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    EMPTY_REQUEST(false, 2004, "키워드를 입력하지 않았습니다"),      // milo 추가함 -> 검색시 키워드 아무것도 안넣었을때 c

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_NAME(false, 2015, "이름을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    POST_USERS_EMPTY_PHONE(false, 2018, "핸드폰번호를 입력해주세요."),      //pedro
    POST_USERS_EMPTY_BIRTH(false, 2019, "생년월일 입력해주세요."),            //pedro
    POST_USERS_EMPTY_USERPWD(false, 2020, "인증번호 6 자리를 입력해주세요."),    //pedro
    POST_USERS_EXISTS_PHONE(false,2021,"이미 등록된 회원 정보입니다."),         //pedro
    POST_USERS_INVALID_USERNAME(false,2022,"등록된 이름과 일치하지 않습니다."),       //pedro
    POST_USERS_INVALID_PHONENUMBER(false,2023,"등록되지 않은 핸드폰번호입니다."),     //pedro
    POST_USERS_INVALID_USERBIRTH(false,2024,"등록된 생년월일과 일치하지 않습니다."),    //pedro



    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),
    EMPTY_RESPONSE(false, 3001, "입력한 키워드에 대한 검색결과가 없습니다."), // 마일로 추가함

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),


    LOGIN_USERS_NOT_JOIN(false,3015 , "로그인실패"),      // 페드로 추가


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),


    // 5000 : 필요시 만들어서 쓰세요
    NOT_MY_REVIEW(false, 5001, "본인이 작성한 리뷰가 아닙니다.");
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
