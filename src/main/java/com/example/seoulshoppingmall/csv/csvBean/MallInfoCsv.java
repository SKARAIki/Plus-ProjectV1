package com.example.seoulshoppingmall.csv.csvBean;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MallInfoCsv {
    @CsvBindByName(column = "business_name")
    private String businessName;

    @CsvBindByName(column = "mall_name")
    private String mallName;

    @CsvBindByName(column = "domain_name")
    private String domainName;

    @CsvBindByName(column = "phone_number")
    private String phoneNumber;

    @CsvBindByName(column = "operator_email")
    private String operatorEmail;

    @CsvBindByName(column = "sales_number")
    private String salesNumber;

    @CsvBindByName(column = "business_type")
    private String businessType;

    @CsvBindByName(column = "initial_report_date")
    private String initialReportDate;

    @CsvBindByName(column = "company_address")
    private String companyAddress;

    @CsvBindByName(column = "business_status")
    private String businessStatus;

    @CsvBindByName(column = "overall_rating")
    private int overallRating;

    @CsvBindByName(column = "business_info_rating")
    private int businessInfoRating;

    @CsvBindByName(column = "cancellation_policy_rating")
    private int cancellationPolicyRating;

    @CsvBindByName(column = "payment_method_rating")
    private int paymentMethodRating;

    @CsvBindByName(column = "terms_rating")
    private int termsRating;

    @CsvBindByName(column = "privacy_security_rating")
    private int privacySecurityRating;

    @CsvBindByName(column = "main_products")
    private String mainProducts;

    @CsvBindByName(column = "cancellation_policy_available")
    private String cancellationPolicyAvailable;

    @CsvBindByName(column = "mandatory_display_items")
    private String mandatoryDisplayItems;

    @CsvBindByName(column = "payment_methods")
    private String paymentMethods;

    @CsvBindByName(column = "terms_compliance")
    private String termsCompliance;

    @CsvBindByName(column = "privacy_policy")
    private String privacyPolicy;

    @CsvBindByName(column = "additional_privacy_requirements")
    private String additionalPrivacyRequirements;

    @CsvBindByName(column = "purchase_safety_service")
    private String purchaseSafetyService;

    @CsvBindByName(column = "secure_server_installation")
    private String secureServerInstallation;

    @CsvBindByName(column = "certification_mark")
    private String certificationMark;

    @CsvBindByName(column = "delivery_date_display")
    private String deliveryDateDisplay;

    @CsvBindByName(column = "return_shipping_cost_responsibility")
    private String returnShippingCostResponsibility;

    @CsvBindByName(column = "customer_complaint_board")
    private String customerComplaintBoard;

    @CsvBindByName(column = "member_withdrawal_method")
    private String memberWithdrawalMethod;

    @CsvBindByName(column = "site_opening_year")
    private String siteOpeningYear;

    @CsvBindByName(column = "monitoring_date")
    private String monitoringDate;


    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    public MallInfoCsv() {} // openCsv는 기본생성자를 사용해서 데이터를 저장함

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
