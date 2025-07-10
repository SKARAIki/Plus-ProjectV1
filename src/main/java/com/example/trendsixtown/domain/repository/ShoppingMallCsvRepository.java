package com.example.trendsixtown.domain.repository;

import com.example.trendsixtown.domain.entity.ShoppingMall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingMallCsvRepository extends JpaRepository<ShoppingMall,Long> {


}
