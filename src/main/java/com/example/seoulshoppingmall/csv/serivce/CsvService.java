package com.example.seoulshoppingmall.csv.serivce;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.csv.csvBean.MallInfoCsv;
import com.example.seoulshoppingmall.domain.mall.entity.Mall;
import com.example.seoulshoppingmall.domain.mall.repository.MallRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
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
    public ApiResponse<String> csvFileUploadProcess(MultipartFile csvFile) {

        try {
            InputStreamReader inputStreamReader
                    = new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8);


            List<MallInfoCsv> mallInfoCsvParse = new CsvToBeanBuilder<MallInfoCsv>(inputStreamReader)
                    .withType(MallInfoCsv.class)
                    .withIgnoreLeadingWhiteSpace(true) // " 문자열" 이 있을 경우 공백제거 여부 함수
                    .build()
                    .parse();

            List<Mall> mallInfoList = mallInfoCsvParse.stream()
                    .map(mallInfoCsv -> mallInfoCsv.toEntity())
                    .collect(Collectors.toList());


            mallRepository.bulkSave(mallInfoList);
            entityManager.flush();
            entityManager.clear();

            ApiResponse<String> success
                    = ApiResponse.success(HttpStatus.CREATED, "CSV파일이 DB에 업로드 되었습니다", "");
            return success;

        } catch (IOException e) {
            throw new RuntimeException("CSV파일 처리 에러");
        }
    }
}


