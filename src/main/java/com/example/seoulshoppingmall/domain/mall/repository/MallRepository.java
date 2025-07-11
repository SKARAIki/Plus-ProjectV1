package com.example.seoulshoppingmall.domain.mall.repository;
import java.util.List;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.List;
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
      //전체평가(overallRating) 및 업소상태(businessStatus) 필터를 조건으로 모니터링 날짜(monitoringDate) 기준 내림차순 정렬된 상위 10개 업체 리스트

    // 전체평가 및 업소상태 복합적용 필터

    List<Mall> findTop10ByOverallRatingAndBusinessStatusInOrderByMonitoringDateDesc(int overallRating,
                                                                                    Collection<String> businessStatuses);

    default List<Mall> findTop10RatedMallsStatus(int overallRating, Collection<String> businessStatus) {
        return findTop10ByOverallRatingAndBusinessStatusInOrderByMonitoringDateDesc(overallRating, businessStatus);
    }


    // 전체평가 필터 적용
    List<Mall> findTop10ByOverallRatingOrderByMonitoringDateDesc(int overallRating);

    default List<Mall> findTop10RatedMall(int overallRating) {
        return findTop10ByOverallRatingOrderByMonitoringDateDesc(overallRating);
    }

    // 업소상태 필터 적용
    List<Mall> findTop10ByBusinessStatusInOrderByMonitoringDateDesc(Collection<String> businessStatuses);

    default List<Mall> findTop10MallBusinessStatus(Collection<String> businessStatuses) {
        return findTop10ByBusinessStatusInOrderByMonitoringDateDesc(businessStatuses);
    }

}

