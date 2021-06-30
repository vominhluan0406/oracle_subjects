package com.luanvo.coincat.rest;

import com.luanvo.coincat.service.CurrencyService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coin")
@CrossOrigin(maxAge = 3600)
public class CoinRest {

    private static final Logger logger = LoggerFactory.getLogger(CoinRest.class);

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/info/{id}")
    public JSONObject getLastValueOfCoin(@PathVariable("id") String id) {
        logger.info("GET /coin/info/"+id);
        return currencyService.getCurrenyById(id);
    }


    @GetMapping("/get_list")
    public JSONObject getList() {
        logger.info("GET /get-list/");
        return currencyService.getListCurrency();
    }

    @GetMapping("/get_coin_real_time")
    public JSONObject getCoinRealTime(@RequestParam("id") String id) {
        logger.info("GET /coin/get_coin_real_time?id=" + id);
        return currencyService.getCoinRealTime(id);
    }

}
