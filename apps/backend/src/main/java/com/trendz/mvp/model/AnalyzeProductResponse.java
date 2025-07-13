package com.trendz.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyzeProductResponse {
    private String recommendation;
    private int confidence;
    private String reasoning;
}
