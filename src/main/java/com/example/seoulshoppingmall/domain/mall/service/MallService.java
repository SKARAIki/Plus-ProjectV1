package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.response.MallResponseDto;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MallService {

    private final MallRepository mallRepository;

    public List<MallResponseDto> GetMallList() {
        return mallRepository.findAllByOrderByMonitoringDateDesc()
                .stream()
                .map(MallResponseDto::new)
                .collect(Collectors.toList());
    }
}
