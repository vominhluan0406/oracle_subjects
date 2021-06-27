package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "luan",name = "\"currency_value\"")
public class CurrencyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "CurrencyValueTimePeriod_SEQ")
    @SequenceGenerator(name = "CurrencyValueTimePeriod_SEQ",allocationSize = 1,sequenceName = "\"currency_value_time_seq\"")
    @Column(name="\"id\"")
    private int id;

    @Column(name = "\"currency_id\"")
    private int currency_id;

    @Column(name = "\"date\"")
    private Date date;

    @Column(name = "\"low\"")
    private double low;

    @Column(name = "\"open\"")
    private double open;

    @Column(name = "\"close\"")
    private double close;

    @Column(name = "\"high\"")
    private double high;

    @Column(name = "\"volume\"")
    private double volume;

    @Column(name = "\"adj_close\"")
    private double adj_close;
}
