package com.trendz.mvp.service;

import com.trendz.mvp.model.AnalyzeProductRequest;
import com.trendz.mvp.model.AnalyzeProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AIService {
  @Value("${openai.api.key}")
  private String openaiApiKey;

  private final WebClient webClient = WebClient.builder()
      .baseUrl("https://api.openai.com/v1/chat/completions")
      .defaultHeader("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
      .build();

  public Mono<AnalyzeProductResponse> analyzeProduct(AnalyzeProductRequest request) {
    // For now, return a mock response. Replace with OpenAI API call as needed.
    return Mono.just(new AnalyzeProductResponse(
        "Yes",
        92,
        "High demand and low competition make it a good candidate."));
  }
}
