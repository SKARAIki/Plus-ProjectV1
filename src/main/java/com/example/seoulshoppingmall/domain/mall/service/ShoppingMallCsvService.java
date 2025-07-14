package com.example.seoulshoppingmall.domain.mall.service;

import com.example.seoulshoppingmall.domain.auth.Exception.FileException;
import com.example.seoulshoppingmall.domain.mall.entity.ShoppingMall;
import com.example.seoulshoppingmall.domain.mall.repository.ShoppingMallCsvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingMallCsvService {

    private final ShoppingMallCsvRepository shoppingMallCsvRepository;
    //데이터를 담을 수 있는 최대 사이즈 선언(CSV 줄 수)\
    //하나의  쓰레드가 처리할 CSV 데이터 줄 수 설정
    private static final int CHUNK_SIZE  = 2000;
    private static final int THREAD_COUNT = 4;


    @Transactional
    public String uploadCsv(MultipartFile file) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<?>> futures = new ArrayList<>();

        //읽은 CSV 리스트에 저장
        List<String> allLines;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            allLines =
                    reader.lines().
                            skip(1).
                            collect(Collectors.toList());

        }
        List<List<String>> shoppingMallChunks = splitIntoChunks(allLines,CHUNK_SIZE);

        //저장된 리스트데이터 엔티티 리스트로 저장 > 데이터베이스에 저장
        for(List<String> shoppingMallChunk : shoppingMallChunks){
            futures.add(executorService.submit(()->{
                List<ShoppingMall> shoppingMallEntity = shoppingMallChunk.stream()
                        .map(this::getShoppingMall)
                        .filter(Objects::nonNull)
                        .toList();

                shoppingMallCsvRepository.saveAll(shoppingMallEntity);
            }));
        }

        for(Future<?> future : futures){
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e){
               throw new FileException();
            }
        }

        executorService.shutdown();

        return allLines.size()+"건";
    }

    private List<List<String>> splitIntoChunks(List<String> liest,int chunkSize){
        //CSV를 2000줄씩 끊어서 리스트에 저장
        List<List<String>> chunks = new ArrayList<>();

        for(int i = 0 ; i < liest.size() ; i += chunkSize){
            chunks.add(liest.subList(i,Math.min(i+chunkSize, liest.size())));
        }
        return chunks;
    }


    //클라이언트가 업로드 한 CSV 파일 받는다
    public ShoppingMall getShoppingMall(String line) {

        //List<Future<?>> futures = new ArrayList<>();
        //ShoppingMall타입으로 리스트 선언
        //엔티티 타입의 값만 반영 가능
        //List<ShoppingMall> shoppingMallList = new ArrayList<>();
        //라인 변수 선언
        //List<String> lines;
        //String line;

        //받은 파일이 내용이 비워져 있으면 예외처리
//        if (file.isEmpty()) {
//            throw new FindException();
//        }

        //파일을 한줄 씩 읽기 가능하도록 설정
        //받은 CSV 파일 바이트 형태로 받은 후 해당 파일을 문자열로 변환 그 후 버퍼링을 통해 효율적으로 읽도록 설정
        //try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

        //지금 읽는 줄이 첫번째 줄인지 여부 판단하는 boolean플래그 변수 활성화
        //boolean isFirst = true;
        //가져온 파일 이름 가져오기
        //String fileName = file.getName();

        //첫번째 읽은 값 받은 후 continue로 건너뛴 후 플래그 변수 비활성화
        //받은 문자열 파일 readLine을 통해
//            while ((line = reader.readLine()) != null) {
//                if (isFirst) {
//                    isFirst = false;
//                    continue;
//                }

        //필드 내 값이 없어도 한 공간으로 판단해서 리스트에 반영 설정 -1이
        //없으면 값 있는 필드만 리스트에 반영
        String[] fields = line.split(",", -1);
        String[] cleanedFields = Arrays.stream(fields)
                .map(s -> s.replace("\"",""))
                .toArray(String[]::new);

        //열이 30개 미만이면 아래 로직 건너뜀 이상이면 진행
        if (fields.length < 33)
            //trim(공백제거)
            //엔티티에 저장된 값 변수에 저장
            return new ShoppingMall(
                    //문자열을 Long 타입으로 변환 & 문자열을 int 타입으로 변환
                    //Long.parseLong(fields[0]),Integer.getInteger(fields[0])
                    cleanedFields[22],cleanedFields[11],cleanedFields[0],cleanedFields[9],
                    cleanedFields[6],cleanedFields[17],cleanedFields[13],cleanedFields[25],
                    cleanedFields[8],cleanedFields[28],cleanedFields[26],cleanedFields[2],
                    cleanedFields[7],cleanedFields[16],cleanedFields[1],cleanedFields[18],cleanedFields[29],
                    cleanedFields[31],cleanedFields[4],cleanedFields[10],
                    cleanedFields[13],cleanedFields[19],cleanedFields[3],cleanedFields[21],cleanedFields[15],
                    cleanedFields[23],cleanedFields[27],cleanedFields[5],cleanedFields[24],cleanedFields[30],
                    cleanedFields[20],cleanedFields[14]
            );



        //엔티티에 저장한 데이터 리스트에 반영
        //shoppingMallList.add(shoppingMall);

        //리스트 사이즈가 rageSize보다 크거나 같을때 저장/같지 않거나 작을때 저장 종료
//                if(shoppingMallList.size() >= BATCH_SIZE){
//                    //데이터베이스에 리스트 내 데이터 저장
//                    shoppingMallCsvRepository.saveAll(shoppingMallList);
//                    shoppingMallList.clear();
//                }

        //리스트에 값이 존재할때 저장 진행/값이 없을때 저장 종료
//            if(!shoppingMallList.isEmpty()){
//                //남은 데이터 마무리로 저장
//                shoppingMallCsvRepository.saveAll(shoppingMallList);
//                //모든 요소만 제거(주의 : 내부에 모든 값이 제거되어 사이즈가 0이 됨 isEmpty도 true됨
//                //리스트가 null이 되는것은 아님 빈 공간이라도 있으니
//                shoppingMallList.clear();
//            }
        //null사용한 이유는 어차피 읽기 전용 저수준 메서드이기때문(저수준 메서드) 활용
        //비지니스 로직을 담는 도메인과 같은 고수준의 모듈에서는 null 절대 사용 금지
        return null;
    }
}
// 1분 9초가 한계... > 이후 피드백 받고 진행
