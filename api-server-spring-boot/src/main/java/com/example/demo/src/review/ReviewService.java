package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.*;

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
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;

    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService){
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
        this.jwtService = jwtService;
    }








}/** reviewService 끝나는 괄호*/
