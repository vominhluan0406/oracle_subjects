package com.luanvo.coincat.repository;

import com.luanvo.coincat.entity.CurrencyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyValueRepository extends JpaRepository<CurrencyValue, Long> {

    @Query(value = "SELECT * FROM \"currency_value\"\n" +
            "LEFT JOIN \"currency\"\n" +
            "ON \"currency\".\"id\" = \"currency_value\".\"currency_id\"\n" +
            "WHERE \"currency\".\"cryptocontrol_coin_id\" = ?1\n" +
            "AND (\"price_date\" >= ?3 AND \"price_date\" < ?2)", nativeQuery = true)
    List<CurrencyValue> findLastWeek(String coin_id, long now, long lastweek);
}
