package com.example.demo.src.home;

import com.example.demo.config.BaseException;
import com.example.demo.src.home.HomeDao;
import com.example.demo.src.home.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class HomeProvider {
    private final HomeDao homeDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public HomeProvider(HomeDao homeDao, JwtService jwtService) {
        this.homeDao = homeDao;
        this.jwtService = jwtService;
    }

    public List<String> getHomepage(int userIdx) throws BaseException{
        try{
            List HomeAd = homeDao.getAdListByHome();
            List HomeProduct = homeDao.getProductListByHome(userIdx);
            List HomepageData = new ArrayList<>(Arrays.asList(HomeAd, HomeProduct));
            return HomepageData;
        }
        catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }




}  /** class HomeProvider 닫는괄호 **/
