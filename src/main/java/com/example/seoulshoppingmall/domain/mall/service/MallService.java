package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.request.MallCursor;
import com.example.seoulshoppingmall.domain.mall.dto.request.MallCursorRequestDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCursorResponseDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallResponseDto;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class MallService {

    private final MallRepository mallRepository;

    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    public MallCursorResponseDto getMallList(MallCursorRequestDto requestDto) {
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
        //마지막 Mall 기준으로 커서 생성
        MallCursor nextCursor;
        if (!malls.isEmpty()) {
            Mall last = malls.get(malls.size() - 1);
            nextCursor = new MallCursor(last.getMonitoringDate(), last.getId());
        } else {
            nextCursor = MallCursor.EMPTY;
        }


        return new MallCursorResponseDto(dtoList, nextCursor.getMonitoringDate(), nextCursor.getId());
    }
}
