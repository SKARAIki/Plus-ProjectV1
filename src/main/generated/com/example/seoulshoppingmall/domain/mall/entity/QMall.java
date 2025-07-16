package com.example.seoulshoppingmall.domain.mall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMall is a Querydsl query type for Mall
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMall extends EntityPathBase<Mall> {

    private static final long serialVersionUID = 1421500160L;

    public static final QMall mall = new QMall("mall");

    public final StringPath additionalPrivacyRequirements = createString("additionalPrivacyRequirements");

    public final NumberPath<Integer> businessInfoRating = createNumber("businessInfoRating", Integer.class);

    public final StringPath businessName = createString("businessName");

    public final StringPath businessStatus = createString("businessStatus");

    public final StringPath businessType = createString("businessType");

    public final StringPath cancellationPolicyAvailable = createString("cancellationPolicyAvailable");

    public final NumberPath<Integer> cancellationPolicyRating = createNumber("cancellationPolicyRating", Integer.class);

    public final StringPath certificationMark = createString("certificationMark");

    public final StringPath companyAddress = createString("companyAddress");

    public final StringPath customerComplaintBoard = createString("customerComplaintBoard");

    public final StringPath deliveryDateDisplay = createString("deliveryDateDisplay");

    public final StringPath domainName = createString("domainName");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath initialReportDate = createString("initialReportDate");

    public final StringPath mainProducts = createString("mainProducts");

    public final StringPath mallName = createString("mallName");

    public final StringPath mandatoryDisplayItems = createString("mandatoryDisplayItems");

    public final StringPath memberWithdrawalMethod = createString("memberWithdrawalMethod");

    public final StringPath monitoringDate = createString("monitoringDate");

    public final StringPath operatorEmail = createString("operatorEmail");

    public final NumberPath<Integer> overallRating = createNumber("overallRating", Integer.class);

    public final NumberPath<Integer> paymentMethodRating = createNumber("paymentMethodRating", Integer.class);

    public final StringPath paymentMethods = createString("paymentMethods");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath privacyPolicy = createString("privacyPolicy");

    public final NumberPath<Integer> privacySecurityRating = createNumber("privacySecurityRating", Integer.class);

    public final StringPath purchaseSafetyService = createString("purchaseSafetyService");

    public final StringPath returnShippingCostResponsibility = createString("returnShippingCostResponsibility");

    public final StringPath salesNumber = createString("salesNumber");

    public final StringPath secureServerInstallation = createString("secureServerInstallation");

    public final StringPath siteOpeningYear = createString("siteOpeningYear");

    public final StringPath termsCompliance = createString("termsCompliance");

    public final NumberPath<Integer> termsRating = createNumber("termsRating", Integer.class);

    public QMall(String variable) {
        super(Mall.class, forVariable(variable));
    }

    public QMall(Path<? extends Mall> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMall(PathMetadata metadata) {
        super(Mall.class, metadata);
    }

}

