package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.entity.QMall;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

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

        //커서 조건
        if (cursorDate != null && cursorId != null) {
            builder.and(
                    mall.monitoringDate.lt(cursorDate)
                            .or(mall.monitoringDate.eq(cursorDate).and(mall.Id.lt(cursorId)))
            );
        }
        //필터 조건
        //평점 조건이 있을 경우 추가
        if (overallRating != null) {
            builder.and(mall.overallRating.eq(overallRating));
        }
        //사업상태 조건이 있을 경우 추가
        if (businessStatus != null) {
            builder.and(mall.businessStatus.eq(businessStatus));
        }
        //where절에 동적으로 생성된 조건을 추가
        return jpaQueryFactory
                .selectFrom(mall)
                .where(builder)
                .orderBy(mall.monitoringDate.desc(), mall.Id.desc())
                .limit(size)
                .fetch();
    }

}
