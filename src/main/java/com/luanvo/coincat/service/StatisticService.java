package com.luanvo.coincat.service;

import com.luanvo.coincat.io.request.GetStatisticCoinRequest;
import org.json.simple.JSONObject;

public interface StatisticService {
    JSONObject  getStatisticOfCoin(GetStatisticCoinRequest request);

    JSONObject getStatisticOfAll(String coin);
}
