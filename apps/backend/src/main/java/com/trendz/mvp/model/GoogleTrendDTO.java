package com.trendz.mvp.model;

import lombok.Data;

@Data
public class GoogleTrendDTO {
    private String keyword;
    private Integer score;
    private String region;
}
