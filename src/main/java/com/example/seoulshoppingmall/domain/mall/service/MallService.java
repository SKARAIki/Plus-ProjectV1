package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallService {
    private final MallRepository mallRepository;

    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    // db에 저장하는 메서드 만들기, count로 세야하나,,?

    // openapi로부터 파싱된 dto리스트 -> mall entity로 변환하고 저장함
    public int saveAllMalls(List<MallOpenApiDto> mallDtoList) {
        int count = 0;

        for (MallOpenApiDto dto : mallDtoList) {
            // DTO → Entity 변환
            Mall mall = convertToMall(dto);
            mallRepository.save(mall);
            count++;
        }

        return count;
    }

    // dto리스트 -> mall entity 변환하는 메서드
    private Mall convertToMall(MallOpenApiDto dto) {
        return new Mall(
                null,
                dto.getBusinessName(),
                dto.getMallName(),
                dto.getDomainName(),
                dto.getPhoneNumber(),
                dto.getOperatorEmail(),
                dto.getSalesNumber(),
                dto.getBusinessType(),
                dto.getInitialReportDate(),
                dto.getCompanyAddress(),
                dto.getBusinessStatus(),
                dto.getOverallRating(),
                dto.getBusinessInfoRating(),
                dto.getCancellationPolicyRating(),
                dto.getPaymentMethodRating(),
                dto.getTermsRating(),
                dto.getPrivacySecurityRating(),
                dto.getMainProducts(),
                dto.getCancellationPolicyAvailable(),
                dto.getMandatoryDisplayItems(),
                dto.getPaymentMethods(),
                dto.getTermsCompliance(),
                dto.getPrivacyPolicy(),
                dto.getAdditionalPrivacyRequirements(),
                dto.getPurchaseSafetyService(),
                dto.getSecureServerInstallation(),
                dto.getCertificationMark(),
                dto.getDeliveryDateDisplay(),
                dto.getReturnShippingCostResponsibility(),
                dto.getCustomerComplaintBoard(),
                dto.getMemberWithdrawalMethod(),
                dto.getSiteOpeningYear(),
                dto.getMonitoringDate()
        );
    }
}
