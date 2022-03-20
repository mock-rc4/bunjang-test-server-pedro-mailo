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
