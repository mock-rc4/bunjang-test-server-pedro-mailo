# Bunjang Clone coding Project 
> ## [🙈 기획서](https://docs.google.com/document/d/1mJEql5gy8jLTYZXEtAzuZwmRtznI321b/edit)
> ## [📫 API Sheet](https://docs.google.com/spreadsheets/d/1saKFspgb7g0NZVLX445RVXS27s1UKaY5/edit#gid=990061567)    
> ## [🧩 ERD](https://aquerytool.com/aquerymain/index/?rurl=f5891c32-395a-4960-8e52-5380280e35ef&)
>> password:42g555

<br /> 


## 📌 1주차 목표 작업 범위 (2022-03-19 ~ 2022-03-25) 
- [x] ERD 설계
- [x] EC2 환경구축
- [x] API Sheet 리스트업
- [x] 유저 회원가입 API 제작
- [x] 유저 로그인 API 제작
- [x] 상품 조회 API 제작
- [x] 결제 API 제작

<br /> 

## 📌 2주차 목표 작업 범위 (2022-03-26 ~ 2022-04-01)
- [ ] 


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
  - validation 처리 (회원가입, 로그인 API 관련 처리 but 정규식은 아직 안함)
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


### 4. 찜 API 오류 (22-03-26)
- 문제 : 찜 비활성화 하는 과정에서 코드 에러가 나는것 확인
``` JAVA
in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2] with root cause]- Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2] with root cause
org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2
```
- 해결 : DAO에서 Body 에 작성한 내용을 파라미터로 받고 Favorite 테이블에 데이터 조회해 데이터 유무를 파악하는데, status = 1 처럼 특정 조건으로 지정해서 쿼리문 오류 나는것 확인


### 5. 팔로워 생성 API (22-03-28)
- 문제 : 팔로워 조회 API 관련 매개변수 처리 문제 

### 6. 파싱 오류 (22-03-30)
- 문제 : 메세지 전송 API 테스트중 파싱 에러 나는 것 확인
 ``` JAVA
 04:31:14.359 WARN  [File:AbstractHandlerExceptionResolver.java] [Func:logException] [Line:207] [Message:Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot construct instance of `com.example.demo.src.chat.model.PostChatMessageRep` (although at least one Creator exists): cannot deserialize from Object value (no delegate- or property-based Creator); nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot construct instance of `com.example.demo.src.chat.model.PostChatMessageRep` (although at least one Creator exists): cannot deserialize from Object value (no delegate- or property-based Creator)

 ```
- 해결 : Req 값으로 파라미터를 메세지만 받아서 생기는 결과.. 임의로 매개변수 하나 추가 해서 테스트 결과 나오는걸로 확인

<<<<<<< HEAD
<br />
=======

### 7. GET 오류 (22-03-30)
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
      | GetUserAddressRes.java // 유저 주소 리스트 Response
      | PatchAddressReq .java // 
      | 
      |
      |
     | Controller
     | Service
     | Provider
     | Dao
    > chat
     > model
     | Controller
     | Service
     | Provider
     | Dao
    > faovire
     > model
     | Controller
     | Service
     | Provider
     | Dao
    > Follow
     > model
     | Controller
     | Service
     | Provider
     | Dao
    > user
     > model
     | Controller
     | Service
     | Provider
     | Dao
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
