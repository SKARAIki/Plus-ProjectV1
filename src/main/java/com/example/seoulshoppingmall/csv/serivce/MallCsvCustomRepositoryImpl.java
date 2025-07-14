package com.example.seoulshoppingmall.csv.serivce;

import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepositoryCustom;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public class MallCsvCustomRepositoryImpl implements MallCsvRepositoryCustom {
    private final JdbcTemplate jdbcTemplate;

    public MallCsvCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void bulkSave(List<Mall> malls) {
        batchInsert(100, malls); // 100개씩 묶어서 저장
    }

    public void batchInsert(int batchSize, List<Mall> malls) {
        String sql =
                "INSERT INTO seoul_city_stores (" +
                        "business_name, mall_name, domain_name, phone_number, operator_email, " +
                        "sales_number, business_type, initial_report_date, company_address, business_status, " +
                        "overall_rating, business_info_rating, cancellation_policy_rating, payment_method_rating, " +
                        "terms_rating, privacy_security_rating, main_products, cancellation_policy_available, " +
                        "mandatory_display_items, payment_methods, terms_compliance, privacy_policy, " +
                        "additional_privacy_requirements, purchase_safety_service, secure_server_installation, " +
                        "certification_mark, delivery_date_display, return_shipping_cost_responsibility, " +
                        "customer_complaint_board, member_withdrawal_method, site_opening_year, monitoring_date" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // 비동기방식
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Mall mall = malls.get(i);
                ps.setString(1, mall.getBusinessName());
                ps.setString(2, mall.getMallName());
                ps.setString(3, mall.getDomainName());
                ps.setString(4, mall.getPhoneNumber());
                ps.setString(5, mall.getOperatorEmail());
                ps.setString(6, mall.getSalesNumber());
                ps.setString(7, mall.getBusinessType());
                ps.setString(8, mall.getInitialReportDate());
                ps.setString(9, mall.getCompanyAddress());
                ps.setString(10, mall.getBusinessStatus());
                ps.setInt(11, mall.getOverallRating());
                ps.setInt(12, mall.getBusinessInfoRating());
                ps.setInt(13, mall.getCancellationPolicyRating());
                ps.setInt(14, mall.getPaymentMethodRating());
                ps.setInt(15, mall.getTermsRating());
                ps.setInt(16, mall.getPrivacySecurityRating());
                ps.setString(17, mall.getMainProducts());
                ps.setString(18, mall.getCancellationPolicyAvailable());
                ps.setString(19, mall.getMandatoryDisplayItems());
                ps.setString(20, mall.getPaymentMethods());
                ps.setString(21, mall.getTermsCompliance());
                ps.setString(22, mall.getPrivacyPolicy());
                ps.setString(23, mall.getAdditionalPrivacyRequirements());
                ps.setString(24, mall.getPurchaseSafetyService());
                ps.setString(25, mall.getSecureServerInstallation());
                ps.setString(26, mall.getCertificationMark());
                ps.setString(27, mall.getDeliveryDateDisplay());
                ps.setString(28, mall.getReturnShippingCostResponsibility());
                ps.setString(29, mall.getCustomerComplaintBoard());
                ps.setString(30, mall.getMemberWithdrawalMethod());
                ps.setString(31, mall.getSiteOpeningYear());
                ps.setString(32, mall.getMonitoringDate());
            }

            @Override
            public int getBatchSize() {
                return malls.size();
            }
        });

    }
}
