package com.luanvo.coincat.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExchangeRate {
    private int id;
    private String currency;
    private Double rate;
    private Date timestamp;
}
