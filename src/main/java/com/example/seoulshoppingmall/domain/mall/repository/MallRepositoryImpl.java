package com.example.seoulshoppingmall.domain.mall.repository;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.entity.QMall;
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
    public List<Mall> searchMalls(Integer overallRating, String businessStatus) {
        QMall mall = QMall.mall;

        return jpaQueryFactory
                .selectFrom(mall)
                .where(
                        overallRating != null ? mall.overallRating.eq(overallRating) : null,
                        businessStatus != null ? mall.businessStatus.eq(businessStatus) : null
                )
                .orderBy(mall.monitoringDate.desc())
                .fetch();
    }

}
