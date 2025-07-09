package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiResponse;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MallService {
    private final MallRepository mallRepository;

    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    public List<MallOpenApiDto> fetchAndParseOpenApiData() {
        // 1. RestTemplate으로 OpenAPI 요청
        RestTemplate restTemplate = new RestTemplate();
        // 1~100 까지 호출
        String openApiUrl = "http://openapi.seoul.go.kr:8088/인증키/xml/ServiceInternetShopInfo/1/100/";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(openApiUrl, String.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("OpenAPI 호출 실패: " + responseEntity.getStatusCode());
        }

        // XML 문자열
        String xml = responseEntity.getBody();

        // 2. XmlMapper로 파싱
        try {
            XmlMapper xmlMapper = new XmlMapper();
            MallOpenApiResponse mallResponse = xmlMapper.readValue(xml, MallOpenApiResponse.class);
            // 파싱된 MallOpenApiDto 리스트
            return mallResponse.getRows();
        } catch (Exception e) {
            throw new RuntimeException("XML 파싱 실패", e);
        }
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
