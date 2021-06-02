package com.luanvo.coincat.service;

import org.json.simple.JSONObject;

public interface CurrencyService {
    JSONObject getListCurrency();
    JSONObject getCurrenyById(String id);
}
