package com.luanvo.coincat.service;

import com.luanvo.coincat.config.RestTemplateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class CrontabService {

    private Logger logger = LoggerFactory.getLogger(CrontabService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GetDataService getDataService;

    @Scheduled(cron = "0 0 0 ? * * ")
    public void getTrending() {
        try{
            logger.info("Crontab : getTrending "+new Date());
            getDataService.getTrending();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
