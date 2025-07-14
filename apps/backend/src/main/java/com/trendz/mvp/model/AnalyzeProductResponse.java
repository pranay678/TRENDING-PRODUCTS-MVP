package com.trendz.mvp.model;

public class AnalyzeProductResponse {
  private String recommendation;
  private int confidence;
  private String reasoning;

  public AnalyzeProductResponse() {
  }

  public AnalyzeProductResponse(String recommendation, int confidence, String reasoning) {
    this.recommendation = recommendation;
    this.confidence = confidence;
    this.reasoning = reasoning;
  }

  public String getRecommendation() {
    return recommendation;
  }

  public void setRecommendation(String recommendation) {
    this.recommendation = recommendation;
  }

  public int getConfidence() {
    return confidence;
  }

  public void setConfidence(int confidence) {
    this.confidence = confidence;
  }

  public String getReasoning() {
    return reasoning;
  }

  public void setReasoning(String reasoning) {
    this.reasoning = reasoning;
  }
}
