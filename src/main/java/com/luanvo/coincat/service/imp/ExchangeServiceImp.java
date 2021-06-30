package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.Exchanges;
import com.luanvo.coincat.io.response.ExchangeResponse;
import com.luanvo.coincat.repository.ExchangesRepository;
import com.luanvo.coincat.service.ExchangeService;
import com.luanvo.coincat.values.ErrorContent;
import com.luanvo.coincat.values.RestResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ExchangeServiceImp implements ExchangeService {

    @Value("${coingecko.exchanges_date}")
    private String api_exchanges;

    @Autowired
    ExchangesRepository exchangesRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public JSONObject getDetail(String id) {
        try {
            JSONObject data = new JSONObject();
            Exchanges exchanges = exchangesRepository.findByStrId(id);
            if (exchanges == null) {
                return RestResponse.error(ErrorContent.EXCHANGE_NOT_FOUND.getMsg());
            }
            data.put("data", ExchangeResponse.parse(exchanges));
            return RestResponse.success(data);
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getList() {
        try {
            JSONObject data = new JSONObject();
            List<ExchangeResponse> lsData = new ArrayList<>();
            List<Exchanges> lsDB = exchangesRepository.findAll();
            lsDB.forEach(o -> lsData.add(ExchangeResponse.parse(o)));
            data.put("data", lsData);
            return RestResponse.success(data);
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getToday(String ex_id) {
        try{
            JSONObject data = new JSONObject();
            LinkedHashMap obj = restTemplate.getForObject(api_exchanges+ex_id,LinkedHashMap.class);

            data.put("data",obj);
            return RestResponse.success(data);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }
}
