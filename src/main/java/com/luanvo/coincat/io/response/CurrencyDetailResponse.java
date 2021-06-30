package com.luanvo.coincat.io.response;

import com.luanvo.coincat.io.entity.Currency;
import com.luanvo.coincat.io.entity.CurrencyOHLCHisEntity;
import lombok.Data;

import javax.persistence.*;

@Data
public class CurrencyDetailResponse {

    private int id;
    private String name;
    private String symbol;
    private String logo_url;
    private String description;
    private String  website_url;
    private String cryptocontrol_coin_id;
    private String ohlc_id;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double market_cap;


    public CurrencyDetailResponse(Currency currency,CurrencyOHLCHisEntity ohlc){
        this.id = currency.getId();
        this.name = currency.getName();
        this.symbol = currency.getSymbol();
        this.logo_url = currency.getLogo_url();
        this.description = currency.getDescription();
        this.website_url = currency.getWebsite_url();
        this.cryptocontrol_coin_id = currency.getCryptocontrol_coin_id();
        this.ohlc_id = currency.getOhlc_id();
        this.high = ohlc.getHigh();
        this.close = ohlc.getClose();
        this.open = ohlc.getOpen();
        this.low = ohlc.getLow();
        this.volume = ohlc.getVolume();
        this.market_cap = ohlc.getMarket_cap();
    }

}
