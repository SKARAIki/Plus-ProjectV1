package com.example.seoulshoppingmall.domain.mall.command;

import java.util.Collection;

public class SearchCommand {
    private final Integer overallRating;
    private final String businessStatus;

    public SearchCommand(Integer overallRating, String businessStatus) {
        this.overallRating = overallRating;
        this.businessStatus = businessStatus;
    }
    public boolean hasRating() {
        if (this.overallRating != null && this.overallRating >= 0 && this.overallRating <= 3) {
            return true;
        } else {
            return false;
        }
    }
    public boolean hasBusinessStatus() {
        if (this.businessStatus != null) {
            return true;
        } else {
            return false;
        }
    }
}
