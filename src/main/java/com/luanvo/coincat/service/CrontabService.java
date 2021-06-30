package com.luanvo.coincat.service;

import com.luanvo.coincat.config.RestTemplateConfig;
import com.luanvo.coincat.io.entity.Currency;
import com.luanvo.coincat.io.entity.CurrencyValueRealTimeEnity;
import com.luanvo.coincat.repository.CurrencyRepository;
import com.luanvo.coincat.repository.CurrencyValueRealTimeRepository;
import com.luanvo.coincat.utils.ConvertUtils;
import com.luanvo.coincat.utils.ZDateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class CrontabService {

    private Logger logger = LoggerFactory.getLogger(CrontabService.class);

    @Value("${nomics.api_get_ticker_coin}")
    private String api_get_currency_ticker;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GetDataService getDataService;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyValueRealTimeRepository currencyValueRealTimeRepository;

    @Scheduled(cron = "0 0 0 ? * * ")
    public void getTrending() {
        try{
            logger.info("Crontab : getTrending "+new Date());
            getDataService.getTrending();
            getDataService.getCoinOHLC();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 10000)
    public void getCurrencyPriceTimeStamp() {
        try{
            logger.info("Crontab :  getCurrencyPriceTimeStamp " +new Date());
            long nowDate = ZDateUtils.getTimeNow();
            List<Currency> currencys = currencyRepository.findAll();
            currencys.forEach(c->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String api = api_get_currency_ticker.replace(":ID",c.getSymbol());
                LinkedHashMap[] dataAPI =  restTemplate.getForObject(api,LinkedHashMap[].class);
                CurrencyValueRealTimeEnity currencyValueRealTimeEnity = new CurrencyValueRealTimeEnity();
                currencyValueRealTimeEnity.setPrice(ConvertUtils.toDouble(dataAPI[0].get("price")));
                currencyValueRealTimeEnity.setCoin_id(c.getCryptocontrol_coin_id());
                currencyValueRealTimeEnity.setPrice_date(nowDate);
                currencyValueRealTimeRepository.save(currencyValueRealTimeEnity);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
