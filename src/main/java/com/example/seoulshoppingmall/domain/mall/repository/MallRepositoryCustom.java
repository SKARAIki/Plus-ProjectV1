package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallRepositoryCustom {
    List<Mall> searchMalls(
            String cursorDate, Long cursorId, int size, Integer overallRating, String businessStatus
    );
    void bulkSave(List<Mall> malls);
}
