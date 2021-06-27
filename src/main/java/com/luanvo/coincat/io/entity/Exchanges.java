package com.luanvo.coincat.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "\"exchanges\"" , schema = "luan")
public class Exchanges {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Exchanges_SEQ")
    @SequenceGenerator(sequenceName = "\"exchanges_seq\"",allocationSize = 1,name = "Exchanges_SEQ")
    @Column(name = "\"id\"")
    private int id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"country\"")
    private String country;

    @Column(name = "\"year_established\"")
    private String year_established;

    @Column(name = "\"url\"")
    private String url;

    @Column(name = "\"image\"")
    private String image;

    @Column(name = "\"description\"")
    private String description;

    @Column(name = "\"ex_id\"")
    private String ex_id;

    @Column(name = "\"create_at\"")
    private Date create_at;

}
