package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"currency_ohlc_his\"",schema = "luan")
public class CurrencyOHLCHisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "CurrencyOHLCHisEntity_SEQ")
    @SequenceGenerator(sequenceName = "\"currency_ohlc_his_seq\"",allocationSize = 1,name = "CurrencyOHLCHisEntity_SEQ")
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"coin_id\"")
    private String coin_id;

    @Column(name = "\"time\"")
    private long time;

    @Column(name = "\"open\"")
    private double open;

    @Column(name = "\"high\"")
    private double high;

    @Column(name = "\"low\"")
    private double low;

    @Column(name = "\"close\"")
    private double close;

    @Column(name = "\"volume\"")
    private double volume;

    @Column(name = "\"market_cap\"")
    private double market_cap;
}
