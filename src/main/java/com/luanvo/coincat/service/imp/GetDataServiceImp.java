package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.Trending;
import com.luanvo.coincat.repository.TrendingRepository;
import com.luanvo.coincat.service.GetDataService;
import com.luanvo.coincat.utils.ConvertUtils;
import com.luanvo.coincat.utils.ZDateUtils;
import com.luanvo.coincat.values.RestResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class GetDataServiceImp implements GetDataService {

    @Value("${coingecko.trending}")
    private String trending_api;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TrendingRepository trendingRepository;

    @Override
    public JSONObject getTrending() {
        try {
            JSONObject dataFromAPI = restTemplate.getForObject(trending_api, JSONObject.class);
            List<LinkedHashMap> coins = (List<LinkedHashMap>) dataFromAPI.get("coins");

            long date = ZDateUtils.getTimeNow();

            coins.forEach(o->{
                LinkedHashMap item = (LinkedHashMap) o.get("item");
                Trending trending = new Trending();
                trending.setCoin_id((item.get("id").toString()));
                trending.setDate(date);
                trending.setThumb(item.get("thumb").toString());
                trending.setName(item.get("name").toString());
                trending.setPrice_btc(ConvertUtils.toDouble(item.get("price_btc")));
                trending.setScore(ConvertUtils.toInt(item.get("score")));
                trending.setSymbol(item.get("symbol").toString());
                trending.setMarket_cap_rank(ConvertUtils.toInt(item.get("market_cap_rank")));
                trendingRepository.save(trending);
            });

            return RestResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }
}
