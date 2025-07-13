package com.example.trendsixtown.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.seoulshoppingmall.domain.mall.entity.ShoppingMall;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShoppingMall is a Querydsl query type for ShoppingMall
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShoppingMall extends EntityPathBase<ShoppingMall> {

    private static final long serialVersionUID = -825986333L;

    public static final QShoppingMall shoppingMall = new QShoppingMall("shoppingMall");

    public final StringPath additionalPrivacyRequirements = createString("additionalPrivacyRequirements");

    public final StringPath businessInfoRating = createString("businessInfoRating");

    public final StringPath businessName = createString("businessName");

    public final StringPath businessStatus = createString("businessStatus");

    public final StringPath businessType = createString("businessType");

    public final StringPath cancellationPolicyAvailable = createString("cancellationPolicyAvailable");

    public final StringPath cancellationPolicyRating = createString("cancellationPolicyRating");

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

    public final StringPath overallRating = createString("overallRating");

    public final StringPath paymentMethodRating = createString("paymentMethodRating");

    public final StringPath paymentMethods = createString("paymentMethods");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath privacyPolicy = createString("privacyPolicy");

    public final StringPath privacySecurityRating = createString("privacySecurityRating");

    public final StringPath purchaseSafetyService = createString("purchaseSafetyService");

    public final StringPath returnShippingCostResponsibility = createString("returnShippingCostResponsibility");

    public final StringPath salesNumber = createString("salesNumber");

    public final StringPath secureServerInstallation = createString("secureServerInstallation");

    public final StringPath siteOpeningYear = createString("siteOpeningYear");

    public final StringPath termsCompliance = createString("termsCompliance");

    public final StringPath termsRating = createString("termsRating");

    public QShoppingMall(String variable) {
        super(ShoppingMall.class, forVariable(variable));
    }

    public QShoppingMall(Path<? extends ShoppingMall> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShoppingMall(PathMetadata metadata) {
        super(ShoppingMall.class, metadata);
    }

}

