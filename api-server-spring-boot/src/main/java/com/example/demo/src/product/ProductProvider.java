package com.example.demo.src.product;

import com.example.demo.config.BaseException;
//import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.product.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ProductProvider {
    private final ProductDao productDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductProvider(ProductDao productDao, JwtService jwtService) {
        this.productDao = productDao;
        this.jwtService = jwtService;
    }

// 제품 상세페이지
    public GetProductDetailRes getProductDetail(int userIdx, int productIdx) throws BaseException{
        try{
            int ViewSync = productDao.CreateView(userIdx,productIdx);
            GetProductDetailRes getProductDetailRes = productDao.getProductDetailRes(userIdx, productIdx);
            return  getProductDetailRes;
        }
        catch (Exception exception){
            System.out.println("provider의 catch부분");
            throw new BaseException(DATABASE_ERROR);
        }
    }





// 검색어로 제품 조회
    public List<GetProductSearchRes> getProductsBySearch(String keyword) throws BaseException{
        try{
            List<GetProductSearchRes> getProductSearchRes = productDao.getProductSearchRes(keyword);
            return  getProductSearchRes;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

// 카테고리로 제품 조회
    public List<GetProductSearchRes> getProductsByCategory(int categoryIdx) throws BaseException{
        System.out.println("카테고리 provider 들어옴");
        try{
            List<GetProductSearchRes> getProductByCategory = productDao.getProductByCategory(categoryIdx);
            System.out.println("getProductByCategory 나오나?");
            System.out.println(getProductByCategory);
            return  getProductByCategory;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }

// 구매내역 조회
    public List<GetBuyRes> getPaymentByBuyer(int buyerIdx) throws BaseException{
        try {
            System.out.println("구매내역 조회 provider 들어옴");
            List<GetBuyRes> getPaymentByBuyer = productDao.getBuyListByUserIdx(buyerIdx);
            return getPaymentByBuyer;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

// 판매내역 조회
    public List<GetBuyRes> getPaymentBySeller(int sellerIdx) throws BaseException{
        try{
            List<GetBuyRes> getPaymentBySeller = productDao.getSellListByUserIdx(sellerIdx);
            return getPaymentBySeller;
        }catch (Exception exception){
            System.out.println("dao에서 에러");
            throw new BaseException(DATABASE_ERROR);
        }
    }


// 상품 문의 조회
    public List<GetProductQuesRes> getProductQuestion(int userIdx, int productIdx) throws BaseException{
        try{
            List<GetProductQuesRes> getQues = productDao.getProductQues(userIdx, productIdx);
            return getQues;
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkQuestionUser(int userIdx, int QIdx) throws BaseException{
        try{
            return productDao.checkQuestionUser(userIdx, QIdx);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }

    }




}  /** class ProductProvider 닫는괄호 **/
