package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.entity.QMall;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MallRepositoryImpl implements MallRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public MallRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Mall> searchMalls(
            String cursorDate, Long cursorId, int size, Integer overallRating, String businessStatus
    ) {
        QMall mall = QMall.mall;
        BooleanBuilder builder = new BooleanBuilder();

        //커서 조건 Optional처리
        Optional.ofNullable(cursorDate)
                .flatMap(date -> Optional.ofNullable(cursorId)
                        .map(id ->mall.monitoringDate.lt(date)
                                .or(mall.monitoringDate.eq(date).and(mall.Id.lt(id))))
                )
                .ifPresent(builder::and);
        //필터 조건
        //평점 조건 Optional처리
        Optional.ofNullable(overallRating)
                .map(mall.overallRating::eq)
                .ifPresent(builder::and);
        //사업상태 조건이 있을 경우 추가
        Optional.ofNullable(businessStatus)
                .map(mall.businessStatus::eq)
                .ifPresent(builder::and);
        //where절에 동적으로 생성된 조건을 추가
        return jpaQueryFactory
                .selectFrom(mall)
                .where(builder)
                .orderBy(mall.monitoringDate.desc(), mall.Id.desc())
                .limit(size)
                .fetch();
    }

}
