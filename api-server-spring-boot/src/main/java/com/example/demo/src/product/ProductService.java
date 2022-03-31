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
            //System.out.println("제품생성 서비스 들어옴");
            int userIdxPram = userIdx;
            PostcreateNewProduct newProductRes = productDao.createProduct(postProductReq,userIdxPram);
            //int Idx = productDao.createProduct(postProductReq,userIdxPram).getProductIdx(); // 방금 만든 product의 Idx
            int Idx = newProductRes.getProductIdx();
            PostProductRes ProductDetail = newProductRes.getProductDetail();

            List newProduct = new ArrayList<PostProductRes>();
            newProduct.add(ProductDetail);
            //System.out.println("제품생성 다오 통과");

            List newProductPictureList = productDao.createProductPicture(postProductReq, Idx);
            //System.out.println("사진생성 다오 통과");
            List newProductTagList = productDao.createProductTag(postProductReq, Idx);
            //System.out.println("태그생성 다오 통과");

            List newProductDetail = new ArrayList<>(Arrays.asList(newProduct, newProductPictureList, newProductTagList));


            return newProductDetail;

        }catch(Exception exception){
            //System.out.println("dao에서 불러올때 에러");
            throw new BaseException(DATABASE_ERROR);
        }

    }

// 상품 수정
    public int editProduct(PostProductReq postProductReq, int userIdx, int productIdx){
        int checkMyProduct = productDao.checkProductUser(userIdx, productIdx);  // 물건이 내가 올린게 맞는지
        if(checkMyProduct == 0){
            System.out.println("내가 작성한 물건이 아니었음");
            return 0;
        }

        int checkProductStatus = productDao.checkProductStatus(productIdx);
        if(checkProductStatus == 2){    // 삭제된 제품일때
            return 2;
        }

        List<String> editImages = postProductReq.getImageUrl();
        List<String> editTags = postProductReq.getTagName();

        if(editImages != null){ // 바꿀 사진이 있으면
            productDao.deleteProductPictureReal(productIdx);
            productDao.createProductPicture(postProductReq, productIdx);
        }
        if(editTags != null){   // 바꿀 태그가 있으면
            productDao.deleteTagReal(productIdx);
            productDao.createProductTag(postProductReq, productIdx);
        }
        productDao.editProduct(postProductReq, productIdx);
        return 1;
    }








// 상품 삭제
    public int deleteProduct(int userIdx, int productIdx) throws BaseException{
        try {
            int checkMyProduct = productDao.checkProductUser(userIdx,productIdx);
            if(checkMyProduct == 0){
                return 0;
                //throw new BaseException(INVALID_USER_JWT);

            }
            int checkProductStatus = productDao.checkProductStatus(productIdx);
            if(checkProductStatus == 2){    // 삭제된 제품일때
                return 2;
            }
            productDao.deleteProduct(productIdx);
            System.out.println("상품 status 변경");
            productDao.deleteProductPicture(productIdx);
            System.out.println("상품사진 status 변경");
            productDao.deleteProductTag(productIdx);
            System.out.println("상품태그 status 변경");
            productDao.deleteProductQuestionByPIdx(productIdx);
            System.out.println("상품문의 status 변경");
            return 1;

        }catch (Exception exception){
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
