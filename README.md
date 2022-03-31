# Bunjang Clone coding Project 
> ## [🙈 기획서](https://docs.google.com/document/d/1mJEql5gy8jLTYZXEtAzuZwmRtznI321b/edit)
> ## [📫 API Sheet](https://docs.google.com/spreadsheets/d/1saKFspgb7g0NZVLX445RVXS27s1UKaY5/edit#gid=990061567)    
> ## [🧩 ERD](https://aquerytool.com/aquerymain/index/?rurl=f5891c32-395a-4960-8e52-5380280e35ef&)
>> password : 42g555

<br /> 


## 📌 1주차 목표 작업 범위 (2022-03-19 ~ 2022-03-25) 
- [x] ERD 설계
- [x] EC2 환경구축
- [x] API Sheet 리스트업
- [x] 유저 회원가입 API 제작
- [x] 유저 로그인 API 제작
- [x] 유저 마이페이지 API 제작 
- [x] 유저 정보(성별, 생년월일, 핸드폰 번호,) 변경 API
<br />  

## 📌 2주차 목표 작업 범위 (2022-03-26 ~ 2022-04-01)
- [x] 유저 찜 등록/삭제 API 제작
- [x] 유저 찜 목록 조회 API 제작 
- [x] 유저 팔로우 등록/삭제 API 제작
- [x] 유저 팔로우, 팔로워 조회 API 제작
- [x] 유저 배송지 등록, 삭제 , 조회 3개 API 제작  
- [x] 유저 회원탈퇴 API 제작
- [x] 유저 상점 설정정보 조회, 수정 2개 API 제작
- [x] 번개톡 채팅방생성, 채팅방 입장 조회, 메세지 전송 3개 API 제작
- [x] 카카오 로그인   

<br /> 

## 📝개발일지 (2022-03-19 ~ 2022-04-01)
<details> 
<summary> 2022.3.19 (SAT) - 프로젝트 시작 </summary>
<div markdown="1">
 
 > 
  - 기획서 작성
  - EC2 서버 구축
  - RDS 구축
  - dev(localhost)/prod 폴더 나누어서 서브 도메인 적용
  - 마일로와 ERD 설계
 
</div>
</details>


<details> 
<summary> 2022.3.20 (SUN) </summary>
<div markdown="1">
 
 > 
  - 마일로와 ERD 설계
  - API 명세서 작성(리스트만 작성)
  - [로컬] 회원가입 API 작성(50%)
</div>
</details>

<details> 
<summary> 2022.3.21 (MON) </summary>
<div markdown="1">
 
 > 
  - [로컬] 회원가입 API (100%)
  - [로컬] 로그인 API (100%) 
  - 회원가입 API 서버 반영
  - 로그인 API 서버 반영 
</div>
</details>

<details> 
<summary> 2022.3.22 (TUE) - 1차 피드백</summary>
<div markdown="1">
 
 > 
  - API 명세서 리스트 작성 
  - validation 처리 (회원가입, 로그인 API 관련 처리)
  - 개발팀장(코롱) 피드백
  - API 명세서 작성 (완성된 API 업데이트)

</div>
</details>

<details> 
<summary> 2022.3.23 (WEN) </summary>
<div markdown="1">
 
 > 
  - 마일로가 작성한 API 서버에 반영 (상품검색어 기준 조회, 상품카테고리 기준 조회)
  - [로컬] MyPage API (100%)
  - 회원 MyPage API 서버 반영
  - API 명세서 작성 (완성된 API 업데이트)
  - 서버 인스턴스 에러 발생 확인후 조치 완료
</div>
</details>

<details> 
<summary> 2022.3.24 (THU) </summary>
<div markdown="1">
 
 > 
  - 마일로가 작성한 API 서버에 반영 (상품상세조회, 메인페이지 API)
  - [로컬] 회원 성별 API (100%)
  - [로컬] 핸드폰번호 API (100%)
  - [로컬] 생년월일 수정 API (100%)
</div>
</details>

<details> 
<summary> 2022.3.25 (FRI) </summary>
<div markdown="1">
 
 
 > 
  - API 명세서 작성 (완성된 API 업데이트)
  - 더미데이터 생성 작업
  - 회원 정보(성별, 생년월일, 핸드폰번호) 수정 API 서버에 반영
</div>
</details>
 
<details> 
<summary> 2022.3.26 (SAT) </summary>
<div markdown="1">
 
 
 > 
  - API 명세서 작성 (완성된 API 업데이트)
  - 유저 찜 등록/삭제 API 작업 (50%)
  - 팔로우 생성/ 삭제 API 작업 (50%)
</div>
</details>
 
<details> 
<summary> 2022.3.27 (SUN) </summary>
<div markdown="1">
 
 > 
  - 마일로가 작성한 API 서버에 반영 (결제 관련 API)
  - [로컬] 회원 팔로우 등록 및 삭제 API (100%)
  - 회원 팔로우 등록 및 삭제 API 서버 반영 
  - API 명세서 작성 (완성된 API 업데이트)
  - API 명세서 정리 작업
</div>
</details>

<details> 
<summary> 2022.3.28 (MON) </summary>
<div markdown="1">
 
 > 
  - [로컬] 배송지 주소 등록 API (100%)
  - [로컬] 배송지 주소 조회 API (100%)
  - [로컬] 배송지 주소 삭제 API (100%)
  - [로컬] 유저 삭제 API (100%)  
  - 배송지 주소 관련 API 및 유저 삭제 API 서버 반영
  - API 명세서 작성 (완성된 API 업데이트)
  - 상품 더미데이터 생성 작업
</div>
</details>

<details> 
<summary> 2022.3.29 (TUE) </summary>
<div markdown="1">
 
 > 
  - [로컬] 팔로워 API (35%)
  - [로컬] 번개톡 API (10%) 
  - API 명세서 정리
  - 번개톡 관련 샘플데이터 생성
</div>
</details>

<details> 
<summary> 2022.3.30 (WEN) - 2차 피드백 </summary>
<div markdown="1">
 
 > 
  - [로컬] 번개톡 채팅방 생성 API 작성 완료 (100%)
  - 번개톡 채팅방 생성 API 서버 반영
  - API 명세서 작성 (완성된 API 업데이트)
  - 쿼리문 수정 (유저 메인 페이지, 번개톡 채팅방 불러오기)
  - 마일로 작성 API 서버반영 (후기 API)
 
 > 2차 피드백 내용 
  - GIT 을 사용하는방법을 굉장히 못했다.
  - 현재 사용하는 방법 말고 다른 방법을 이용해 코드를 보안해라
  1. Try - Catch 문을 지양하자 
  2. 어노테이션을 활용하라 
  3. DAO대신 마이바티스와 JPA 를 사용하여 sql 문은 처리하자

</div>
</details>


<details> 
<summary> 2022.3.31 (THU)  </summary>
<div markdown="1">
 
 > 
  - 팔로우 조회 API 작성 완료 (100%)
  - 카카오 API 작성 완료(100%)
  - 팔로우 조회, 카카오 API 서버에 반영
  - 코드 구성도 작성
  - 소스코드 주석 작성
  
</div>
</details>


<br /> 

## ⚠ Issue
### 1. 인증번호 구현의 어려움 (22-03-20)
- 문제: 인증번호 구현의 난이도가 어렵다고 판단하여 인증번호를 패스워드로 변경 구현하는 방식을 서버 멘토에게 제안
- 해결: 인증번호를 패스워드로 변경 구현하는 방식으로 진행

### 2. 회원가입 API 오류 (22-03-21)
- 문제 : 회원가입 API 생성후 postman 으로 테스트 할시 데이터베이스 오류나는것확인, 개발팀장과 확인결과, 유저 테이블에 패스워드 테이블의 데이터 타입을 VARCHAR(45) 로 설정하여 나오는 오류 확인, 사전에 패스워드를 설정시 암호화 변환되어 등록되는데,  VARCHAR(45)가 암호화된 패스워드 값을 받아내기에 너무 짧은것으로 확인 
- 해결 : 유저 테이블의 패스워드 칼럼의 데이터 타입을 varchar(45) -> text 로 변경후 정상적으로 나오는것 확인

### 3. 인스턴스 오류 (22-03-23)
- 문제 : 서버 가동중 3/23 새벽 2시경 EC2 인스턴스 에러 나는것 확인 시스템은 정상이었지만, 통신상 오류가 나는 상황 확인.
- 해결 : 인스턴스 재부팅 후 정상으로 되는것 확인 완료

### 4. API 설정 여부
- 문제 : 기존 찜/ 팔로우 생성과 삭제 전부 동일한 상황에서 선택되는것인데, 따로 API 구성을 하는게 맞는지 고민

- 해결 : 각 찜 / 팔로우 테이블에 Status 를 1 : 활성화 , 2 : 비활성화 기준으로 두어서, 

### 5. 찜 API 오류 (22-03-26)
- 문제 : 찜 비활성화 하는 과정에서 코드 에러가 나는것 확인
``` JAVA
in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2] with root cause]- Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2] with root cause
org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2
```
- 해결 : DAO에서 Body 에 작성한 내용을 파라미터로 받고 Favorite 테이블에 데이터 조회해 데이터 유무를 파악하는데, status = 1 처럼 특정 조건으로 지정해서 쿼리문 오류 나는것 확인


### 6. 팔로워 조회 API (22-03-28)
- 문제 : 팔로워 조회 API 생성도중 , 기존에 작성한 List 형태가 [팔로잉한 유저 정보] [팔로잉한 유저가 올린 상품 리스트] 형태로 리스트 나와야하는데 분리를 못하는 상황 생김
 
- 시도 : List로 followingIdx 를 받아서 매개변수로 받아야하는데 , List 형태로 받은 값들이 원하는 형태로 안나오는 것 확인 , List 형태가 int 형태인 followingIdx 를 담은 json 형태로 안나오는것으로 확인
 
- 해결 : followingIdx 를 받은 List의 Length 를 for문으로 각각 followingIdx를 queryforobject 형태로 추출하여 매개변수로 사용하여 API 생성 완료 

### 7. 파싱 오류 (22-03-30)
- 문제 : 메세지 전송 API 테스트중 파싱 에러 나는 것 확인
 ``` JAVA
 04:31:14.359 WARN  [File:AbstractHandlerExceptionResolver.java] [Func:logException] [Line:207] [Message:Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot construct instance of `com.example.demo.src.chat.model.PostChatMessageRep` (although at least one Creator exists): cannot deserialize from Object value (no delegate- or property-based Creator); nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot construct instance of `com.example.demo.src.chat.model.PostChatMessageRep` (although at least one Creator exists): cannot deserialize from Object value (no delegate- or property-based Creator)

 ```
- 해결 : Req 값으로 파라미터를 메세지만 받아서 생기는 결과.. 임의로 매개변수 하나 추가 해서 테스트 결과 나오는걸로 확인

<<<<<<< HEAD
<br />
=======

<br />
=======

### 8. GET 오류 (22-03-30)
- 문제 : 채팅방 입장및 조회 하는 API 테스트 중 에러 나오는 것 확인
``` JAVA 
15:03:06.699 WARN  [File:AbstractHandlerExceptionResolver.java] [Func:logException] [Line:207] [Message:Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'GET' not supported]]- Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'GET' not supported]

```
- 해결 : 매핑하는 URL 잘못 기재하여 생긴 오류 , URL 수정후 정상적으로 나오는것 확인
>>>>>>> pedro


## 🚀 코드분석(주요 폴더및 파일로 분석)
``` text
bunjang-test-server-pedro-mailo 

 > gradle
 > .idea // 인텔리제이의 프로젝트별 설정 파일이 포함되어있는 폴더. 해당 폴더에는 VCS 매핑 및 실행 및 디버그 구성과 같은 프로젝트 세부정보, 탐색 기록, 현재 선택된 구성과 같은 사용자별 세부 정보를 포함한다
  | .gitignore // git 버전 관리에서 제외할 파일 목록을 지정하는 파일(JWT 비밀키, 등등 보안에 신경써야하는 정보)
 > gradle // 빌드 자동화 위한 폴더
  | gradle-wrapper.jar // Wrapper 파일, 실행 스크립트가 동작하면 Wrapper에 맞는 환경을 로컬 캐시에 다운로드 받은 뒤에 실제 명령에 해당하는 task를 실행. 
  | gradle-wrapper.properties // 해당 프로젝트에 사용할 Gradle 버전의 상세 내용이 포함되어있다. 
 > logs // 로그 파일??? 
  | app.log // warn, error 레벨에 해당하는 로그가 작성 되는 파일
  | app-%d{yyyy-MM-dd}.%i.gz
  | error.log // error 레벨에 해당하는 로그가 작성 되는 파일
  | error-%d{yyyy-MM-dd}.%i.gz
 build.gradle // gradle 빌드시에 필요한 dependency 설정하는 곳
 gradlew // gradle wrapper 줄임말 , 새로운 환경에서도 gradle을 설치하지 않아도 빌드 할 수있게 해주는 역할
 
 > src // 해당 구조는 페드로가 작성한 파일 우선순위로 작성하였습니다. 
  > java
   | DemoApplication // SpringBootApplication 서버 시작 지점
   > config
    | BaseException.java // Controller, Service, Provider 에서 Response 용으로 공통적으로 사용 될 익셉션 클래스
    | BaseResponse.java // Controller 에서 Response 용으로 공통적으로 사용되는 구조를 위한 모델 클래스
    | BaseResponseStatus.java // Controller, Service, Provider 에서 사용 할 Response Status 관리 클래스 
    | Constant.java // 공통적으로 사용될 상수 값들을 관리하는 곳
    > secret
     | Secret.java // 시크릿 키 값들이 작성되어있는 파일
   > utils
    | AES128.java // 암호화 관련 클래스
    | JwtService.java // jwt 관련 클래스
    | ValidateRegex.java // 정규표현식 관련 클래스
   > src(각 폴더의 controller, service, provider, Dao 는 이하 전부 동일한 이름으로 작성하였습니다. ) 
    > address
     > model
      | GetUserAddressRes.java // 유저 배송지 리스트 Response 클래스 
      | PatchAddressReq.java // 유저 배송지 수정 Request 클래스 
      | PatchAddressRes.java // 유저 배송지 수정 Response 클래스 
      | PostaddressReq.java // 유저 배송지 등록 Request 클래스 
      | PostaddressRes.java // 유저 배송지 등록 Response 클래스
     | Controller // 코드 주석 참고
     | Service // 코드 주석 참고
     | Provider // 코드 주석 참고
     | Dao // 코드 주석 참고
    > chat
     > model
      | GetChatInfoRes.java //특정 채팅방 정보 불러오는 Response 클래스
      | getMessageRes.java // 채팅방 메시지 정보 조회 Response 클래스 
      | PostChatMessageReq.java // 채팅방 메시지 전송 Request 클래스 
      | PostChatMessageRes.java // 채팅방 메시지 전송 Response 클래스
      | PostChatReq.java // 채팅방 생성 Respuest 클래스 
      | PostChatRes.java // 채팅방 생성 Ruponse 클래스 
     | Controller // 코드 주석 참고
     | Service // 코드 주석 참고
     | Provider // 코드 주석 참고
     | Dao // 코드 주석 참고
    > faovire
     > model
      | GetUserFavoriteListRes.java // 유저 찜 목록 조회 Response 클래스
      | PostFavoriteInfoReq.java // 유저 찜 등록/삭제 resquest 클래스 
      | PostFavoriteInfoRes.java // 유저 찜 등록/삭제 response 클래스
     | Controller // 코드 주석 참고
     | Service // 코드 주석 참고
     | Provider // 코드 주석 참고
     | Dao // 코드 주석 참고
    > Follow
     > model
      | FollointIdxRes.java // 팔로우한 사람들 리스트 수정 요망
      | GetfollowDescRes.java // 팔로우 한 사람들 상세 정보 Response 클래스
      | GetFollowerRes.java // 팔로워 리스트 정보 조회 Response 클래스 
      | GetfollowRes.java // 팔로워 정보 조회 response 클래스
      | PostFollowInfoReq // 팔로워 등록/삭제여부 resquest 클래스
      | PostFollowInfoRes // 팔로워 등록/삭제여부 response 클래스
     | Controller // 코드 주석 참고
     | Service // 코드 주석 참고
     | Provider // 코드 주석 참고
     | Dao // 코드 주석 참고
    > user
     > model
      | DeleteUserReq.java // 유저 탈퇴(삭제는 안하고 status 값만 변경) Request 클래스
      | GerUserSettingRes.java // 유저 상점정보 조회 Response 클래스
      | GetSearchByUserNameRes.java // 상점명 키워드로하여 상점 조회 Response 클래스
      | GetUserInfoRes.java // 유저 메인페이지 유저기본정보 조회 Response 클래스
      | GetUserProductCountRes.java // 유저가 등록한 판매상품 갯수 조회하는 Response 클래스
      | GetUserProductListRes.java  // 유저가 등록한 판매상품 리스트 조회하는 Response 클래스
      | PatchShopNameReq.java // 유저 상점명 변경 Request 클래스
      | PatchUserBirthReq.java // 유저 생년월일 변경 Request 클래스
      | PatchUserPhoneReq.java // 유저 핸드폰 번호 변경 Request 클래스
      | PatchUserSettingReq.java // 유저 상점정보 설정 Request 클래스
      | PatchUserSexReq.java // 유저 성별정보 변경 Request 클래스
      | PostLoginReq.java // 유저 로그인 Request 클래스
      | PostLoginRes.java // 유저 로그인 Response 클래스
      | PostUserReq.java // 유저 회원가입 Request 클래스 
      | PostUserRes.java // 유저 회원가입 Response 클래스
      | User.java // 유저 정보 클래스
     | Controller // 코드 주석 참고
     | Service // 코드 주석 참고
     | Provider // 코드 주석 참고
     | Dao // 코드 주석 참고
    > home
    > product
    > review
    > test
  > resource
   | application.yml // Database 연동을 위한 설정 값 세팅 및 Port 정의 파일
   | logback-spring.xml // logger 사용시 console, file 설정 값 정의 파일
   
 
 
 
```


## 참고폴더
1. [idea폴더](https://rider-support.jetbrains.com/hc/en-us/articles/207097529-What-is-the-idea-folder-)
2. [gitignore](https://devlog-wjdrbs96.tistory.com/237)
3. [gradle](https://www.jetbrains.com/idea/guide/tutorials/working-with-gradle/gradle-wrapper/)





# 마일로 파트

## 2022-03-19 진행상황
+ EC2 서버 구축
+ ERD 설계

  전체적인 설계 완료, 직거래 주소 반영 여부는 팀원들과의 조율이 필요해 주소 반영과 미반영 두 가지의 product table을 만들어 언제든지 변경 가능하도록 대비함.

  <details>
  <summary>ERD설계 이미지</summary>
  <div markdown="1">
    
  + ![화면 캡처 2022-03-20 011923](https://user-images.githubusercontent.com/77482065/159129383-35773f7b-8040-4611-a6fb-43622614faf2.png)
  </div>
  </details>
  
  + https://aquerytool.com/aquerymain/index/?rurl=4f34fb57-579b-4e4b-8586-7aac376aeb59&
  + Password : pbcwsf
  
## 2022-03-20 진행상황
+ ERD 설계 -> 추가 수정사항
  + product와 product_copy 테이블 createAt에  AI체크 해제
  + SubCategory 테이블 categoryName 타입 누락 -> 추가완료
  + Product 테이블 directtrans 타입 누락 -> 추가 완료
  + reviewImage 테이블 imageUrl 타입 varchar(45)에서 text로 변경
  + Address 테이블의 createAt, updateAt 디폴트값 누락 -> DataGrip에서 변경이 안되는 에러 발생 -> 우선은 배송지 관련 더미데이터 생성 생략
+ 제작할 API리스트업, 명세서 작성 시작
  + API 명세서: https://docs.google.com/spreadsheets/d/1saKFspgb7g0NZVLX445RVXS27s1UKaY5/edit#gid=990061567
+ RDS에 ERD설계한 DB 생성하고 DataGrip 연결 -> 더미 데이터 생성 시작

## 2022-03-21 진행상황
+ ERD 설계 -> 추가 수정사항
  +  userPwd int-> text로 변경
+ API 생성 -> 검색어에 따른 상품명 조회

+ 1차 피드백
  + ERD 설계 부분
    + status의 type이 너무 크다, 작은걸로 바꿔보자 -> tinyint로 변경
    + ProductQuestion의 questionDesc type이 varchar(45) 너무 작다 -> 질의 내용 100자 이내에 맞게 varchar(100)으로 변경
    + 서브 카테고리가 있다고 해서 카테고리 테이블을 굳이 3개로 나눌 필요가 없다. 트리구조를 생각하자 -> parentIdx를 이용한 Category 테이블 하나로 병합
    + user 테이블의 avaTime을 시작시간과 끝시간 나눠서 저장하자 -> avaTimeStart와 avaTimeEnd 둘로 나눔
    + 번개톡에 관한 테이블도 일단 만들어놓자 -> ChatMessage, ChatRoom, ChatRoomjoinUser 테이블 추가 생성
    + 상품 상세조회 페이지에 나오는 게시물 조회수는 서버에서 만들어야 한다 -> 어떤 user가 어떤 product를 조회했는지 기록하는 Views 테이블 추가
  + API 부분
    + user의 회원정보 수정/삭제를 제외하고 나머지 요소들은 수정과 삭제를 하나로 묶어서 진행
    + 상품 상세조회의 게시물 조회수 서버에서 작성 -> ERD에서 테이블 추가
    + 찜 여부 확인은 jwt토큰을 헤더에 받아와서 쓰면 서버에서 쿼리 만들 수 있다 

  피드백 후 ERD 설계 전체적으로 개편 
  + URL : https://aquerytool.com/aquerymain/index/?rurl=f5891c32-395a-4960-8e52-5380280e35ef&
  + Password : 42g555
  
  API는 피드백 받은 내용 참고해 작성해나갈 예정
   
## 2022-03-22 진행상황
  + ERD 설계 -> 추가 수정사항
    +  Payment 테이블의 sellerIdx를 buyerIdx로 변경
    +  광고에 대한 정보를 저장하는 AD 테이블 추가
  + API 설계 -> 추가 수정사항
    +  피드백받은 내용을 고려해 전체적인 URI 구성 변경
    +  API 생성 -> 카테고리에 따른 상품 조회
    +  상품 상세조회에 대한 한 방 쿼리 작성 완료

## 2022-03-23 진행상황
  + 22일까지 진행헀던 commit을 기준으로 페드로의 코드와 병합 진행 -> 특별한 오류 X

    병합 후 페드로가 작성한 유저 관련 API 테스트 해보고 내 로컬 환경에서 작동하는지 확인
  
  + API 설계 -> 추가 수정사항
    + 메인페이지 API 작성 -> URI 형식 변경
    + 상품 상세조회 API 작성
    + 상품명검색, 카테고리로 상품 조회 API에서 빠진 부분 추가 필요 -> path variable로 idx를 받아오는 방식이 아닌 jwt 토큰 이용하는 방식으로 변경해보자


 
 ## 2022-03-24 진행상황
  + API 설계 -> 추가 수정사항
    + 상품등록 API 작성 
    + 상품명검색, 카테고리로 상품 조회 페이지에서 AD 표시 여부 추가해야 함 -> 페드로와 협의 후 진행하기로

  + ⚠Issue: local환경에서 post API, jwt token을 이용한 API를 호출하지 못하는 문제 발생
  
     error code -> java.lang.NoClassDefFoundError: javax/xml/bind/DatatypeConverter
     
    +  최신 jdk의 java EE API 미포함에 따른 문제
    +  build.grade에 추가적인 dependency 설정해 문제 해결


 ## 2022-03-25 진행상황
  + API 설계 -> 추가 수정사항
    + 상품등록 API에서 사진 리스트와 태그 정보 받아오는 방식 고민중 -> 수정 필요
    + 상품 상세조회 클릭시 조회수 증가하는 구문 추가
    + 상품 결제 등록 API 작성
    + 상품 수정 API 작성중 상품등록에서와 같이 사진과 태그에 관한 문제로 인해 중단
    + 전체적인 URI 구성 페드로와 협의 후 변경


## 2022-03-26 진행상황
  + App 팀과 중간 회의 진행
    + 상세조회에서 이미지URL와 태그 등을 ,로 받아올지 리스트 형식으로 받아올지 협의 -> 일단 기존에 진행했던 방식 유지
    + 프론트 입장에서 우선시 되어야 할 API 점검
  + API 설계
    + 결제등록 API 작성
    + 구매내역 API 작성
    + 우선 전체적으로 동작이 되게 API 작성 후 세부사항 고쳐나갈 예정


## 2022-03-27 진행상황 
  + API 설계
    + 판매내역 조회 API 작성
    + 상품문의 조회 API 작성
    + 상품문의 등록 API 작성
    + 상품등록 API 작성중(여러장의 사진과 태그를 동시에 등록할 수 있게)
    + 이제까지 작성한 API중 예외처리 필요한 부분 확인 -> 우선적으로 나머지 API 작성 후 고쳐나갈 계획

## 2022-03-28 진행상황 
  + API 설계
    + 상품 상세조회 API 수정 -> 게시날짜, 판매자에게 달린 리뷰 수 추가
    + 상품등록 API 작성완료
    + 상품문의 삭제 API 작성

    DB에 상품 더미데이터 추가

## 2022-03-29 진행상황 
  + API 설계
    + 유저 후기 조회 API 작성
    + 후기 삭제 API 작성
    + 후기등록 API 작성
    + 제품 상세조회에서 특정 인덱스 이외의 상품들이 불러오지 않는 오류 발생 -> 수정 완료
    + 상품 수정, 후기 수정에서 새로운 사진 또는 태그를 추가할 때 이전의 사진들과 태그를 어떻게 처리해야 할지 고민중

## 2022-03-30 진행상황
  + API 설계
    + 상품/ 후기 수정 부분에서 새로운 사진을 받는다면 기존에 있던 사진은 삭제한 후 세부정보 수정하고 새로 받은 사진 추가하는 방식 적용
    + 후기 수정 API 작성
    + 제품 상세정보 수정 API 작성  -> 기존에 계획했던 API는 모두 작성
  + 작성한 코드들에 대한 추가 예외처리, API 명세서 정리
  
  <details>
  <summary>2차 피드백 진행</summary>
  <div markdown="1">
    
  깃허브 쓰는 방식 잘못됨. 협업의 의미가 없다 
    
  -> 서로 작성해야 할 폴더를 나눠 그 안에서만 작업을 진행하였고 병합시에도 각자의 브랜치에서 코드를 다운받아 폴더만 추가해 주는 식으로 병합을 진행했었음 
    
  -> git을 제대로 활용하지 못함 
    
  -> git merge 등의 명령어를 이용해 서로의 코드를 주기적으로 병합해야 했는데 이해를 잘못한채로 진행해옴
  </div>
  </details>

    
## 2022-03-31 진행상황
  + API 설계
    + HomeDao 쿼리 수정
    + Product 관련 API 예외처리 추가
    + Review 관련 API 예외처리 추가
  + API 명세서 정리
  + BaseResponseStatus 에러코드 정리
  + gitignore -> application.yml 추가
