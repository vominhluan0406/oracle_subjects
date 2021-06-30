package com.luanvo.coincat.rest;

import com.luanvo.coincat.service.ExchangeService;
import com.luanvo.coincat.service.GetDataService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestRest {
    private static final String token = "luanvo";

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    GetDataService getDataService;

    @GetMapping("/api_exchanges")
    public JSONObject getApiExchanges(@RequestParam("id") String id){
        return exchangeService.getToday(id);
    }

    @GetMapping("/ohlc")
    public JSONObject getAPIOHLC(){
        return getDataService.getCoinOHLC();
    }
}
