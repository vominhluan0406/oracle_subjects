package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.CurrencyOHLCHisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyOHLCHisRepository extends JpaRepository<CurrencyOHLCHisEntity,Integer> {

    @Query(value = "SELECT * FROM (SELECT * FROM \"currency_ohlc_his\" WHERE \"coin_id\" = :id " +
            "ORDER BY \"id\" DESC ) WHERE ROWNUM = 1",nativeQuery = true)
    CurrencyOHLCHisEntity getLastByID(@Param("id") String id);
}
