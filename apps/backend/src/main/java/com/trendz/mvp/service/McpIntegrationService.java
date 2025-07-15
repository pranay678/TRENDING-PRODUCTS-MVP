package com.trendz.mvp.service;

import com.trendz.mvp.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class McpIntegrationService {
    private final WebClient webClient;

    @Value("${mcp.api.url:http://localhost:3001}")
    private String mcpApiUrl;
    @Value("${mcp.api.key:}")
    private String mcpApiKey;

    public McpIntegrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "mcpProducts", key = "#query + #country + #state + #city + #maxPrice + #currency + #category")
    public Mono<List<Product>> getProductsFromMcpServer(
            String query, 
            String country, 
            String state, 
            String city, 
            BigDecimal maxPrice, 
            String currency, 
            String category) {
        
        // For now, return empty list since MCP server is not implemented
        return Mono.just(List.of());
    }

    @Cacheable(value = "mcpTrending", key = "#country + #state + #city + #category")
    public Mono<List<Product>> getTrendingFromMcpServer(
            String country, 
            String state, 
            String city, 
            String category) {
        
        // For now, return empty list since MCP server is not implemented
        return Mono.just(List.of());
    }

    @Cacheable(value = "mcpWholesale", key = "#country + #state + #city + #maxWholesalePrice + #category")
    public Mono<List<Product>> getWholesaleOpportunitiesFromMcpServer(
            String country, 
            String state, 
            String city, 
            BigDecimal maxWholesalePrice, 
            String category) {
        
        // For now, return empty list since MCP server is not implemented
        return Mono.just(List.of());
    }

    public Mono<Map<String, Object>> getMcpServerStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "offline");
        status.put("error", "MCP server not implemented yet");
        return Mono.just(status);
    }
}
