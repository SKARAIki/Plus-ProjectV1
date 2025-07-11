package com.example.seoulshoppingmall.domain.mall.repository;
import java.util.List;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MallRepository extends JpaRepository<Mall, Long>, MallRepositoryCustom {

    List<Mall> findTop10ByOverallRating(
            Integer overallRating, Pageable pageable
    );
    List<Mall> findTop10ByBusinessStatus(
            String businessStatus, Pageable pageable
    );
    List<Mall> findTop10ByOverallRatingAndBusinessStatus(
            Integer overallRating, String businessStatus, Pageable pageable
    );
}

