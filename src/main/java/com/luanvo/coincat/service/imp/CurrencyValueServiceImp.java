package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.entity.CurrencyValue;
import com.luanvo.coincat.repository.CurrencyValueRepository;
import com.luanvo.coincat.service.CurrencyValueService;
import com.luanvo.coincat.utils.ZDateUtils;
import com.luanvo.coincat.values.RestResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyValueServiceImp implements CurrencyValueService {

    @Autowired
    CurrencyValueRepository currencyValueRepo;

    @Override
    public JSONObject getCurrencyValueCurrent(String coin_id) {
        try {
            JSONObject rs = new JSONObject();
            long lastWeekTime = ZDateUtils.getTimeLastWeek();
            long nowTime = ZDateUtils.getTimeNow();
            List<CurrencyValue> lsDB = currencyValueRepo.findLastWeek(coin_id, nowTime, lastWeekTime);

            rs.put("data", lsDB);

            return RestResponse.success(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error(e.getMessage());
        }
    }

    @Override
    public JSONObject getLastWeekCurValue(String coin_id) {
        return null;
    }

    @Override
    public JSONObject getMonthValue(int month, int year, String coin_id) {
        return null;
    }

}
