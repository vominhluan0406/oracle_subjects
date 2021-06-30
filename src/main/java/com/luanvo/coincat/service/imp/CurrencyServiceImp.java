package com.luanvo.coincat.service.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.luanvo.coincat.io.entity.Currency;
import com.luanvo.coincat.io.entity.CurrencyOHLCHisEntity;
import com.luanvo.coincat.io.entity.CurrencyValueRealTimeEnity;
import com.luanvo.coincat.io.response.CurrencyDetailResponse;
import com.luanvo.coincat.repository.CurrencyOHLCHisRepository;
import com.luanvo.coincat.repository.CurrencyRepository;
import com.luanvo.coincat.io.response.CoinInfoResponse;
import com.luanvo.coincat.repository.CurrencyValueRealTimeRepository;
import com.luanvo.coincat.service.CurrencyService;
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
public class CurrencyServiceImp implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyValueRealTimeRepository currencyValueRealTimeRepository;

    @Autowired
    CurrencyOHLCHisRepository currencyOHLCHisRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public JSONObject getListCurrency() {
        JSONObject rs = new JSONObject();
        try {
            List<CoinInfoResponse> lsRs = new ArrayList<>();
            List<Currency> lsCurrency = currencyRepository.findAll();

            lsCurrency.forEach(o -> {
                CoinInfoResponse coinInfoResponse = new CoinInfoResponse();
                coinInfoResponse.setCoin_id(o.getCryptocontrol_coin_id());
                coinInfoResponse.setName(o.getName());
                coinInfoResponse.setLogo(o.getLogo_url());
                lsRs.add(coinInfoResponse);
            });
            rs.put("data", lsRs);
            rs.put("total", lsCurrency.size());
            return RestResponse.success(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getCurrenyById(String id) {
        JSONObject rs = new JSONObject();
        try {
            Currency currency = currencyRepository.findByCryptoControlCoinId(id);
            if (currency == null) {
                return RestResponse.error(ErrorContent.CURRENCY_NOT_FOUND.getMsg());
            }
            CurrencyOHLCHisEntity currencyOHLCHisEntity = currencyOHLCHisRepository.getLastByID(id);
            rs.put("data", new CurrencyDetailResponse(currency,currencyOHLCHisEntity));
            return RestResponse.success(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getCoinRealTime(String id) {
        try{
            JSONObject response = new JSONObject();
            CurrencyValueRealTimeEnity currencyValueRealTimeEnity = currencyValueRealTimeRepository.getLastOfCoin(id);
            response.put("data",currencyValueRealTimeEnity);
            return RestResponse.success(response);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }
}
