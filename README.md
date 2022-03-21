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