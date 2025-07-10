package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListResponseDto;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MallService {

    private final MallRepository mallRepository;

    @Transactional
    public MallGetListResponseDto getMallListService(
            int overallRating, String businessStatus
    ) {
        m

        // 리스트 조회
        List<Mall> all = mallRepository.findAll();
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
                200, "성공",  mallListResponseDtoList
        );
    }
}
