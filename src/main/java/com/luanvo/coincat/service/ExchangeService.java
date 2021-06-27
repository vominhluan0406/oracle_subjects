package com.luanvo.coincat.service;

import org.json.simple.JSONObject;

public interface ExchangeService {
    JSONObject getDetail(String id);

    JSONObject getList();
}
