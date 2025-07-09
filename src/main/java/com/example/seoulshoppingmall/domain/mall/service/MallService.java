package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.response.MallResponseDto;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MallService {

    private final MallRepository mallRepository;

    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    public List<MallResponseDto> GetMallList(Integer overallRating, String businessStatus) {
        List<Mall> malls = mallRepository.searchMalls(overallRating, businessStatus);

        return malls.stream()
                .map(MallResponseDto::new)
                .collect(Collectors.toList());
    }
}
