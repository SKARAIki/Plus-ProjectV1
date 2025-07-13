package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.ShoppingMall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingMallCsvRepository extends JpaRepository<ShoppingMall,Long> {


}
