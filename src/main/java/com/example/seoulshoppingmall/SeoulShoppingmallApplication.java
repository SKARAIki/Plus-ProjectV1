package com.example.seoulshoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO) //  Page<T> 반환을 위한 스프링이 제공해주는 pageable, 페이지 dto 만들거면 삭제
public class SeoulShoppingmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeoulShoppingmallApplication.class, args);
    }

}
