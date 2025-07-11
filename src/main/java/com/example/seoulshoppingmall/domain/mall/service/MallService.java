package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiWrapper;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import org.springframework.beans.factory.annotation.Value;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class MallService {
    private final MallRepository mallRepository;

    @Value("${seoul.api.base-url}")
    private String baseUrl;

    @Value("${seoul.api.auth-key}")
    private String authKey;

    @Value("${seoul.api.format}")
    private String format;

    @Value("${seoul.api.service}")
    private String service;

    public MallService(MallRepository mallRepository) {
        this.mallRepository = mallRepository;
    }

    // OpenAPI 호출, DTO 파싱
    public List<MallOpenApiDto> fetchAndParseOpenApiData(int start, int end) {
        String url = String.format("%s/%s/%s/%s/%d/%d/", baseUrl, authKey, format, service, start, end);

        // RestTemplate - 외부 API를 요청할 때 사용하는 스프링툴
        RestTemplate restTemplate = new RestTemplate();
        // 결과를 객체로 파싱함
        ResponseEntity<MallOpenApiWrapper> response = restTemplate.getForEntity(url, MallOpenApiWrapper.class);

        // row만 꺼내서 반환
        return response.getBody()
                .getServiceInternetShopInfo()
                .getRow();
    }

    // 키워드 기반 필터링
    public List<MallOpenApiDto> filterByKeyword(List<MallOpenApiDto> mallOpenapiDto, String keyword) {

        // keyword가 없거나 빈 문자열일 경우("") 필터링 안하고 전체 반환
        if (keyword == null || keyword.isBlank()) return mallOpenapiDto;

        // 필터링된거 반환
        return mallOpenapiDto.stream()
                // 조건
                .filter(dto -> {
                    // mainProducts가 null이면 필터 대상에서 제외하고
                    if (dto.getMainProducts() == null) return false;

                    // 화장품/향수 같은 슬래쉬로 구분된 경우에 개별로 나눔
                    String[] parts = dto.getMainProducts().split("/");

                    // 각 품목을 trim 으로 공백제거
                    return Arrays.stream(parts)
                            .map(String::trim)
                            .anyMatch(p -> p.contains(keyword.trim()));
                })
                .toList();
    }

    @Transactional
    public int saveAllMalls(List<MallOpenApiDto> mallOpenapiDto) {
        // dto리스트를 엔티티리스트로 변환
        List<Mall> malls = mallOpenapiDto.stream()
                .map(Mall::fromDto) // DTO → Entity
                .toList();

        // DB 저장
        mallRepository.saveAll(malls);
        // 저장된 개수 반환(몇개 저장했는지)
        return malls.size();
    }
}