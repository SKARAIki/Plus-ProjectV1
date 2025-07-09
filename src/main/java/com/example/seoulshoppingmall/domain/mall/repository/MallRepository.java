package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MallRepository extends JpaRepository<Mall, Long>, MallRepositoryCustom {

    List<Mall> findAllByOrderByMonitoringDateDesc();

    List<Mall> findByOverallRatingOrderByMonitoringDateDesc(int overallRating);

    List<Mall> findByBusinessStatusOrderByMonitoringDateDesc(String businessStatus);

    List<Mall> findByOverallRatingAndBusinessStatusOrderByMonitoringDateDesc(int overallRating, String businessStatus);
}
