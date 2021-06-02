package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.entity.Currency;
import com.luanvo.coincat.repository.CurrencyRepository;
import com.luanvo.coincat.response.CoinInfoResponse;
import com.luanvo.coincat.service.CurrencyService;
import com.luanvo.coincat.values.ErrorContent;
import com.luanvo.coincat.values.RestResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImp implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

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
            rs.put("data", currency);
            return RestResponse.success(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }
}
