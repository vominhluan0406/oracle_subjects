package com.luanvo.coincat.repository;

import com.luanvo.coincat.io.entity.Trending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendingRepository extends JpaRepository<Trending, Integer> {

    @Query(value = "SELECT * FROM \"trending\" WHERE \"date\" >= :today",nativeQuery = true)
    List<Trending> findToday(@Param("today") long today);
}
