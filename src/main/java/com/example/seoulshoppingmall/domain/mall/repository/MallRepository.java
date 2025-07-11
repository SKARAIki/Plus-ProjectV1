package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MallRepository extends JpaRepository<Mall, Long> {

}
