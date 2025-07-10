package com.example.seoulshoppingmall.domain.auth.repository;

import com.example.seoulshoppingmall.domain.auth.entity.Member;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
