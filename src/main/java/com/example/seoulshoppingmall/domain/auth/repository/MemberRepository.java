package com.example.seoulshoppingmall.domain.auth.repository;

import com.example.seoulshoppingmall.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
