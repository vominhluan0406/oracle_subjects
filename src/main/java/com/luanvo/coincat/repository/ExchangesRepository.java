package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.Exchanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangesRepository extends JpaRepository<Exchanges,Integer> {

    @Query(value = "SELECT * FROM \"exchanges\" WHERE \"exchanges\".\"ex_id\" = :id",nativeQuery = true)
    Exchanges findByStrId(@Param("id") String id);
}
