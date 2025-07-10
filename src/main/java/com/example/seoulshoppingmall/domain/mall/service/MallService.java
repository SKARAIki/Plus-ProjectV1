package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.command.SearchCommand;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListV1Response;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.exception.NotFoundParamException;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MallService {
    // 속
    private final MallRepository mallRepository;

    // 생
    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    // 기
    @Transactional(readOnly = true)
    public List<MallGetListV1Response> getListMallsInfoProcess(Integer overallRating, Collection<String> businessStatus) {
        // 깡통 malls 준비
        List<Mall> malls = new ArrayList<>();

        // null - 직접적으로 사용하고 싶지 않아서 Optional로 처리
        // Param에 값이 있다면 ture 반환 없다면 false 반환
//        boolean hasRating = Optional.ofNullable(overallRating)
//                .map(rating -> rating >= 0 && rating <= 3)
//                .orElse(false);
//        boolean hasBusinessStatus = Optional.ofNullable(businessStatus)
//                .map(businessStatusList -> !businessStatus.isEmpty())
//                .orElse(false);
        SearchCommand searchCommand = new SearchCommand(overallRating, businessStatus);
        boolean hasRating = searchCommand.hasRating();
        boolean hasBusinessStatus = searchCommand.hasBusinessStatus();

        if (hasRating && hasBusinessStatus) {
            malls = mallRepository.findTop10RatedMallsStatus(overallRating, businessStatus);
        } else if (hasRating) {
            malls = mallRepository.findTop10RatedMall(overallRating);
        } else if (hasBusinessStatus) {
            malls = mallRepository.findTop10MallBusinessStatus(businessStatus);
        } else {
            throw new NotFoundParamException();
        }

        List<MallGetListV1Response> mallGetListV1Responses = malls
                .stream()
                .map(mall -> new MallGetListV1Response(mall))
                .toList();
        return mallGetListV1Responses;

    }
}
