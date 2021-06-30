package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "luan",name = "\"currency\"")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Currency_SEQ")
    @SequenceGenerator(sequenceName = "\"currency_seq\"",allocationSize = 1,name = "Currency_SEQ")
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"symbol\"")
    private String symbol;

    @Column(name = "\"logo_url\"")
    private String logo_url;

    @Column(name = "\"description\"")
    private String description;

    @Column(name = "\"website_url\"")
    private String  website_url;

    @Column(name = "\"cryptocontrol_coin_id\"")
    private String cryptocontrol_coin_id;

    @Column(name = "\"ohlc_id\"")
    private String ohlc_id;
}
