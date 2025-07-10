package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

//- 전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(2개 필터 동시 적용, 각각 1개씩 적용) 상위 10개 리스트 보여주기를 만들어요. 조회 조건은 다음을 참고해 주세요.
//    - ‘전체평가’ 필터 조회
//        - ‘전체평가’는 0점 ~ 3점 으로 이루어져 있고 점수를 입력하여 해당 업체 리스트만 조회
//        - 예) ‘전체평가’가 3점인 경우만 리스트 조회
//    - ‘업소상태’ 필터 조회
//        - `사이트운영중단`, `휴업중`, `광고용(홍보용)`, `등록정보불일치`, `사이트폐쇄`, `영업중`, `확인안됨` 상태 중 1개를 선택하여 해당 업체 리스트만 조회
//    - ‘모니터링날짜’의 내림차순
//-
//
public interface MallRepository extends JpaRepository<Mall, Long> {
    List<Mall> findTop10OverallRating(Integer overallRating);
    List<Mall> findTop10businessStatus(String businessStatus);
    List<Mall> findTop10ByOverallRatingAndBusinessStatus(
            Integer overallRating, String businessStatus, Pageable pageable
    );
}
