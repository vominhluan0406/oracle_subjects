package com.luanvo.coincat.response;

import lombok.Data;

@Data
public class StatisticDataCointResponse {

    private String date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume_btc;
    private Double volume_usd;

}
