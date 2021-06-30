package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.CurrencyValueRealTimeEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyValueRealTimeRepository extends JpaRepository<CurrencyValueRealTimeEnity,Integer> {

    @Query(value = "SELECT * FROM (SELECT * FROM \"currency_value_real_time\" " +
            "WHERE \"coin_id\" = :id ORDER BY \"id\" DESC) WHERE ROWNUM = 1" ,nativeQuery = true)
    CurrencyValueRealTimeEnity getLastOfCoin(@Param("id") String id);
}
