package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "\"currency_value_real_time\"",schema = "luan")
@Entity
public class CurrencyValueRealTimeEnity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "CurrencyValueRealTimeEnity_SEQ")
    @SequenceGenerator(name = "CurrencyValueRealTimeEnity_SEQ",allocationSize = 1,sequenceName = "\"currency_value_real_time_seq\"")
    @Column(name="\"id\"")
    private int id;

    @Column(name = "\"coin_id\"")
    private String coin_id;

    @Column(name = "\"price_date\"")
    private long price_date;

    @Column(name = "\"price\"")
    private double price;
}
