package com.group22.dao.repositories;

import com.group22.dao.models.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketDataRepository extends JpaRepository<MarketData, Long> {
    @Query(value = "SELECT * FROM market_data WHERE ticker=?1 ORDER BY timestamp DESC LIMIT 2", nativeQuery = true)
    List<MarketData> findByTicker(String ticker);

}
