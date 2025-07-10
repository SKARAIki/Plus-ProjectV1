package com.example.trendsixtown.repository;

import com.example.trendsixtown.entity.ShoppingMall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingMallCsvRepository extends JpaRepository<ShoppingMall,Long> {


}
