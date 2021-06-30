package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.Currency;
import com.luanvo.coincat.io.entity.CurrencyOHLCHisEntity;
import com.luanvo.coincat.io.entity.Trending;
import com.luanvo.coincat.repository.CurrencyOHLCHisRepository;
import com.luanvo.coincat.repository.CurrencyRepository;
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

    @Value("${nomics.api_get_ticker_coin}")
    private String ticker_coin_api;

    @Value("${coingecko.get_ohlc}")
    private String coin_ohlc_api;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TrendingRepository trendingRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyOHLCHisRepository currencyOHLCHisRepository;

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

    @Override
    public JSONObject getTickerDateOfCoin() {
        try{

            return RestResponse.success();
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getCoinOHLC() {
        try{
            List<Currency> currencys = currencyRepository.findAll();
            long nowDate = ZDateUtils.getTimeNow();
            currencys.forEach(c->{
                String api = coin_ohlc_api.replace(":ID",c.getOhlc_id());
                LinkedHashMap[] dataAPI =  restTemplate.getForObject(api,LinkedHashMap[].class);
                CurrencyOHLCHisEntity currencyOHLCHisEntity = new CurrencyOHLCHisEntity();
                currencyOHLCHisEntity.setCoin_id(c.getCryptocontrol_coin_id());
                currencyOHLCHisEntity.setHigh(ConvertUtils.toDouble(dataAPI[0].get("high")));
                currencyOHLCHisEntity.setLow(ConvertUtils.toDouble(dataAPI[0].get("low")));
                currencyOHLCHisEntity.setOpen(ConvertUtils.toDouble(dataAPI[0].get("open")));
                currencyOHLCHisEntity.setClose(ConvertUtils.toDouble(dataAPI[0].get("close")));
                currencyOHLCHisEntity.setVolume(ConvertUtils.toDouble(dataAPI[0].get("volume")));
                currencyOHLCHisEntity.setMarket_cap(ConvertUtils.toDouble(dataAPI[0].get("market_cap")));
                currencyOHLCHisEntity.setTime(nowDate);
                currencyOHLCHisRepository.save(currencyOHLCHisEntity);
            });

            return RestResponse.success();
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }


}
