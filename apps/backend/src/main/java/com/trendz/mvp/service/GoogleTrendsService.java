package com.trendz.mvp.service;

import com.trendz.mvp.model.Trend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class GoogleTrendsService {
    private final WebClient webClient;

    @Value("${googletrends.api.url:http://localhost:5000/trends}")
    private String trendsApiUrl;

    public GoogleTrendsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<Trend> getTrends(String keyword, String region) {
        // TODO: Call your Python microservice or a public trends API
        // Example: GET http://localhost:5000/trends?keyword=...&region=...
        // Map response to List<Trend>
        return List.of(); // placeholder
    }
} 