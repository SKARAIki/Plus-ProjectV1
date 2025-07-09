package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.request.MallCursorRequestDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCursorResponseDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallResponseDto;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MallService {

    private final MallRepository mallRepository;

    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    public List<MallResponseDto> GetMallList(MallCursorRequestDto requestDto) {
        // 쿼리 실행
        List<Mall> malls = mallRepository.searchMalls(
                requestDto.getMonitoringDateCursor(),
                requestDto.getIdCursor(),
                requestDto.getSize(),
                requestDto.getOverallRating(),
                requestDto.getBusinessStatus()
        );
        // Entity → DTO 변환
        List<MallResponseDto> dtoList = malls.stream()
                .map(MallResponseDto:: new)
                .toList();
        //마지막 Mall의 정보로 nextCursor 계산
        String nextDate = null;
        Long nextId = null;

        if (!malls.isEmpty()) {
            Mall last = malls.get(malls.size() - 1);
            nextDate = last.getMonitoringDate();
            nextId = last.getId();
        }
        return new MallCursorResponseDto(dtoList, nextDate, nextId);
    }
}
