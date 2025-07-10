package com.example.trendsixtown.domain.service;

import com.example.trendsixtown.domain.entity.ShoppingMall;
import com.example.trendsixtown.domain.repository.ShoppingMallCsvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShoppingMallCsvService {

    private final ShoppingMallCsvRepository shoppingMallCsvRepository;

    //클라이언트가 업로드 한 CSV 파일 받는다
    public String getShoppingMall(MultipartFile file) {

        //ShoppingMall타입으로 리스트 선언
        //엔티티 타입의 값만 반영 가능
        List<ShoppingMall> shoppingMallList = new ArrayList<>();
        //데이터를 담을 수 있는 최대 사이즈 선언
        final int BATCH_SIZE = 1000;

        //받은 파일이 내용이 비워져 있으면 예외처리
        if (file.isEmpty()) {
            throw new FindException();
        }
        //파일을 한줄 씩 읽기 가능하도록 설정
        //받은 CSV 파일 바이트 형태로 받은 후 해당 파일을 문자열로 변환 그 후 버퍼링을 통해 효율적으로 읽도록 설정
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            //라인 변수 선언
            String line;
            //지금 읽는 줄이 첫번째 줄인지 여부 판단하는 boolean플래그 변수 활성화
            boolean isFirst = true;
            //가져온 파일 이름 가져오기
            String fileName = file.getName();

            //첫번째 읽은 값 받은 후 continue로 건너뛴 후 플래그 변수 비활성화
            //받은 문자열 파일 readLine을 통해
            while ((line = reader.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                //필드 내 값이 없어도 한 공간으로 판단해서 리스트에 반영 설정 -1이
                //없으면 값 있는 필드만 리스트에 반영
                String[] fields = line.split(",",-1);

                //열이 30개 미만이면 아래 로직 건너뜀 이상이면 진행
                if (fields.length < 33) continue;

                //trim(공백제거)
                //엔티티에 저장된 값 변수에 저장
                ShoppingMall shoppingMall = new ShoppingMall(
                        //문자열을 Long 타입으로 변환 & 문자열을 int 타입으로 변환
                        //Long.parseLong(fields[0]),Integer.getInteger(fields[0])
                        fields[22], fields[11], fields[0], fields[9], fields[6],
                        fields[17], fields[13], fields[25], fields[8], fields[28],
                        fields[26], fields[2], fields[7], fields[16], fields[1],
                        fields[18], fields[29], fields[31], fields[4], fields[10],
                        fields[13], fields[19], fields[3], fields[21], fields[15],
                        fields[23], fields[27], fields[5], fields[24], fields[30],
                        fields[20], fields[14]
                );

                //엔티티에 저장한 데이터 리스트에 반영
                shoppingMallList.add(shoppingMall);

                //리스트 사이즈가 rageSize보다 크거나 같을때 저장/같지 않거나 작을때 저장 종료
                if(shoppingMallList.size() >= BATCH_SIZE){
                    //데이터베이스에 리스트 내 데이터 저장
                    shoppingMallCsvRepository.saveAll(shoppingMallList);
                    //모든 요소만 제거(주의 : 내부에 모든 값이 제거되어 사이즈가 0이 됨 isEmpty도 true됨
                    //리스트가 null이 되는것은 아님 빈 공간이라도 있으니
                    shoppingMallList.clear();
                }
            }

            //리스트에 값이 존재할때 저장 진행/값이 없을때 저장 종료
            if(!shoppingMallList.isEmpty()){
                //남은 데이터 마무리로 저장
                shoppingMallCsvRepository.saveAll(shoppingMallList);
            }

            //결과 반환
            return fileName + "을 업로드 및 저장하였습니다.";
        } catch (IOException e) {
            throw new FindException();
        }
    }
}
// 1분 9초가 한계... > 이후 피드백 받고 진행
