package com.example.seoulshoppingmall.csv.controller;

import com.example.seoulshoppingmall.common.dto.ApiResponse;
import com.example.seoulshoppingmall.csv.dto.CsvFileResponse;
import com.example.seoulshoppingmall.csv.serivce.CsvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping
public class CsvController {
    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/collection")
    public ApiResponse<CsvFileResponse> CsvReadApi(@ModelAttribute MultipartFile csvFile) {
        ApiResponse<CsvFileResponse> fileUploadSuccess = csvService.csvFileUploadProcess(csvFile);
        return fileUploadSuccess;

    }
}
