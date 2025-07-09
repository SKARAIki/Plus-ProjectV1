package com.example.seoulshoppingmall.common.config;

import com.example.seoulshoppingmall.domain.auth.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    // 비밀키의 경우 일반적으로 환경변수로 관리합니다.
    // 편의를 위해 속성으로 선언해 놓고 사용하는 예시입니다.
    private String secret = "long-long-long-long-long-long-long";

    /**
     * 토큰 만들기
     */
    public String createToken(Member member) {
        // 1. 서명 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. 데이터 준비
        String subject = member.getId().toString(); // 사용자 준비
        Date now = new Date();                // 현재시간
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60); // 만료시간 설정 1분뒤

        // 2. 토큰 만들기
        String jwt = Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .claim("email", "sparta@xxxx.com") // 💡 커스텀 하게 활용하는 방법
                .claim("memberName", "lee")
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
        return jwt;
    }

    /**
     * 토큰을 검증하고 memberId 를 반환합니다.(원래 검증, 반환 따로 해야함)
     */
    public Long verifyToken(String token) {
        // 1. 서명 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. 검증
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        // 2. 사용자 추출
        String subject = claims.getSubject();
        // String value1  = (String) claims.get("key1"); // 커스텀하게 설정한 요소 추출

        // 3. 타입 변환
        Long id = Long.parseLong(subject);

        // 4. 반환
        return id;
    }
}
