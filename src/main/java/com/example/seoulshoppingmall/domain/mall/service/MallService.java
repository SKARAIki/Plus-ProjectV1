package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListResponseDto;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MallService {

    private final MallRepository mallRepository;

    @Transactional
    public MallGetListResponseDto getMallListFilterService(
            Integer overallRating, String businessStatus
    ) {
        // 리스트 조회
        //    - ‘전체평가’ 필터 조회
//        - ‘전체평가’는 0점 ~ 3점 으로 이루어져 있고 점수를 입력하여 해당 업체 리스트만 조회
//        - 예) ‘전체평가’가 3점인 경우만 리스트 조회
        //만약 overallRating 이 널이고,0보다 작고, 3보다 크면 안된다.

        if (overallRating == null || overallRating < 0 || overallRating > 3) {
            throw new RuntimeException("not found overallRating or 0 to 3 points only");
        }
        //    - ‘업소상태’ 필터 조회
//        - `사이트운영중단`, `휴업중`, `광고용(홍보용)`, `등록정보불일치`, `사이트폐쇄`,
//        `영업중`, `확인안됨` 상태 중 1개를 선택하여 해당 업체 리스트만 조회
        //
        //만약 businessStatus 이 널이고 businessStatus 이 비었으면 안된다.
        if (businessStatus == null || businessStatus.isBlank()) {
            throw new RuntimeException("not found status");
        }

        //검증 된 데이터 준비
        PageRequest monitoringDate = PageRequest.of(
                1, 10, Sort.by("MonitoringDate").descending()
        );
        //전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(각각 1개씩 적용)상위 10개 리스트 보여주기
        List<Mall> top10OverallRating = mallRepository.findTop10OverallRating(overallRating);

        List<Mall> top10businessStatus = mallRepository.findTop10businessStatus(businessStatus);

        //전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(2개 필터 동시 적용)상위 10개 리스트 보여주기
        List<Mall> all = mallRepository.findTop10ByOverallRatingAndBusinessStatus(
                overallRating, businessStatus, monitoringDate
        );

        //responseDto 만들기
        List<MallGetListResponseDto.GetMallListResponseDto> mallListResponseDtoList = all.stream()
                .map(mall -> new MallGetListResponseDto.GetMallListResponseDto(
                        mall.getId(),
                        mall.getBusinessName(),
                        mall.getMallName(),
                        mall.getDomainName(),
                        mall.getPhoneNumber(),
                        mall.getOperatorEmail(),
                        mall.getSalesNumber(),
                        mall.getBusinessType(),
                        mall.getInitialReportDate(),
                        mall.getCompanyAddress(),
                        mall.getBusinessStatus(),
                        mall.getOverallRating(),
                        mall.getBusinessInfoRating(),
                        mall.getCancellationPolicyRating(),
                        mall.getPaymentMethodRating(),
                        mall.getTermsRating(),
                        mall.getPrivacySecurityRating(),
                        mall.getMainProducts(),
                        mall.getCancellationPolicyAvailable(),
                        mall.getMandatoryDisplayItems(),
                        mall.getPaymentMethods(),
                        mall.getTermsCompliance(),
                        mall.getPrivacyPolicy(),
                        mall.getAdditionalPrivacyRequirements(),
                        mall.getPurchaseSafetyService(),
                        mall.getSecureServerInstallation(),
                        mall.getCertificationMark(),
                        mall.getDeliveryDateDisplay(),
                        mall.getReturnShippingCostResponsibility(),
                        mall.getCustomerComplaintBoard(),
                        mall.getMemberWithdrawalMethod(),
                        mall.getSiteOpeningYear(),
                        mall.getMonitoringDate()
                )).toList();

        return new MallGetListResponseDto(
                200, "성공", mallListResponseDtoList
        );
    }
}
