package com.luanvo.coincat.service.imp;

import com.luanvo.coincat.io.entity.Exchanges;
import com.luanvo.coincat.io.response.ExchangeResponse;
import com.luanvo.coincat.repository.ExchangesRepository;
import com.luanvo.coincat.service.ExchangeService;
import com.luanvo.coincat.values.ErrorContent;
import com.luanvo.coincat.values.RestResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeServiceImp implements ExchangeService {

    @Autowired
    ExchangesRepository exchangesRepository;

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
}
