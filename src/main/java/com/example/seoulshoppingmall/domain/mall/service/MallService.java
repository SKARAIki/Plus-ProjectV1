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
@RequiredArgsConstructor
public class MallService {

    private final MallRepository mallRepository;

    public List<MallResponseDto> GetMallList(Integer overallRating, String businessStatus) {
        List<Mall> malls;

        if (overallRating != null && businessStatus != null) {
            malls = mallRepository.findByOverallRatingAndBusinessStatusOrderByMonitoringDateDesc(overallRating, businessStatus);
        } else if (overallRating != null) {
            malls = mallRepository.findByOverallRatingOrderByMonitoringDateDesc(overallRating);
        } else if (businessStatus != null) {
            malls = mallRepository.findByBusinessStatusOrderByMonitoringDateDesc(businessStatus);
        } else {
            malls = mallRepository.findAllByOrderByMonitoringDateDesc();
        }
        return malls.stream()
                .map(MallResponseDto::new)
                .collect(Collectors.toList());
    }
}
