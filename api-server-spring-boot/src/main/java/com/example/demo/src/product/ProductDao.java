package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 거래정보 생성
     */
    public int createPayment(PostPaymentReq postPaymentReq, int buyerIdx, int productIdx) {
        System.out.println("제품생성 dao 들어옴");
        String createPaymentQuery = "insert into Payment (productIdx, buyerIdx, safetyTax," +
                "point, totalPaymentAmount, paymentMethod, transactionMethod, address) VALUES (?,?,?,?,?,?,?,?)";
        int buyerIdxParm = buyerIdx;
        int productIdxParm = productIdx;
        System.out.println(buyerIdx);
        System.out.println(productIdx);
        this.jdbcTemplate.update(createPaymentQuery, productIdxParm, buyerIdxParm, postPaymentReq.getSafetyTax(),
                postPaymentReq.getPoint(), postPaymentReq.getTotalPaymentAmount(), postPaymentReq.getPaymentMethod(), postPaymentReq.getTransactionMethod(),
                postPaymentReq.getAddress());
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public int changeProductProgress(int productIdx) {
        String changeProgressQuery = "update Product P\n" +
                "set P.progress = 3\n" +
                "where P.Idx = ?";
        int productParams = productIdx;
        Object[] changeProgressParams = new Object[]{productIdx};
        return this.jdbcTemplate.update(changeProgressQuery, changeProgressParams);
    }


    /**
     * 제품 생성
     **/
    public PostcreateNewProduct createProduct(PostProductReq postProductReq, int userIdx) {
        System.out.println("제품생성 Dao 들어옴");
        String createProductQuery = "insert into Product (userIdx, categoryIdx, " +
                "productName, productDesc, " +
                "productCondition, saftyPay," +
                "isExchange, amount," +
                "includeFee, price, directtrans) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        int userIdxParams = userIdx;
        System.out.println(userIdxParams);

        System.out.println(createProductQuery);

        this.jdbcTemplate.update(createProductQuery, userIdxParams, postProductReq.getCategoryIdx(),
                postProductReq.getProductName(), postProductReq.getProductDesc(), postProductReq.getProductCondition(),
                postProductReq.getSaftyPay(), postProductReq.getIsExchange(), postProductReq.getAmount(),
                postProductReq.getIncludeFee(), postProductReq.getPrice(), postProductReq.getDirecttrans());
        System.out.println("확인1");
        String lastInserIdQuery = "select last_insert_id()";
        System.out.println("확인2");

        PostProductRes postProductRes;
        postProductRes = new PostProductRes(userIdxParams, postProductReq.getCategoryIdx(),
                postProductReq.getProductName(), postProductReq.getProductDesc(), postProductReq.getProductCondition(),
                postProductReq.getSaftyPay(), postProductReq.getIsExchange(), postProductReq.getAmount(),
                postProductReq.getIncludeFee(), postProductReq.getPrice(), postProductReq.getDirecttrans());

        PostcreateNewProduct ProductIdxAndRes;
        ProductIdxAndRes = new PostcreateNewProduct(this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class),postProductRes);


        return ProductIdxAndRes;
    }

    /**
     * 제품 정보 수정
     **/
    public void editProduct(PostProductReq postProductReq, int productIdx){
        String editProductQuery = "update Product P\n" +
                "set P.categoryIdx = ?,\n" +
                "    P.productName = ?,\n" +
                "    P.productDesc = ?,\n" +
                "    P.productCondition = ?,\n" +
                "    P.saftyPay = ?,\n" +
                "    P.isExchange = ?,\n" +
                "    P.amount = ?,\n" +
                "    P.includeFee = ?,\n" +
                "    P.price = ?,\n" +
                "    P.directtrans = ?\n" +
                "where P.Idx =?";
        this.jdbcTemplate.update(editProductQuery, postProductReq.getCategoryIdx(), postProductReq.getProductName(),
                postProductReq.getProductDesc(), postProductReq.getProductCondition(), postProductReq.getSaftyPay(),
                postProductReq.getIsExchange(), postProductReq.getAmount(), postProductReq.getIncludeFee(),
                postProductReq.getPrice(), postProductReq.getDirecttrans(), productIdx);
    }



    /**
     * 제품 등록시 사진 첨부
     **/
    public List<String> createProductPicture(PostProductReq postProductReq, int productIdx){
        System.out.println("사진생성 다오 들어옴");
        List<String> pic = postProductReq.getImageUrl();
        System.out.println(pic);
        List newPictureList =new ArrayList<String>();
        for(String newpic : pic){
            String createPictureQuery = "insert into ProductImage (productIdx, imageUrl) VALUES (?,?)";
            this.jdbcTemplate.update(createPictureQuery, productIdx, newpic);
            newPictureList.add(newpic);
        }


        return newPictureList;
    }

    /**
     * 제품 등록시 테그 첨부
     **/
    public List<String> createProductTag(PostProductReq postProductReq, int productIdx){
        System.out.println("태그생성 다오 들어옴");
        List<String> tag = postProductReq.getTagName();
        System.out.println(tag);
        List newTagList = new ArrayList<String>();
        if(tag != null){
            for(String newtag:tag){
                String createTagQuery = "insert into ProductTag(productIdx, tagName) VALUES (?,?)";
                this.jdbcTemplate.update(createTagQuery, productIdx, newtag);
                newTagList.add(newtag);
            }
        }

        return newTagList;
    }


    /**
     * 상품이 내가 올린 상품인지 확인쿼리
     */
    public int checkProductUser(int userIdx, int productIdx){
        String checkQuestionQuery = "select exists(\n" +
                "    select *\n" +
                "    where P.userIdx = ?\n" +
                "           ) myProduct\n" +
                "from Product P\n" +
                "where P.Idx = ?";
        return this.jdbcTemplate.queryForObject(checkQuestionQuery,int.class, userIdx, productIdx);
    }



    /**
     * 상품 삭제 -> 상품 status 변경쿼리
     */
    public void deleteProduct(int productIdx){
        System.out.println("삭제 dao 접근 성공");
        System.out.println(productIdx);
        String changeStatusQuery = "update Product P\n" +
                "set status = 2\n" +
                "where P.Idx = ?";
        System.out.println(changeStatusQuery);
        Object[] changeStatueParms  =new Object[]{productIdx};
        this.jdbcTemplate.update(changeStatusQuery,changeStatueParms);
        //return this.jdbcTemplate.update(changeStatusQuery,changeStatueParms);
    }


    /**
     * 상품 삭제 -> 상품 사진 삭제(비활성화) 쿼리
     */
    public void deleteProductPicture(int productIdx){
        System.out.println("사진 삭제 dao 접근 성공");
        System.out.println(productIdx);
        String changeStatusQuery = "update ProductImage PI\n" +
                "set status = 2\n" +
                "where PI.productIdx = ?";
        System.out.println(changeStatusQuery);
        Object[] changeStatueParms  =new Object[]{productIdx};

        this.jdbcTemplate.update(changeStatusQuery,changeStatueParms);
    }

    /**
     * 상품 진짜 삭제 -> 상품 사진 삭제 쿼리
     */
    public void deleteProductPictureReal(int productIdx){
        String deleteQuery = "delete from ProductImage where productIdx = ?";
        this.jdbcTemplate.update(deleteQuery,productIdx);
    }




    /**
     * 상품 삭제 -> 상품 태그 삭제(비활성화) 쿼리
     */
    public void deleteProductTag(int productIdx){
        System.out.println("사진 삭제 dao 접근 성공");
        System.out.println(productIdx);
        String changeStatusQuery = "update ProductTag PT\n" +
                "set status = 2\n" +
                "where PT.productIdx = ?";
        System.out.println(changeStatusQuery);
        Object[] changeStatueParms  =new Object[]{productIdx};
        this.jdbcTemplate.update(changeStatusQuery,changeStatueParms);
    }

    /**
     * 상품 태그 삭제 -> 상품 태그 진짜 삭제 쿼리
     */
    public void deleteTagReal(int productIdx){
        String deleteQuery = "delete from ProductTag where productIdx = ?";
        this.jdbcTemplate.update(deleteQuery,productIdx);
    }





    /**
     * 상품 삭제 -> 상품 문의 삭제(비활성화) 쿼리
     */
    public void deleteProductQuestionByPIdx(int productIdx){
        System.out.println("사진 삭제 dao 접근 성공");
        System.out.println(productIdx);
        String changeStatusQuery = "update ProductQuestion PQ\n" +
                "set status = 2\n" +
                "where PQ.productIdx = ?";
        System.out.println(changeStatusQuery);
        Object[] changeStatuePram  =new Object[]{productIdx};
        this.jdbcTemplate.update(changeStatusQuery,changeStatuePram);
    }







    /**
     * 제품 상세조회
     **/
    public GetProductDetailRes getProductDetailRes(int userIdx, int productIdx) {
        System.out.println("dao 들어옴");
        System.out.println(userIdx);
        System.out.println(productIdx);
        String getProductDetailQuery = "select P.Idx PIdx, group_concat(distinct PI.imageUrl) imageUrl,\n" +
                "       P.price, P.saftyPay,\n" +
                "       P.productName,\n" +
                "       case when 24 >= timestampdiff(HOUR, P.createAt, current_timestamp)\n" +
                "                           then concat(timestampdiff(HOUR, P.createAt, current_timestamp),'시간 전')\n" +
                "                           else concat(timestampdiff(DAY, P.createAt, current_timestamp), '일 전') end createAt,\n" +
                "       count(distinct V.Idx) viewCount, count(distinct F.Idx) likeCount,\n" +
                "       P.directtrans,\n" +
                "       P.productCondition, P.includeFee, P.amount,\n" +
                "       P.productDesc,\n" +
                "       group_concat(distinct PT.tagName) tag,\n" +
                "       C.categoryName,\n" +
                "       U.Idx UIdx, U.profileImage, U.shopName,\n" +
                "       count(distinct FW.Idx) follower,\n" +
                "       avg(distinct FORRATE.reviewRate) avgStar, count(distinct FORRATE.reviewRate) reviewCount,\n" +
                "       case when UF.FavoriteUserIdx = ? then 1\n" +
                "            else 0 end myLike\n" +
                "from Product P\n" +
                "left join ProductImage PI on P.Idx = PI.productIdx\n" +
                "left join Views V on P.Idx = V.productIdx\n" +
                "left join Favorite F on P.Idx = F.productIdx\n" +
                "left join ProductTag PT on P.Idx = PT.productIdx\n" +
                "left join Category C on P.categoryIdx = C.Idx\n" +
                "left join User U on P.userIdx = U.Idx\n" +
                "left join Follow FW on U.Idx = FW.followingIdx\n" +
                "left join ((select P2.Idx, P2.userIdx, PYR.productIdx, PYR.reviewRate\n" +
                "    from Product P2\n" +
                "    join (select PY.productIdx,R.reviewRate\n" +
                "                from Payment PY\n" +
                "                join Review R on PY.Idx = R.paymentIdx) as PYR on PYR.productIdx = P2.Idx) as FORRATE)\n" +
                "    on U.Idx = FORRATE.userIdx\n" +
                "left join (select U.Idx FavoriteUserIdx, F.productIdx FavoriteProcductIdx\n" +
                "      from Favorite F\n" +
                "      join User U on U.Idx = F.userIdx\n" +
                "      where U.Idx =?) UF on UF.FavoriteProcductIdx = P.Idx\n" +
                "\n" +
                "where P.Idx = ?\n" +
                "group by P.Idx";
        int GetUserIdx = userIdx;
        int GetProductIdx = productIdx;

        return this.jdbcTemplate.queryForObject(getProductDetailQuery,
                (rs, rowNum) -> new GetProductDetailRes(
                        rs.getInt("PIdx"),
                        rs.getString("imageUrl"),
                        rs.getInt("price"),
                        rs.getInt("saftyPay"),
                        rs.getString("productName"),
                        rs.getString("createAt"),
                        rs.getInt("viewCount"),
                        rs.getInt("likeCount"),
                        rs.getString("directtrans"),
                        rs.getInt("productCondition"),
                        rs.getInt("includeFee"),
                        rs.getInt("amount"),
                        rs.getString("productDesc"),
                        rs.getString("tag"),
                        rs.getString("categoryName"),
                        rs.getInt("UIdx"),
                        rs.getString("profileImage"),
                        rs.getString("shopName"),
                        rs.getInt("follower"),
                        rs.getFloat("avgStar"),
                        rs.getInt("reviewCount"),
                        rs.getInt("myLike")),
                GetUserIdx, GetUserIdx, GetProductIdx
        );
    }

    public int CreateView(int userIdx, int productIdx) {
        String createViewQuery = "insert into Views (userIdx,productIdx) values (?,?)";
        int userParams = userIdx;
        int productParams = productIdx;
        Object[] createViewParams = new Object[]{userIdx, productIdx};
        return this.jdbcTemplate.update(createViewQuery, createViewParams);
    }


    /**
     * 검색어로 제품 조회
     **/
    public List<GetProductSearchRes> getProductSearchRes(String keyword) {
        String getProductsQuery = "select P.Idx, P.price, P.productName, P.saftyPay, PI.imageUrl\n" +
                "from Product as P\n" +
                "left join ProductImage as PI on P.Idx = PI.productIdx\n" +
                "where P.productName like concat('%',?,'%')\n" +
                "group by P.Idx";
        String GetProductSearchResPrams = keyword;
        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductSearchRes(
                        rs.getInt("Idx"),
                        rs.getInt("price"),
                        rs.getString("productName"),
                        rs.getInt("saftyPay"),
                        rs.getString("imageUrl")),
                GetProductSearchResPrams);
    }   // getProductSearchRes() 끝


    /**
     * 카테고리로 제품 조회
     **/
    public List<GetProductSearchRes> getProductByCategory(int categoryIdx) {
        System.out.println("카테고리 dao 들어옴");
        String getProductsQuery = "select P.Idx, P.price, P.productName, P.saftyPay, PI.imageUrl\n" +
                "                from Product as P\n" +
                "                left join ProductImage as PI on P.Idx = PI.productIdx\n" +
                "                join Category as C on P.categoryIdx = C.Idx\n" +
                "                where C.Idx = ?\n" +
                "                group by P.Idx";
        int GetProductByCategoryPrams = categoryIdx;
        System.out.println("쿼리 파라미터 받음");
        return this.jdbcTemplate.query(getProductsQuery,
                (rs, rowNum) -> new GetProductSearchRes(
                        rs.getInt("Idx"),
                        rs.getInt("price"),
                        rs.getString("productName"),
                        rs.getInt("saftyPay"),
                        rs.getString("imageUrl")),
                GetProductByCategoryPrams);
    }   // getProductByCategory() 끝

    /**
     * 구매내역 조회
     */
    public List<GetBuyRes> getBuyListByUserIdx(int buyerIdx) {
        System.out.println("구매내역 Dao 들어옴");
        String getProductsByBuyerQuery = "select PY.Idx PYIdx, P.Idx PIdx,\n" +
                "       PI.imageUrl, P.productName,\n" +
                "       P.price,\n" +
                "       U.shopName buyerName, PY.paymentMethod,\n" +
                "       case when instr(DATE_FORMAT(PY.updateAt, '%Y-%m-%d %p %h:%i'), 'PM') > 0\n" +
                "       then replace(DATE_FORMAT(PY.updateAt, '%Y-%m-%d %p %h:%i'), 'PM', '오후')\n" +
                "       else replace(DATE_FORMAT(PY.updateAt, '%Y-%m-%d %p %h:%i'), 'AM', '오전')\n" +
                "       end as updateTime\n" +
                "from Payment PY\n" +
                "join Product P on PY.productIdx = P.Idx\n" +
                "join User U on PY.buyerIdx = U.Idx\n" +
                "left join ProductImage PI on PY.productIdx = PI.Idx\n" +
                "where PY.buyerIdx = ?";
        int getBuyerIdxPrams = buyerIdx;
        return this.jdbcTemplate.query(getProductsByBuyerQuery,
                (rs, rowNum) -> new GetBuyRes(
                        rs.getInt("PYIdx"),
                        rs.getInt("PIdx"),
                        rs.getString("imageUrl"),
                        rs.getString("productName"),
                        rs.getInt("price"),
                        rs.getString("buyerName"),
                        rs.getInt("paymentMethod"),
                        rs.getString("updateTime")),
                getBuyerIdxPrams);
    }

    /**
     * 판매내역 조회
     */
    public List<GetBuyRes> getSellListByUserIdx(int userIdx) {
        System.out.println("판매내역 Dao 들어옴");
        String getProductsBySellerQuery = "select PY.Idx PYIdx, P.Idx PIdx,\n" +
                "       PI.imageUrl, P.productName,\n" +
                "       P.price,\n" +
                "       U.shopName buyerNmae, PY.paymentMethod,\n" +
                "  case when\n" +
                "      instr(DATE_FORMAT(P.updateAt, '%Y-%m-%d %p %h:%i'), 'PM') > 0\n" +
                "        THEN\n" +
                "        replace(DATE_FORMAT(P.updateAt, '%Y-%m-%d %p %h:%i'), 'PM', '오후')\n" +
                "        ELSE\n" +
                "        replace(DATE_FORMAT(P.updateAt, '%Y-%m-%d %p %h:%i'), 'AM', '오전')\n" +
                "        END AS updateTime\n" +
                "from Payment PY\n" +
                "join Product P on PY.productIdx = P.Idx\n" +
                "join User U on PY.buyerIdx = U.Idx\n" +
                "left join ProductImage PI on PY.productIdx = PI.Idx\n" +
                "where P.userIdx = ?";
        int getSellerIdx = userIdx;
        System.out.println(getSellerIdx);
        return this.jdbcTemplate.query(getProductsBySellerQuery,
                (rs, rowNum) -> new GetBuyRes(
                        rs.getInt("PYIdx"),
                        rs.getInt("PIdx"),
                        rs.getString("imageUrl"),
                        rs.getString("productName"),
                        rs.getInt("price"),
                        rs.getString("buyerName"),
                        rs.getInt("paymentMethod"),
                        rs.getString("updateTime")),
                getSellerIdx
                );
    }

    /**
     * 상품문의 등록
     */
    public int createProductQuestion(PostProductQuesReq postProductQuesReq, int productIdx, int userIdx){
        String createQuesQuery = "insert into ProductQuestion (productIdx, userIdx, questionDesc)\n" +
                "VALUES (?,?,?)";
        int userIdxParm = userIdx;
        int productIdxParm = productIdx;
        this.jdbcTemplate.update(createQuesQuery, productIdxParm, userIdxParm, postProductQuesReq.getQuestionDesc());
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }




    /**
     * 상품문의 조회
     */
    public List<GetProductQuesRes> getProductQues(int userIdx, int productIdx) {
        System.out.println("상품문의 Dao 들어옴");
        System.out.println(productIdx);
        String getQuesQuery = "select PQ.Idx QIdx, PQ.productIdx PIdx, PQ.userIdx UIdx,\n" +
                "       U.profileImage, U.shopName, case when 60 > timestampdiff(MINUTE , PQ.createAt, current_timestamp)\n" +
                "                                        then concat(timestampdiff(MINUTE, PQ.createAt, current_timestamp), '분 전')\n" +
                "                                        when 24 >= timestampdiff(HOUR, PQ.createAt, current_timestamp)\n" +
                "                                        then concat(timestampdiff(HOUR, PQ.createAt, current_timestamp), '시간 전')\n" +
                "                                        when 31 >= timestampdiff(DAY , PQ.createAt, current_timestamp)\n" +
                "                                        then concat(timestampdiff(Day , PQ.createAt, current_timestamp), '일 전')\n" +
                "                                        else concat(timestampdiff(MONTH , PQ.createAt, current_timestamp), '달 전')\n" +
                "                                        end createAt,\n" +
                "       PQ.questionDesc,\n" +
                "       exists(select * where PQ.userIdx = ?) myQuestion\n" +
                "from ProductQuestion PQ\n" +
                "join User U on U.Idx = PQ.userIdx\n" +
                "where PQ.productIdx = ? and PQ.status = 1";
        int getproductIdx = productIdx;
        int getuserIdx = userIdx;
        return this.jdbcTemplate.query(getQuesQuery,
                (rs, rowNum)-> new GetProductQuesRes(
                        rs.getInt("QIdx"),
                        rs.getInt("PIdx"),
                        rs.getInt("UIdx"),
                        rs.getString("profileImage"),
                        rs.getString("shopName"),
                        rs.getString("createAt"),
                        rs.getString("questionDesc"),
                        rs.getInt("myQuestion")
                ),getuserIdx, getproductIdx
                );
    }

    /**
     * 상품문의가 내가 적은 문의인지 확인쿼리
     */
    public int checkQuestionUser(int userIdx, int QIdx){
        String checkQuestionQuery = "select exists(\n" +
                "    select *\n" +
                "    where PQ.userIdx = ?\n" +
                "           ) myQuestion\n" +
                "from ProductQuestion PQ\n" +
                "where PQ.Idx = ?";
        return this.jdbcTemplate.queryForObject(checkQuestionQuery,int.class, userIdx, QIdx);
    }
    /**
     * 상품문의 status 변경쿼리
     */
    public int deleteProductQuestion(int QIdx){
        System.out.println("삭제 dao 접근 성공");
        System.out.println(QIdx);
        String changeStatusQuery = "update ProductQuestion PQ\n" +
                                    "set status = 2\n" +
                                    "where PQ.Idx = ?";
        System.out.println(changeStatusQuery);
        Object[] changeStatueParms  =new Object[]{QIdx};
        return this.jdbcTemplate.update(changeStatusQuery,changeStatueParms);
    }


}   /** class  productDao 끝나는  괄호 */





