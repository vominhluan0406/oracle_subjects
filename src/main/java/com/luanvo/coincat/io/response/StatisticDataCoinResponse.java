package com.luanvo.coincat.io.response;

import com.luanvo.coincat.io.entity.CurrencyValue;
import lombok.Data;

@Data
public class StatisticDataCoinResponse {

    private long date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
    private Double volume_total;

    public static StatisticDataCoinResponse parse(CurrencyValue currencyValue){
        StatisticDataCoinResponse rs = new StatisticDataCoinResponse();
        rs.setDate(currencyValue.getDate().getTime());
        rs.setHigh(currencyValue.getHigh());
        rs.setClose(currencyValue.getClose());
        rs.setVolume(currencyValue.getVolume());
        rs.setOpen(currencyValue.getOpen());
        rs.setVolume_total(currencyValue.getAdj_close());
        rs.setLow(currencyValue.getLow());
        return rs;
    }

}
