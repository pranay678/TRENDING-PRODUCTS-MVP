package com.trendz.mvp.service;

import com.trendz.mvp.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class McpIntegrationService {
    private final WebClient webClient;

    @Value("${mcp.api.url}")
    private String mcpApiUrl;
    @Value("${mcp.api.key}")
    private String mcpApiKey;

    public McpIntegrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "mcpProducts", key = "#query")
    public Mono<List<Product>> getProductsFromMcpServer(String query) {
        return webClient.get()
                .uri(mcpApiUrl + "/products?query=" + query)
                .header("Authorization", "Bearer " + mcpApiKey)
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList();
    }
}
