package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.command.SearchCommand;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListV1Response;
import com.example.seoulshoppingmall.domain.mall.exception.NotFoundParamException;
import com.example.seoulshoppingmall.domain.mall.dto.request.MallCursor;
import com.example.seoulshoppingmall.domain.mall.dto.request.MallCursorRequestDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallCursorResponseDto;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallResponseDto;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import com.example.seoulshoppingmall.domain.mall.dto.response.MallGetListResponseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class MallService {
    // 속
    private final MallRepository mallRepository;

    // 생
    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    // 기
    public MallCursorResponseDto getMallList(MallCursorRequestDto requestDto) {
        // 쿼리 실행
        List<Mall> malls = mallRepository.searchMalls(
                requestDto.getMonitoringDateCursor(),
                requestDto.getIdCursor(),
                requestDto.getSize(),
                requestDto.getOverallRating(),
                requestDto.getBusinessStatus()
        );
        // Entity → DTO 변환
        List<MallResponseDto> dtoList = malls.stream()
                .map(MallResponseDto::new)
                .toList();
        //마지막 Mall 기준으로 커서 생성
        MallCursor nextCursor;
        if (!malls.isEmpty()) {
            Mall last = malls.get(malls.size() - 1);
            nextCursor = new MallCursor(last.getMonitoringDate(), last.getId());
        } else {
            nextCursor = MallCursor.EMPTY;
        }


        return new MallCursorResponseDto(dtoList, nextCursor.getMonitoringDate(), nextCursor.getId());
    }


    @Transactional
    public MallGetListResponseDto getMallListFilterService(Integer overallRating, String businessStatus) {
        List<Mall> malls = new ArrayList<>();
        // 리스트 조회
        //    - ‘전체평가’ 필터 조회
//        - ‘전체평가’는 0점 ~ 3점 으로 이루어져 있고 점수를 입력하여 해당 업체 리스트만 조회
//        - 예) ‘전체평가’가 3점인 경우만 리스트 조회
        //만약 overallRating 이 널이 아니고,0보다 같거나 크고, 3보다 같거나 작아야 한다.
        boolean overallRatingFilter = overallRating != null && 0 <= overallRating && overallRating <= 3;

        //    - ‘업소상태’ 필터 조회
//        - `사이트운영중단`, `휴업중`, `광고용(홍보용)`, `등록정보불일치`, `사이트폐쇄`,
//        `영업중`, `확인안됨` 상태 중 1개를 선택하여 해당 업체 리스트만 조회
        //
        //만약 businessStatus 이 널이 아니고 businessStatus 이 비어야 한다.(businessStatus 가 비어 있지 않다면 true)
        boolean businessStatusFilter = businessStatus != null && !businessStatus.isBlank();

        //검증 된 데이터 준비
        PageRequest pageRequest = PageRequest.of(
                1, 10, Sort.by("monitoringDate").descending()
        );
        //전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(각각 1개씩 적용)상위 10개 리스트 보여주기
        //전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(2개 필터 동시 적용)상위 10개 리스트 보여주기
        if (overallRatingFilter && businessStatusFilter) {

            malls = mallRepository.findTop10ByOverallRatingAndBusinessStatus(
                    overallRating, businessStatus, pageRequest
            );
        } else if (overallRatingFilter) {
            malls = mallRepository.findTop10ByOverallRating(
                    overallRating, pageRequest
            );
        } else if (businessStatusFilter) {
            malls = mallRepository.findTop10ByBusinessStatus(
                    businessStatus, pageRequest
            );
        }

        if (malls.isEmpty()) {
            throw new RuntimeException(
                    "overallRating or businessStatus or overallRating with businessStatus only /" +
                            "overallRating is 0~3 points only / businessStatus shouldn't be empty."
            );
        }

        //responseDto 만들기
        List<MallGetListResponseDto.Malls> mallListResponseDtoList = malls.stream()
                .map(mall -> new MallGetListResponseDto.Malls(
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
        @Transactional(readOnly = true)
        public List<MallGetListV1Response> getListMallsInfoProcess (Integer overallRating, String businessStatus){
            // 깡통 malls 준비
            List<Mall> malls;

            SearchCommand searchCommand = new SearchCommand(overallRating, businessStatus);
            boolean hasRating = searchCommand.hasRating();
            boolean hasBusinessStatus = searchCommand.hasBusinessStatus();

            if (hasRating && hasBusinessStatus) {
                malls = mallRepository.findTop10RatedMallsStatus(overallRating, businessStatus);
            } else if (hasRating) {
                malls = mallRepository.findTop10RatedMall(overallRating);
            } else if (hasBusinessStatus) {
                malls = mallRepository.findTop10MallBusinessStatus(businessStatus);
            } else {
                throw new NotFoundParamException();
            }

            List<MallGetListV1Response> mallGetListV1Responses = malls
                    .stream()
                    .map(mall -> new MallGetListV1Response(mall))
                    .toList();
            return mallGetListV1Responses;

        }
    }

