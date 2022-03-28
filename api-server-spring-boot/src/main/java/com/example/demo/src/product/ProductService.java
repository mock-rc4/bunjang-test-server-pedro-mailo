package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.src.product.model.*;
import com.example.demo.src.product.ProductDao;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class ProductService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;
    private final ProductProvider productProvider;
    private final JwtService jwtService;

    @Autowired
    public ProductService(ProductDao productDao, ProductProvider productProvider, JwtService jwtService) {
        this.productDao = productDao;
        this.productProvider = productProvider;
        this.jwtService = jwtService;
    }


// product 생성
    public List<String> createProduct(PostProductReq postProductReq, int userIdx) throws BaseException{
        try{
            System.out.println("제품생성 서비스 들어옴");
            int userIdxPram = userIdx;
            PostcreateNewProduct newProductRes = productDao.createProduct(postProductReq,userIdxPram);
            //int Idx = productDao.createProduct(postProductReq,userIdxPram).getProductIdx(); // 방금 만든 product의 Idx
            int Idx = newProductRes.getProductIdx();
            PostProductRes ProductDetail = newProductRes.getProductDetail();

            List newProduct = new ArrayList<PostProductRes>();
            newProduct.add(ProductDetail);
            /**
            newProduct.add(userIdxPram);
            newProduct.add(postProductReq.getCategoryIdx());
            newProduct.add(postProductReq.getProductName());
            newProduct.add(postProductReq.getProductDesc());
            newProduct.add(postProductReq.getProductCondition());
            newProduct.add(postProductReq.getSaftyPay());
            newProduct.add(postProductReq.getIsExchange());
            newProduct.add(postProductReq.getAmount());
            newProduct.add(postProductReq.getIncludeFee());
            newProduct.add(postProductReq.getPrice());
            newProduct.add(postProductReq.getDirecttrans());
             **/
            System.out.println("제품생성 다오 통과");

            List newProductPictureList = productDao.createProductPicture(postProductReq, Idx);
            System.out.println("사진생성 다오 통과");
            List newProductTagList = productDao.createProductTag(postProductReq, Idx);
            System.out.println("태그생성 다오 통과");

            List newProductDetail = new ArrayList<>(Arrays.asList(newProduct, newProductPictureList, newProductTagList));


            return newProductDetail;


            /*
            return new PostProductRes(Idx, userIdxPram, postProductReq.getCategoryIdx(),
                    postProductReq.getProductName(), postProductReq.getProductDesc(), postProductReq.getProductCondition(),
                    postProductReq.getSaftyPay(), postProductReq.getIsExchange(), postProductReq.getAmount(),
                    postProductReq.getIncludeFee(), postProductReq.getPrice(), postProductReq.getDirecttrans());
            */
        }catch(Exception exception){
            System.out.println("dao에서 불러올때 에러");
            throw new BaseException(DATABASE_ERROR);
        }

    }








// payment 생성
    public PostPaymentRes createPayment(PostPaymentReq postPaymentReq, int buyerIdx, int productIdx) throws BaseException{
        try{
            System.out.println("제품생성 서비스 들어옴");
            int buyerIdxParm = buyerIdx;
            int productIdxParm = productIdx;
            int changeProductProgress = productDao.changeProductProgress(productIdxParm);
            int Idx = productDao.createPayment(postPaymentReq, buyerIdxParm, productIdxParm);

            return new PostPaymentRes(Idx, productIdxParm, buyerIdxParm, postPaymentReq.getSafetyTax(),
                    postPaymentReq.getPoint(), postPaymentReq.getTotalPaymentAmount(), postPaymentReq.getPaymentMethod(), postPaymentReq.getTransactionMethod(),
                    postPaymentReq.getAddress());
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

// ProductQuestion 생성
    public PostProductQuesRes createProductQuestion(PostProductQuesReq postProductQuesReq, int userIdx, int productIdx) throws BaseException{
        try{
            System.out.println("상품문의 생성 서비스 들어옴");
            int userIdxParm =userIdx;
            int productIdxParm = productIdx;
            int QIdx = productDao.createProductQuestion(postProductQuesReq, productIdxParm, userIdxParm);

            return new PostProductQuesRes(QIdx, productIdxParm, userIdxParm, postProductQuesReq.getQuestionDesc());
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

// 상품 문의 삭제
    public void deleteProductQuestion(int userIdx, int QIdx)throws BaseException{
        try{
            System.out.println("상품문의 삭제 서비스 들어옴");
            int checkMyQuestion = productDao.checkQuestionUser(userIdx, QIdx);
            if(checkMyQuestion == 0){
                System.out.println("체크에서 걸렸을때");
                throw new BaseException(INVALID_USER_JWT);
            }
            else{
                System.out.println("체크에서 안 걸렸을때");
                int changeStatus = productDao.deleteProductQuestion(QIdx);
                System.out.println("dao에서 불러오기 성공");
            }
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }




}
