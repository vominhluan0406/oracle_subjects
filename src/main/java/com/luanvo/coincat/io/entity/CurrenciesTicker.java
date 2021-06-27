package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "\"currencies_ticker\"", schema = "luan")
public class CurrenciesTicker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "CurrenciesTicker_SEQ")
    @SequenceGenerator(sequenceName = "\"currencies_ticker_seq\"",allocationSize = 1,name = "CurrenciesTicker_SEQ")
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"coin_id\"")
    private int coin_id;

    @Column(name = "\"price\"")
    private double price;

    @Column(name = "\"price_date\"")
    private Date price_date;

    @Column(name = "\"price_timestamp\"")
    private long price_timestamp;

    @Column(name = "\"market_cap\"")
    private double market_cap;

}
