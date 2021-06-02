package com.luanvo.coincat.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(schema = "LUAN",name = "\"currency_value\"")
public class CurrencyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Currency_Value_SEQ")
    @SequenceGenerator(sequenceName = "\"currency_value_seq\"",allocationSize = 1,name = "Currency_Value_SEQ")
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"currency_id\"")
    private int currency_id;

    @Column(name = "\"price\"")
    private Double price;

    @Column(name = "\"price_date\"")
    private Date price_date;

    @Column(name = "\"price_timestamp\"")
    private Date price_timestamp;

    @Column(name = "\"market_cap\"")
    private Long market_cap;

    @Column(name = "\"market_cap_dominance\"")
    private Double market_cap_dominance;

}
