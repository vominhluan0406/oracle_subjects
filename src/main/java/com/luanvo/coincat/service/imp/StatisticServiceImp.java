package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.Currency;
import com.luanvo.coincat.io.entity.CurrencyValue;
import com.luanvo.coincat.io.request.GetStatisticCoinRequest;
import com.luanvo.coincat.io.response.StatisticDataCoinResponse;
import com.luanvo.coincat.repository.CurrencyRepository;
import com.luanvo.coincat.repository.CurrencyValueRepository;
import com.luanvo.coincat.service.StatisticService;
import com.luanvo.coincat.utils.ZDateUtils;
import com.luanvo.coincat.values.ErrorContent;
import com.luanvo.coincat.values.RestResponse;
import com.luanvo.coincat.values.TimeValue;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class StatisticServiceImp implements StatisticService {

    @Autowired
    CurrencyValueRepository currencyValueRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public JSONObject getStatisticOfCoin(GetStatisticCoinRequest request) {
        try{
            JSONObject rs = new JSONObject();
            List<StatisticDataCoinResponse> lsRs = new ArrayList<>();

            String start = ZDateUtils.getDateStr(request.getStart());
            String end = ZDateUtils.getDateStr(request.getEnd());
            String coin_id = request.getCoin();

            List<CurrencyValue> lsDB = currencyValueRepository.findAllByRequest(coin_id,start,end);
            lsDB.forEach(o-> lsRs.add(StatisticDataCoinResponse.parse(o)));

            rs.put("data",lsRs);
            return RestResponse.success(rs);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getStatisticOfAll(String coin) {
        try {
            JSONObject response = new JSONObject();

            Currency currency = currencyRepository.findByCryptoControlCoinId(coin);
            if(currency == null){
                return RestResponse.error(ErrorContent.CURRENCY_NOT_FOUND.getMsg());
            }

            List<CurrencyValue> lsDB = currencyValueRepository.findByID(currency.getId());

            response.put("data",lsDB);
            return RestResponse.success(response);
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }
}
