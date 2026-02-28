package com.ankit.repository;

import com.ankit.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByStockSymbol(String stockSymbol);
}
