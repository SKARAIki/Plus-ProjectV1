package com.example.seoulshoppingmall.common.config;

import com.example.seoulshoppingmall.common.filter.LoginJwtFilter;
import lombok.Getter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class FilterConfig {
    //속상
    private final JwtTokenProvider jwtTokenProvider;

    //생성자
    public FilterConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public FilterRegistrationBean<LoginJwtFilter> jwtFilter(JwtTokenProvider jwtTokenProvider) {
        FilterRegistrationBean<LoginJwtFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoginJwtFilter(jwtTokenProvider));
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
