package com.example.seoulshoppingmall.domain.mall.dto.response;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

//디티오는 게터 써도 괜찮아서 편의를 위해
@Getter
//생성자 자동 생성
@AllArgsConstructor
public class MallGetListResponseDto {
    private int status;

    private String message;

    private List<Malls> mallListResponseDtoList;

    //생성자 자동 생성
    @AllArgsConstructor
    //디티오는 게터 써도 괜찮아서 편의를 위해
    @Getter
    public static class Malls {
        private Long id;

        private String businessName;

        private String mallName;

        private String domainName;

        private String phoneNumber;

        private String operatorEmail;

        private String salesNumber;

        private String businessType;

        private String initialReportDate;

        private String companyAddress;

        private String businessStatus;

        private int overallRating;

        private int businessInfoRating;

        private int cancellationPolicyRating;

        private int paymentMethodRating;

        private int termsRating;

        private int privacySecurityRating;

        private String mainProducts;

        private String cancellationPolicyAvailable;

        private String mandatoryDisplayItems;

        private String paymentMethods;

        private String termsCompliance;

        private String privacyPolicy;

        private String additionalPrivacyRequirements;

        private String purchaseSafetyService;

        private String secureServerInstallation;

        private String certificationMark;

        private String deliveryDateDisplay;

        private String returnShippingCostResponsibility;

        private String customerComplaintBoard;

        private String memberWithdrawalMethod;

        private String siteOpeningYear;

        private String monitoringDate;
    }
}