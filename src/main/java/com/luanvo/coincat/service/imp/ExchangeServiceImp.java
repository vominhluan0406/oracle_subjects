package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.ExchangeDateEntity;
import com.luanvo.coincat.io.entity.Exchanges;
import com.luanvo.coincat.io.response.ExchangeResponse;
import com.luanvo.coincat.repository.ExchangeDateRepository;
import com.luanvo.coincat.repository.ExchangesRepository;
import com.luanvo.coincat.service.ExchangeService;
import com.luanvo.coincat.utils.ConvertUtils;
import com.luanvo.coincat.values.ErrorContent;
import com.luanvo.coincat.values.RestResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ExchangeServiceImp implements ExchangeService {

    @Value("${coingecko.exchanges_date}")
    private String api_exchanges;

    @Value("${url.image.coin}")
    private String url_img;

    @Autowired
    ExchangesRepository exchangesRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExchangeDateRepository exchangeDateRepository;

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
            LinkedHashMap obj = restTemplate.getForObject(api_exchanges+ex_id,LinkedHashMap.class);
            List<LinkedHashMap> tickers = (List<LinkedHashMap>) obj.get("tickers");

            Date now = new Date();
            tickers.forEach(ticker->{
                ExchangeDateEntity entity = new ExchangeDateEntity();
                entity.setExchange_id(ex_id);
                entity.setCoin(ticker.get("coin_id").toString().toUpperCase(Locale.ROOT).replace("-"," "));
                entity.setDate(now);
                entity.setImage(url_img.replace(":ID",ticker.get("base").toString().toLowerCase(Locale.ROOT)));
                entity.setBid_ask_spread_percentage(ConvertUtils.toDouble(ticker.getOrDefault("bid_ask_spread_percentage","0")));
                entity.setVolume(ConvertUtils.toDouble(ticker.getOrDefault("volume","0")));
                entity.setLast(ConvertUtils.toDouble(ticker.getOrDefault("last","0")));
                entity.setUrl_chart(ticker.get("trade_url").toString());
                exchangeDateRepository.save(entity);
            });
            return RestResponse.success();
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getTickers(String ex_id) {
        try{
            JSONObject data = new JSONObject();

            List<ExchangeDateEntity> dataDB = exchangeDateRepository.findAllExchangeId(ex_id);

            data.put("data",dataDB);
            return RestResponse.success(data);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }


}
