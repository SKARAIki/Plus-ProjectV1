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
    private final MallCsvCustomRepositoryImpl mallRepository;
    private final EntityManager entityManager;

    public CsvService(EntityManager entityManager, MallCsvCustomRepositoryImpl mallRepository) {
        this.entityManager = entityManager;
        this.mallRepository = mallRepository;
    }

    @Transactional
    public ApiResponse<Object> csvFileUploadProcess(MultipartFile csvFile) {

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

            ApiResponse<Object> success
                    = ApiResponse.success(HttpStatus.CREATED, "CSV파일이 DB에 업로드 되었습니다", "");
            return success;

        } catch (IOException e) {
            throw new RuntimeException("CSV파일 처리 에러");
        }
    }
}
/**
 * csv 파일 읽기
 *  근데 왜 csv파일 읽는데 InputStreamReader 써야함?
 * 1. 바이트 단위 데이터를 문자(character) 단위 데이터로 처리할 수 있도록 변환해준다.
 * 2. char 배열로 데이터를 받을 수 있다.
 * -> 그래서 왜? 웹에서 업로드 된 파일(MultipartFile)을 서버에 바이트 스트림(inputStream) 형태로 전달.
 * 이걸 문자로 읽을 수 있게 도와주는 것이 InputStreamReader.
 *
 *  그럼 공식 문서의 Quick Start에는
 * new CsvToBeanBuilder(FileReader("yourfile.csv")) 라는 형태의 FileReader가 있던데 왜 쓰지 않지?
 * -> FileReader는 로컬 환경내에 있는 csv파일을 읽는 형태이며 현재 웹을 개발하여 서비스 하는 방향으로 잡기 때문에
 * FileReader는 사용에 있어서 방향성이 다름. 만약 FileReader 사용시 매개변수에 csv 파일이 위치한 절대경로를 입력 해야함.
 *
 *  StandardCharsets.UTF_8 란?
 * - 텍스트 파일을 읽기 위한 인코딩 방식이고, UTF_8은 국제 표준 인코딩 방식, 특수문자나 한글, 일본어 중국어 등
 *  영어 이외에 문자들을 깨짐 없이 옳바르게 인코딩 하기 위한 방식임.
 *  그럼 csv에 영어만 존재하면 없어도 되나? -> 그렇지 않음.
 *  왜? 향후 어떤 csv 파일을 읽을지 모르는데, UTF_8 형식을 지정하지 않으면 JVM의 기본 인코딩 방식으로 처리,
 *  그 파일 내용에 영어 이외의 문자들이 존재하면 문자가 깨질 수도 있는 현상이 발생함 ( 확장성 고려 )
 */

//            int chunkSize = 2000;
//            for (int i = 0; i < mallInfoList.size(); i += chunkSize ) {
//                int end = Math.min(i + chunkSize, mallInfoList.size());
//                List<Mall> mallSaveList = mallInfoList.subList(i, end);
//                log.info("mallSaveList :::: {} ", mallSaveList);
//
//                mallRepository.saveAll(mallSaveList);
//                log.info("mallSaveList 저장 ::: {} ", mallRepository.saveAll(mallSaveList) );
//
//                entityManager.flush();
//                entityManager.clear();
//            }
