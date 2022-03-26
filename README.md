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