package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;

import java.util.List;

public interface MallRepositoryCustom {
    List<Mall> searchMalls(String cursorDate, Long cursorId, int size, Integer overallRating, String businessStatus);
}
