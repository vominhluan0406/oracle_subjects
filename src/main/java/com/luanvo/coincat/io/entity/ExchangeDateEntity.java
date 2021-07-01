package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "\"exchanges_date\"",schema = "luan")
public class ExchangeDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ExchangeDateEntity_SEQ")
    @SequenceGenerator(sequenceName = "\"exchanges_date_seq\"",allocationSize = 1,name = "ExchangeDateEntity_SEQ")
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"coin\"")
    private String coin;

    @Column(name = "\"exchange_id\"")
    private String exchange_id;

    @Column(name = "\"image\"")
    private String image;

    @Column(name = "\"date\"")
    private Date date;

    @Column(name = "\"volume\"")
    private double volume;

    @Column(name = "\"bid_ask_spread_percentage\"")
        private double bid_ask_spread_percentage;

    @Column(name = "\"last\"")
    private double last;

    @Column(name = "\"url_chart\"")
    private String url_chart;
}
