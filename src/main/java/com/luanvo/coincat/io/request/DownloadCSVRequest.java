package com.luanvo.coincat.io.request;

import lombok.Data;

@Data
public class DownloadCSVRequest {
    private String coin_id;
    private long start;
    private long end;
}
