package com.luanvo.coincat.service;

import org.json.simple.JSONObject;

public interface GetDataService {
    JSONObject getTrending();
    JSONObject getTickerDateOfCoin();
    JSONObject getCoinOHLC();
}
