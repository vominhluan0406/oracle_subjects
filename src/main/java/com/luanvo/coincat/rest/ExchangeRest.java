package com.luanvo.coincat.rest;

import com.luanvo.coincat.service.ExchangeService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
@CrossOrigin(maxAge = 3600)
public class ExchangeRest {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRest.class);

    @Autowired
    ExchangeService exchangeService;

    @GetMapping("/detail")
    public JSONObject getDetail(@RequestParam String id) {
        logger.info("GET /exchange/detail?id=" + id);
        return exchangeService.getDetail(id);
    }

    @GetMapping("/get_list")
    public JSONObject getList() {
        logger.info("GET /exchange/get_list");
        return exchangeService.getList();
    }

    @GetMapping("/get_today")
    public JSONObject getList(@RequestParam("id") String ex_id) {
        logger.info("GET /exchange/get_today?id=" + ex_id);
        return exchangeService.getToday(ex_id);
    }
}
