package com.trendz.mvp.model;

import lombok.Data;

@Data
public class AnalyzeProductRequest {
  private String name;
  private int price;
  private String platform;
}
