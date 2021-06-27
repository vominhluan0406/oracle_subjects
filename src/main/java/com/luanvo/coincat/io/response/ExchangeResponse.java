package com.luanvo.coincat.io.response;

import com.luanvo.coincat.io.entity.Exchanges;
import lombok.Data;

@Data
public class ExchangeResponse {
    private String id;
    private String name;
    private String country;
    private String year_established;
    private String url;
    private String image;
    private String description;

    public static ExchangeResponse parse(Exchanges exchanges) {
        ExchangeResponse response = new ExchangeResponse();
        response.setCountry(exchanges.getCountry());
        response.setDescription(exchanges.getDescription());
        response.setId(exchanges.getEx_id());
        response.setImage(exchanges.getImage());
        response.setUrl(exchanges.getUrl());
        response.setYear_established(exchanges.getYear_established());
        response.setName(exchanges.getName());
        return response;
    }
}
