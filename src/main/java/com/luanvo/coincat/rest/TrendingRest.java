package com.luanvo.coincat.rest;

import com.luanvo.coincat.service.TrendingService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trending")
@CrossOrigin(maxAge = 3600)
public class TrendingRest {

    private static final Logger logger = LoggerFactory.getLogger(TrendingRest.class);

    @Autowired
    TrendingService trendingService;

    @GetMapping("/get_today")
    public JSONObject getTrendingToday() {
        logger.info("GET /trending/get_today");
        return trendingService.getToday();
    }
}
