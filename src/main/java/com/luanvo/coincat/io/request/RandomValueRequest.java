package com.luanvo.coincat.io.request;

import lombok.Data;

@Data
public class RandomValueRequest {

    private long start_date;
    private long end_date;
    private String coin_id;
    private double average;
    private int number_of_multiply;
    private double market_cap;
}
