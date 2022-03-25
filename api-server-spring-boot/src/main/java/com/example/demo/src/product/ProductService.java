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
    public PostProductRes createProduct(PostProductReq postProductReq, int userIdx) throws BaseException{
        try{
            System.out.println("제품생성 서비스 들어옴");
            int userIdxPram = userIdx;
            int Idx = productDao.createProduct(postProductReq,userIdxPram);

            return new PostProductRes(Idx, userIdxPram, postProductReq.getCategoryIdx(),
                    postProductReq.getProductName(), postProductReq.getProductDesc(), postProductReq.getProductCondition(),
                    postProductReq.getSaftyPay(), postProductReq.getIsExchange(), postProductReq.getAmount(),
                    postProductReq.getIncludeFee(), postProductReq.getPrice(), postProductReq.getDirecttrans());

        }catch(Exception exception){
            System.out.println("dao에서 불러올때 에러");
            throw new BaseException(DATABASE_ERROR);
        }

    }

// payment 생성
    public PostPaymentRes createPayment(PostPaymentReq postPaymentReq, int buyerIdx) throws BaseException{
        try{
            int buyerIdxParm = buyerIdx;
            int Idx = productDao.createPayment(postPaymentReq, buyerIdxParm);

            return new PostPaymentRes(Idx, postPaymentReq.getStatus(), postPaymentReq.getProductIdx(), buyerIdxParm, postPaymentReq.getSafetyTax(),
                    postPaymentReq.getPoint(), postPaymentReq.getTotalPaymentAmount(), postPaymentReq.getPaymentMethod(), postPaymentReq.getTransactionMethod(),
                    postPaymentReq.getAddress());
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }




}
