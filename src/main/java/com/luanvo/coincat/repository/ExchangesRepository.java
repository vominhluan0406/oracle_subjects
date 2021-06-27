package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.Exchanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangesRepository extends JpaRepository<Exchanges,Integer> {
}
