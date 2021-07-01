package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.ExchangeDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeDateRepository extends JpaRepository<ExchangeDateEntity,Integer> {

    @Query(value = "SELECT * FROM \"exchanges_date\" WHERE \n" +
            "\"exchanges_date\".\"exchange_id\" = :id \n" +
            "ORDER BY \"exchanges_date\".\"id\" DESC FETCH FIRST 100 ROWS ONLY",nativeQuery = true)
    List<ExchangeDateEntity> findAllExchangeId(@Param("id") String ex_id);
}
