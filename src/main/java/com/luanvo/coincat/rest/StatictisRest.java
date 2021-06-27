package com.luanvo.coincat.rest;

import com.luanvo.coincat.io.request.GetStatisticCoinRequest;
import com.luanvo.coincat.service.StatisticService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistic")
@CrossOrigin(maxAge = 3600)
public class StatictisRest {

    private static final Logger logger = LoggerFactory.getLogger(StatictisRest.class);

    @Autowired
    StatisticService statisticService;

    @GetMapping("/get")
    public JSONObject getStatistic(GetStatisticCoinRequest request) {
        logger.info("GET /statistic/get " + request);
        return statisticService.getStatisticOfCoin(request);
    }

    @GetMapping("/get/{coin}")
    public JSONObject getStatistic(@PathVariable("coin") String coin) {
        logger.info("GET /statistic/get " + coin);
        return statisticService.getStatisticOfAll(coin);
    }
}
