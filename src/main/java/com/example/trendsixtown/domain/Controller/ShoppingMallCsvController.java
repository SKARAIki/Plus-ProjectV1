package com.example.trendsixtown.domain.Controller;

import com.example.trendsixtown.common.dto.ApiResponse;
import com.example.trendsixtown.domain.service.ShoppingMallCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/csv")
public class ShoppingMallCsvController {

    private final ShoppingMallCsvService shoppingMallCsvService;

    @PostMapping("/collection")
    public ResponseEntity<ApiResponse<String>> getShoppingMallCSV(@RequestParam("mall")MultipartFile file) throws IOException {

        String cvs = shoppingMallCsvService.uploadCsv(file);

        return new ResponseEntity<>(ApiResponse.success(HttpStatus.OK,cvs,"CSV 전송 및 업로드 완료"),HttpStatus.OK);
    }
}
