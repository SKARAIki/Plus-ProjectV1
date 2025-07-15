package com.example.seoulshoppingmall.csv.dto;

public class CsvFileResponse {
   private final int totalCount;
   private final int successCount;
   private final int failCount;

    public CsvFileResponse(int totalCount, int successCount, int failCount) {
        this.totalCount = totalCount;
        this.successCount = successCount;
        this.failCount = failCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getFailCount() {
        return failCount;
    }
}
