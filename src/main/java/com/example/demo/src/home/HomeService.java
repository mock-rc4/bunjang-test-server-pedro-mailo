package com.example.demo.src.home;


import com.example.demo.config.BaseException;
import com.example.demo.src.home.model.*;
import com.example.demo.src.home.HomeDao;
import com.example.demo.src.home.HomeProvider;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;



@Service
public class HomeService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final  HomeDao homeDao;
    private final  HomeProvider homeProvider;
    private final JwtService jwtService;

    @Autowired
    public HomeService(HomeDao homeDao, HomeProvider homeProvider, JwtService jwtService){
        this.homeDao =homeDao;
        this.homeProvider =homeProvider;
        this.jwtService = jwtService;
    }






}
