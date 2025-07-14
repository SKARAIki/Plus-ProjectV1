package com.example.seoulshoppingmall.csv.serivce;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;

import java.util.List;

public interface MallCsvRepositoryCustom {
    void bulkSave(List<Mall> malls);
}
