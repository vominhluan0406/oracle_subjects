package com.luanvo.coincat.rest;

import com.luanvo.coincat.service.GetDataService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/get_data")
public class GetDataRest {

    private static final Logger logger = LoggerFactory.getLogger(GetDataRest.class);

    @Autowired
    GetDataService getDataService;

    @GetMapping("/trending")
    public JSONObject getTrending(){
        logger.info("GET /get_data/trending");
        return getDataService.getTrending();
    }
}
