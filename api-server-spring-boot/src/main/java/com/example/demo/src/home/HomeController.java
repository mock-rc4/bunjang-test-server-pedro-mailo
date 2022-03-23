package com.example.demo.src.home;

import com.example.demo.src.home.model.*;

import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.product.ProductService;
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
@RequestMapping("/app/home")
public class HomeController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final HomeProvider homeProvider;
    @Autowired
    private final HomeService homeService;
    @Autowired
    private final JwtService jwtService;


    public HomeController(HomeProvider homeProvider, HomeService homeService, JwtService jwtService){
        this.homeProvider = homeProvider;
        this.homeService = homeService;
        this.jwtService = jwtService;
    }


    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<String>> getHomepageData(){
        try{
            int userIdx = jwtService.getUserIdx();
            List<String> getHomepageByUserIdx = homeProvider.getHomepage(userIdx);

            return new BaseResponse<>(getHomepageByUserIdx);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
