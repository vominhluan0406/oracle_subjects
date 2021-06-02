package com.luanvo.coincat.request;

import lombok.Data;

@Data
public class RandomValueRequest {

    private long start_date;
    private long end_date;
    private String coin_id;
    private double average;

}
