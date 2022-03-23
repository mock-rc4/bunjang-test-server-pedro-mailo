package com.example.demo.src.product;

import com.example.demo.src.product.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;


@RestController
@RequestMapping("/app/products")
public class ProductController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ProductProvider productProvider;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final JwtService jwtService;

    public ProductController(ProductProvider productProvider, ProductService productService, JwtService jwtService){
        this.productProvider = productProvider;
        this.productService = productService;
        this.jwtService = jwtService;
    }

    /**
     * 제품 상세조회 API
     * [GET] products/:productIdx
     *
     */
    @ResponseBody
    @GetMapping("/{productIdx}")
    public BaseResponse<List<GetProductDetailRes>> getProductDetail(@PathVariable("productIdx") int productIdx){
        try{
            //jwt에서 idx 추출.
            int userIdx = jwtService.getUserIdx();

            List<GetProductDetailRes> getProductDetail = productProvider.getProductDetail(userIdx,productIdx);

            return new BaseResponse<>(getProductDetail);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }





    /**
     * 검색어로 제품 조회 API
     * [GET] products/productName?Keyword=
     */
    @ResponseBody
    @GetMapping("/productName")
    public BaseResponse<List<GetProductSearchRes>> getProductBySearch(@RequestParam(required = false) String Keyword){
        //System.out.println("키워드 컨트롤러 들어옴");
        //.out.println(Keyword);
        try{
            if(Keyword == ""){ // 검색어를 입력하지 않았을 때
                System.out.println("검색어 입력 안함");
                return new BaseResponse<>(EMPTY_REQUEST); // 2004 키워드를 입력하지 않았습니다
            }
            List<GetProductSearchRes> getProductSearchRes = productProvider.getProductsBySearch(Keyword);
            if(getProductSearchRes.size() == 0){ // 검색어에 해당하는 정보가 없을 때
                System.out.println("검색어에 해당하는 정보 없음");
                return new BaseResponse<>(EMPTY_RESPONSE); // 3001 입력한 키워드에 대한 검색결과가 없습니다.
            }
            else{
                return new BaseResponse<>(getProductSearchRes);
            }
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @GetMapping("/category/{categoryIdx}")
    public BaseResponse<List<GetProductSearchRes>> getProductByCategory(@PathVariable("categoryIdx") int categoryIdx){
        System.out.println("카테고리 컨트롤러 들어옴");
        System.out.println(categoryIdx);
        try{
            List<GetProductSearchRes> getProductByCategory = productProvider.getProductsByCategory(categoryIdx);
            if(getProductByCategory.size() == 0){ // 검색어에 해당하는 정보가 없을 때
                System.out.println("검색어에 해당하는 정보 없음");
                return new BaseResponse<>(EMPTY_RESPONSE); // 3001 입력한 키워드에 대한 검색결과가 없습니다.
            }
            else{
                return new BaseResponse<>(getProductByCategory);
            }
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }


/**
 * public BaseResponse<GetShopRes> getShop(@PathVariable("shopIdx") int restNum) {
 *         //System.out.println(restNum);
 *         try{
 *             //System.out.println("try 들어옴");
 *             GetShopRes getShopRes = shopProvider.getShop(restNum);
 *             return new BaseResponse<>(getShopRes);
 *         } catch(BaseException exception){
 *             //System.out.println("에러 체크");
 *             return new BaseResponse<>((exception.getStatus()));
 *         }
 *
 *     }
 */






} /** class ProductController 끝나는 괄호 **/
