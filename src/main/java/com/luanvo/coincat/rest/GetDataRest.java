package com.luanvo.coincat.rest;

import com.luanvo.coincat.dto.CurrenciesDTO;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/data")
public class GetDataRest {

    private static final Logger logger = LoggerFactory.getLogger(GetDataRest.class);

    @Autowired
    RestTemplate restTemplate;


    @Value("${luan.api-get-price}")
    private String url;

    @Value("${luan.api-get-price-btc}")
    private String url_btc;

    @GetMapping("/get-price")
    public Object getBitCoinPrice(){
        try {
            List<JSONObject> array = restTemplate.getForObject(this.url, List.class);
            return array.get(0);
        }catch (Exception e){e.printStackTrace();
            return e.getCause();
        }
    }

    @GetMapping("/get-price-btc")
    public Object getBitCoinPriceBTC(){
        try {
            CurrenciesDTO[] arrObj = restTemplate.getForObject(this.url_btc, CurrenciesDTO[].class);
            assert arrObj != null;
            return arrObj;
        }catch (Exception e){e.printStackTrace();
            return e.getCause();
        }
    }

    @GetMapping("/get-value/{coin_id}")
    public Object getValueCoin(@Param("coin_id") String coin_id){
        try {
            CurrenciesDTO[] arrObj = restTemplate.getForObject(this.url_btc, CurrenciesDTO[].class);
            assert arrObj != null;
            return arrObj;
        }catch (Exception e){e.printStackTrace();
            return e.getCause();
        }
    }
}
