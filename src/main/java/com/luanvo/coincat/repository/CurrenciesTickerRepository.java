package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.CurrenciesTicker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesTickerRepository extends JpaRepository<CurrenciesTicker,Integer> {
}
