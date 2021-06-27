package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"trending\"",schema = "luan")
public class Trending {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Trending_SEQ")
    @SequenceGenerator(sequenceName = "\"trending_seq\"",allocationSize = 1,name = "Trending_SEQ")
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"coin_id\"")
    private String coin_id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"symbol\"")
    private String symbol;

    @Column(name = "\"market_cap_rank\"")
    private int market_cap_rank;

    @Column(name = "\"price_btc\"")
    private double price_btc;

    @Column(name = "\"score\"")
    private int score;

    @Column(name = "\"date\"")
    private long date;

    @Column(name = "\"thumb\"")
    private String thumb;

}
