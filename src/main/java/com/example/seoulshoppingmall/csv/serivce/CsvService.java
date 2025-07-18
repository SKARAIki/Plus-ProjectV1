package com.example.seoulshoppingmall.csv.serivce;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.csv.csvBean.CsvMallInfo;
import com.example.seoulshoppingmall.csv.dto.CsvFileResponse;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CsvService {

    private final MallRepository mallRepository;
    private final EntityManager entityManager;

    public CsvService(EntityManager entityManager, MallRepository mallRepository) {
        this.entityManager = entityManager;
        this.mallRepository = mallRepository;
    }


    @Transactional
    public ApiResponse<CsvFileResponse> csvFileUploadProcess(MultipartFile csvFile) {

        try {
            InputStreamReader inputStreamReader
                    = new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8);


            List<CsvMallInfo> csvMallInfoParse = new CsvToBeanBuilder<CsvMallInfo>(inputStreamReader)
                    .withType(CsvMallInfo.class)
                    .withIgnoreLeadingWhiteSpace(true) // " 문자열" 이 있을 경우 공백제거 여부 함수
                    .build()
                    .parse();

            List<Mall> mallInfoList = csvMallInfoParse.stream()
                    .map(csvMallInfo -> csvMallInfo.toEntity())
                    .collect(Collectors.toList());


            mallRepository.bulkSave(mallInfoList);
            entityManager.flush();
            entityManager.clear();
            int totalCount = csvMallInfoParse.size();
            int successCount = mallInfoList.size();
            int failCount = totalCount - successCount;
            CsvFileResponse csvFileResponse = new CsvFileResponse(totalCount, mallInfoList.size(), failCount);

            ApiResponse<CsvFileResponse> csvFileSaveSuccess
                    = ApiResponse.success(HttpStatus.CREATED, "CSV파일이 DB에 업로드 되었습니다", csvFileResponse);
            return csvFileSaveSuccess;

        } catch (IOException e) {
            throw new RuntimeException("CSV파일 처리 에러");
        }
    }
}


