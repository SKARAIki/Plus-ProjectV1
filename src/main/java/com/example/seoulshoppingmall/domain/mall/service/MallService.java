package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiDto;
import com.example.seoulshoppingmall.domain.mall.dto.openapi.MallOpenApiWrapper;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
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

    // OpenAPI 호출, DTO 파싱
    public List<MallOpenApiDto> fetchAndParseOpenApiData(int start, int end) {
        String url = "http://openapi.seoul.go.kr:8088/4c6a55624e6c796a37374256576363/json/ServiceInternetShopInfo/" + start + "/" + end + "/";; // 인증키 = 4c6a55624e6c796a37374256576363
        // http://openAPI.seoul.go.kr:8088/(인증키)/xml/ServiceInternetShopInfo/1/5/
        // RestTemplate - 외부 API를 요청할 때 사용하는 스프링툴(방선희 튜터님 피드백 참고)
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MallOpenApiWrapper> response = restTemplate.getForEntity(url, MallOpenApiWrapper.class);

        // row만 꺼내서 반환
        //디버깅 이용하기 (홍태호 튜터님)
        return response.getBody()
                .getServiceInternetShopInfo()
                .getRow();
        }
        public int saveAllMalls(List<MallOpenApiDto> mallDtos) {
        List<Mall> malls = mallDtos.stream()
                .map(Mall::fromDto) // DTO → Entity
                .toList();

        mallRepository.saveAll(malls); // DB 저장
        return malls.size(); // 저장된 개수 반환
        }
    }



    /*
        try {
            //json문자열을 java객체로 바꿔줌
            ObjectMapper objectMapper = new ObjectMapper();

            // JSON 구조에서 row만 추출, 왜냐면 row 기준으로 한 단락?이 결정되기 때문, 넘버링이 되어있지 않음
            String rowsJson = objectMapper.readTree(response.getBody())
                    .path("ServiceInternetShopInfo")
                    .path("row")
                    .toString();
            // 추출한 row를 dto리스트로 변환
            return objectMapper.readValue(rowsJson, new TypeReference<List<MallOpenApiDto>>() {

            });

        } catch (Exception e) {
            // 예외처리
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }

     // 위에서 파싱된 DTO 리스트를 Entity 변환 후 db에 저장
    public int saveAllMalls(List<MallOpenApiDto> mallDtos) {
        // dto를 entity로 반환(entity에 정적메서드)
        List<Mall> malls = mallDtos.stream()
                // 엔티티 안에 static 메서드로 변환
                .map(Mall::fromDto)
                .toList();

        mallRepository.saveAll(malls);
        // 개수반환
        return malls.size();
    }

     */
