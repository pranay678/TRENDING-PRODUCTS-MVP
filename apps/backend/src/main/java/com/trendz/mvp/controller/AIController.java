package com.trendz.mvp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.trendz.mvp.model.AnalyzeProductRequest;
import com.trendz.mvp.model.AnalyzeProductResponse;
import com.trendz.mvp.service.AIService;

@RestController
@RequestMapping("/api/analyze-product")
@CrossOrigin(origins = "http://localhost:3000")
public class AIController {
  private final AIService aiService;

  public AIController(AIService aiService) {
    this.aiService = aiService;
  }

  @PostMapping
  public AnalyzeProductResponse analyzeProduct(@RequestBody AnalyzeProductRequest request) {
    // For now, return mock response synchronously
    return aiService.analyzeProduct(request).block();
  }
}
