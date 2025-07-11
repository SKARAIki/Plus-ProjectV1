package com.example.seoulshoppingmall.common.filter;

import com.example.seoulshoppingmall.common.config.JwtTokenProvider;
import com.example.seoulshoppingmall.domain.auth.repository.MemberRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class LoginJwtFilter implements Filter {
    //속성
    private final JwtTokenProvider jwtTokenProvider;

    //생성자
    public LoginJwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * 토큰 검증 불필요한 경로들
     */
    private static final Set<String> WHITE_LIST = Set.of(
            "/api/members",
            "/api/members/login"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        String auth = req.getHeader("Authorization");

        //회원가입, 로그인 요청은 통과 (Set에 저장된 값들을 내부적으로 순회하면서 입력된 uri와 같은 게 있는지 비교해서 확인)
        if (WHITE_LIST.contains(uri)) {
            chain.doFilter(request, response);
            return;
        }

        // JWT 추출
        Optional<String> optionalToken = jwtTokenProvider.extractToken(auth);
        //토큰이 null이면 다음줄 에러 처리
        if (optionalToken.isEmpty()) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증 정보가 누락되었습니다..");
            return;
        }
        //null이 아니면 토큰 꺼내기
        String token = optionalToken.get();

        // JWT 검증
        if (!jwtTokenProvider.validateToken(token)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT 토큰이 유효하지 않습니다.");
            return;
        }
        chain.doFilter(request, response);
    }
}
