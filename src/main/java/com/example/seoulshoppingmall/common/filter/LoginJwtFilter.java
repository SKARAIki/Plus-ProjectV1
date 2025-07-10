package com.example.seoulshoppingmall.common.filter;

import com.example.seoulshoppingmall.common.config.JwtTokenProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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
    private static final Set<String> WHITELIST = Set.of(
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

        //회원가입, 로그인 요청은 통과
        if (uri.equals("/api/members") || uri.equals("/api/members/login")) {
            chain.doFilter(request, response);
            return;
        }

        // JWT 추출
        String authHeader = req.getHeader("Authorization");
        String token = jwtTokenProvider.extractToken(authHeader);
        if (token == null) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 필요합니다.");
            return;
        }

        // JWT 검증
        if (!jwtTokenProvider.validateToken(token)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT 토큰이 유효하지 않습니다.");
            return;
        }

        // 사용자 정보 저장 (memberName)
        request.setAttribute("id", jwtTokenProvider.extractId(token));
        request.setAttribute("memberName", jwtTokenProvider.extractMemberName(token));
        request.setAttribute("email", jwtTokenProvider.extractEmail(token));

        chain.doFilter(request, response);
    }
}
