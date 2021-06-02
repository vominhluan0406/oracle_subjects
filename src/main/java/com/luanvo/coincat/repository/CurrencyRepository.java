package com.luanvo.coincat.repository;

import com.luanvo.coincat.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    @Query(value = "SELECT * FROM \"currency\" WHERE \"currency\".\"cryptocontrol_coin_id\" = :id",nativeQuery = true)
    Currency findByCryptoControlCoinId(@Param("id") String id);
}
