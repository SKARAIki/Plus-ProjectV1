package com.example.seoulshoppingmall.csv.csvBean;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvMallInfo {
    @CsvBindByName(column = "상호")
    private String businessName;

    @CsvBindByName(column = "쇼핑몰명")
    private String mallName;

    @CsvBindByName(column = "도메인명")
    private String domainName;

    @CsvBindByName(column = "전화번호")
    private String phoneNumber;

    @CsvBindByName(column = "운영자이메일")
    private String operatorEmail;

    @CsvBindByName(column = "통신판매번호")
    private String salesNumber;

    @CsvBindByName(column = "영업형태")
    private String businessType;

    @CsvBindByName(column = "최초신고일자")
    private String initialReportDate;

    @CsvBindByName(column = "회사주소")
    private String companyAddress;

    @CsvBindByName(column = "업소상태")
    private String businessStatus;

    @CsvBindByName(column = "전체평가")
    private int overallRating;

    @CsvBindByName(column = "사업자정보표시평가")
    private int businessInfoRating;

    @CsvBindByName(column = "청약철회평가")
    private int cancellationPolicyRating;

    @CsvBindByName(column = "결재방법평가")
    private int paymentMethodRating;

    @CsvBindByName(column = "이용약관평가")
    private int termsRating;

    @CsvBindByName(column = "개인정보보안평가")
    private int privacySecurityRating;

    @CsvBindByName(column = "주요취급품목")
    private String mainProducts;

    @CsvBindByName(column = "청약철회가능여부")
    private String cancellationPolicyAvailable;

    @CsvBindByName(column = "초기화면필수항목중표시사항")
    private String mandatoryDisplayItems;

    @CsvBindByName(column = "결제방법")
    private String paymentMethods;

    @CsvBindByName(column = "이용약관준수정도")
    private String termsCompliance;

    @CsvBindByName(column = "개인정보취급방침")
    private String privacyPolicy;

    @CsvBindByName(column = "표준약관이상개인정보항목요구")
    private String additionalPrivacyRequirements;

    @CsvBindByName(column = "구매안전서비스")
    private String purchaseSafetyService;

    @CsvBindByName(column = "보안서버설치")
    private String secureServerInstallation;

    @CsvBindByName(column = "인증마크")
    private String certificationMark;

    @CsvBindByName(column = "배송예정일표시")
    private String deliveryDateDisplay;

    @CsvBindByName(column = "철회시배송비부담여부")
    private String returnShippingCostResponsibility;

    @CsvBindByName(column = "고객불만게시판운영")
    private String customerComplaintBoard;

    @CsvBindByName(column = "회원탈퇴방법")
    private String memberWithdrawalMethod;

    @CsvBindByName(column = "사이트개설년도")
    private String siteOpeningYear;

    @CsvBindByName(column = "모니터링날짜")
    private String monitoringDate;


    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    public CsvMallInfo() {} // openCsv는 기본생성자를 사용해서 데이터를 저장함

    public Mall toEntity() {
        Mall mall = new Mall(this.businessName, this.mallName, this.domainName, this. phoneNumber, this.operatorEmail,
                this.salesNumber, this.businessType, this.initialReportDate, this.companyAddress, this.businessStatus,
                this.overallRating, this.businessInfoRating, this.cancellationPolicyRating, this.paymentMethodRating,
                this.termsRating, this.privacySecurityRating, this.mainProducts, this.cancellationPolicyAvailable,
                this.mandatoryDisplayItems, this.paymentMethods, this.termsCompliance, this.privacyPolicy,
                this.additionalPrivacyRequirements, this.purchaseSafetyService, this.secureServerInstallation,
                this.certificationMark, this.deliveryDateDisplay,this.returnShippingCostResponsibility,
                this.customerComplaintBoard, this.memberWithdrawalMethod, this.siteOpeningYear, this.monitoringDate);
        return mall;
    }
}
