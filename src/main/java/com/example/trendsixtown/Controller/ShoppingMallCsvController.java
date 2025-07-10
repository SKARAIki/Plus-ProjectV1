package com.example.trendsixtown.Controller;

import com.example.trendsixtown.service.ShoppingMallCsvService;
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
    public ResponseEntity<String> getShoppingMallCSV(@RequestParam("mall")MultipartFile file) {

        String cvs = shoppingMallCsvService.getShoppingMall(file);

        return new ResponseEntity<>(cvs,HttpStatus.OK);
    }
}
