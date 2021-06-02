package com.luanvo.coincat.service;

import org.json.simple.JSONObject;

public interface CurrencyValueService {
    JSONObject getCurrencyValueCurrent(String coin_id);
    JSONObject getLastWeekCurValue(String coin_id);
    JSONObject getMonthValue(int month,int year,String coid_id);
}
