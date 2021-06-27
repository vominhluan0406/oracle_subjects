package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.CurrencyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyValueRepository extends JpaRepository<CurrencyValue,Integer> {

    @Query(value = "SELECT * FROM \"currency_value\" WHERE \"currency_id\" = :coin_id",nativeQuery = true)
    List<CurrencyValue> findByID(@Param("coin_id") int coin_id);

    @Query(value = "SELECT \"currency_value\".* FROM \"currency_value\" \n" +
            "LEFT JOIN \"currency\" ON \"currency\".\"id\" = \"currency_value\".\"currency_id\"\n" +
            "WHERE  \"currency\".\"cryptocontrol_coin_id\" = :coin_id\n" +
            "AND \"currency_value\".\"date\" >=  TO_DATE(:start,'YYYY-MM-DD') \n" +
            "AND \"currency_value\".\"date\" <=  TO_DATE(:end,'YYYY-MM-DD')",nativeQuery = true)
    List<CurrencyValue> findAllByRequest(@Param("coin_id") String coin_id,@Param("start") String start,@Param("end") String end);
}
