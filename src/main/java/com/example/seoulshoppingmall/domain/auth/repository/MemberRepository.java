package com.example.seoulshoppingmall.domain.auth.repository;

import com.example.seoulshoppingmall.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //특정 이메일이 DB에 존재하는지 여부만 확인
    boolean existsByEmail(String userEmail);
    //특정 이메일을 가진 회원을 조회
    Optional<Member> findByEmail(String email);
}
