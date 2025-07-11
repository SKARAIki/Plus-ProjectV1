package com.example.seoulshoppingmall.domain.auth.repository;

import com.example.seoulshoppingmall.domain.auth.entity.Member;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.List;
>>>>>>> e786e64a43632d47e3854a449b9bb418eefc47c6

public interface MemberRepository extends JpaRepository<Member, Long> {
    //특정 이메일이 DB에 존재하는지 여부만 확인
    boolean existsByEmail(String userEmail);
    //특정 이메일을 가진 회원을 조회
    Optional<Member> findByEmail(String email);
}
