package com.luanvo.coincat.io.response;

import com.luanvo.coincat.io.entity.Trending;
import lombok.Data;

@Data
public class TrendingResponse {
    private String id;
    private String name;
    private String symbol;
    private int market_cap_rank;
    private String thumb;
    private int score;

    public static TrendingResponse parse(Trending trending){
        TrendingResponse rs = new TrendingResponse();
        rs.setId(trending.getCoin_id());
        rs.setName(trending.getName());
        rs.setScore(trending.getScore());
        rs.setSymbol(trending.getSymbol());
        rs.setThumb(trending.getThumb());
        rs.setMarket_cap_rank(trending.getMarket_cap_rank());
        return rs;
    }
}
