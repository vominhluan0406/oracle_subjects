package com.luanvo.coincat.io.request;

import lombok.Data;

@Data
public class GetStatisticCoinRequest {
    private String coin;
    private long start;
    private long end;
}
