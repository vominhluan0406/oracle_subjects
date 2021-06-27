package com.luanvo.coincat.io.dto;

import lombok.Data;

@Data
public class CurrenciesDTO {
    private String id;
    private String currency;
    private String name;
    private String price;
    private String price_date;
    private String price_timestamp;
    private String market_cap;
    private String max_supply;
    private String rank;
}
