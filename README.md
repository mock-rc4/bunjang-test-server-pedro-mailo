# Bunjang Clone coding Project 
> ## [🙈 기획서](https://docs.google.com/document/d/1mJEql5gy8jLTYZXEtAzuZwmRtznI321b/edit)
> ## [📫 API Sheet](https://docs.google.com/spreadsheets/d/1saKFspgb7g0NZVLX445RVXS27s1UKaY5/edit#gid=990061567)    
> ## [🧩 ERD](https://aquerytool.com/aquerymain/index/?rurl=f5891c32-395a-4960-8e52-5380280e35ef&)
>> password:42g555

<br /> 

## 📌 1주차 목표 작업 범위 (2022-03-19 ~ 
- [x] ERD 설계
- [x] EC2 환경구축
- [x] API Sheet 리스트업
- [x] 유저 회원가입 API 제작
- [x] 유저 로그인 API 제작
- [x] 상품 조회 API 제작
- [ ] 결제 API 제작

<br /> 

## 📌 2주차 목표 작업 범위

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
  - 회원가입 API 작성(50%)
</div>
</details>

<details> 
<summary> 2022.3.21 (MON) </summary>
<div markdown="1">
 
 > 
  - API 명세서 작성(완성된 API 업데이트)
  - 회원가입 API 작성(100%)
  - 로그인 API (100%) 
</div>
</details>

<details> 
<summary> 2022.3.22 (TUE) </summary>
<div markdown="1">
 
 > 
  - API 명세서 작성(리스트만 작성)
  - validation 처리 작성(화원가입, 로그인 API 관련 처리 but 정규식은 아직 안함)
  - 개발팀장 피드백
  - 회원가입, 로그인 API 서버에 업로드
</div>
</details>

<details> 
<summary> 2022.3.23 (WEN) </summary>
<div markdown="1">
 
 > 
  - 마일로가 작성한 API 서버에 반영(상품검색어 기준 조회, 상품카테고리 기준 조회)
  - 회원 MyPage API 작성 완료 (100%)
  - 서버 인스턴스 에러 발생 확인후 조치 완료
</div>
</details>

<details> 
<summary> 2022.3.24(THU) </summary>
<div markdown="1">
 
 > 
  - 마일로가 작성한 API 서버에 반영(상품상세조회, 메인페이지 API)
  - 회원 MyPage API 작성 완료 (100%)
  - 회원 성별, 핸드폰번호, 생년월일 수정 총 3개 API 작성(50%)
</div>
</details>

<details> 
<summary> 2022.3.25 (FRI) </summary>
<div markdown="1">
 
 
 > 
  - 회원 성별, 핸드폰번호, 생년월일 수정 총 3개 API 작성완료(100%)
</div>
</details>
 

 
<details> 
<summary> 2022.3.27(SUN) </summary>
<div markdown="1">
 
 > 
  - 마일로가 작성한 API 서버에 반영(결제 관련 API)
  - 회원 팔로우 등록 및 삭제 API  작성 완료 (100%)
  - API 명세서 정리
</div>
</details>

<details> 
<summary> 2022.3.28(MON) </summary>
<div markdown="1">
 
 > 
  - 배송지 주소 등록, 조회 , 삭제 API 작성 완료 (100%)\
  - 유저 삭제 API 완료  
  - API 명세서 정리
  - 상품 더미데이터 생성
</div>
</details>

<details> 
<summary> 2022.3.29(TUE) </summary>
<div markdown="1">
 
 > 
  - 팔로워 API 작성중  (35%)\
  - 번개톡 API 작성 시작 
  - API 명세서 정리
  - 번개톡 관련 샘플데이터 생성
</div>
</details>


<br /> 

## ⚠ Issue
### 1. 인증번호 구현의 어려움 (22-03-20)
- 문제: 인증번호 구현의 난이도가 어렵다고 판단하여 인증번호를 패스워드로 변경 구현하는 방식을 서버 멘토에게 제안
- 해결: 인증번호를 패스워드로 변경 구현하는 방식으로 진행

### 2. 회원가입 API 오류(22-03-21)
- 문제 : 회원가입 API 생성후 postman 으로 테스트 할시 데이터베이스 오류나는것확인, 개발팀장과 확인결과, 유저 테이블에 패스워드 테이블의 데이터 타입을 VARCHAR(45) 로 설정하여 나오는 오류 확인, 사전에 패스워드를 설정시 암호화 변환되어 등록되는데,  VARCHAR(45)가 암호화된 패스워드 값을 받아내기에 너무 짧은것으로 확인 
- 해결 : 유저 테이블의 패스워드 칼럼의 데이터 타입을 varchar(45) -> text 로 변경후 정상적으로 나오는것 확인

### 3. 인스턴스 오류(22-03-23)
- 문제 : 서버 가동중 3/23 새벽 2시경 EC2 인스턴스 에러 나는것 확인 시스템은 정상이었지만, 통신상 오류가 나는 상황 확인.
- 해결 : 인스턴스 재부팅 후 정상으로 되는것 확인 완료


### 4. 찜 API 오류(22-03-26)
- 문제 : 찜 비활성화 하는 과정에서 코드 에러가 나는것 확인
``` JAVA
in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2] with root cause]- Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2] with root cause
org.springframework.dao.IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 2
```
- 해결 : DAO에서 Body 에 작성한 내용을 파라미터로 받고 Favorite 테이블에 데이터 조회해 데이터 유무를 파악하는데, status = 1 처럼 특정 조건으로 지정해서 쿼리문 오류 나는것 확인
<br /> 

### 5. 팔로워 생성 API (22-03-28)
- 문제 : 팔로워 조회 API 관련 매개변수  

## 🚀 참고자료



