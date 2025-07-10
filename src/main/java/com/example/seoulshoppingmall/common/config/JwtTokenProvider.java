package com.example.seoulshoppingmall.common.config;

import com.example.seoulshoppingmall.domain.auth.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    //속성
    private String secret;
    private final SecretKey key;

    // JWT 앞에 붙는 접두사
    public static final String BEARER_PREFIX = "Bearer ";

    //생성자
    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    /**
     * 토큰 만들기
     */
    public String createToken(Member member) {
        // 1. 서명 만들기
//        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

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
                .signWith(key)
                .compact();
        return BEARER_PREFIX + jwt;
    }

    /**
     * 헤더에서 "Bearer <토큰>" 형식에서 토큰만 추출
     */
    public String extractToken(String header) {
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length()); // "Bearer " 제거
        }
        return null;
    }

    /**
     * 토큰에서 사용자 아이디(id)를 추출
     */
    public long extractId(String token) {
//        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parser()
                .setSigningKey(key) //비밀키 설정
                .build() //파서 빌더 빌드
                .parseClaimsJws(token) //토큰 파싱 및 검증
                .getBody(); //파싱 결과에서 실제 내용Body

        return Long.parseLong(claims.getSubject());
    }

    /**
     * 토큰에서 사용자 이름(MemberName)을 추출
     */
    public String extractMemberName(String token) {
//        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // setSubject로 저장했던 값 꺼냄
    }

    /**
     * 토큰에서 이메일 추출
     */
    public String extractEmail(String token) {
//        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getBody();

        return claims.get("email", String.class);
    }

    /**
     * 토큰이 유효한지 확인
     * - 서명이 맞는지
     * - 만료되지 않았는지
     * - 형식이 맞는지 등 검증
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token); // 파싱 실패하면 예외 발생
            return true;
        } catch (SecurityException e) {
            log.error("서명이 올바르지 않습니다.", e);
        } catch (MalformedJwtException e) {
            log.error("토큰 형식이 잘못되었습니다.", e);
        } catch (ExpiredJwtException e) {
            log.error("토큰이 만료되었습니다.", e);
        } catch (UnsupportedJwtException e) {
            log.error("지원하지 않는 토큰입니다.", e);
        } catch (IllegalArgumentException e) {
            log.error("토큰이 비어 있거나 잘못되었습니다.", e);
        }
        return false;
    }
}
