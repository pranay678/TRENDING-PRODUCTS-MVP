package com.trendz.mvp.service;

import com.trendz.mvp.model.Product;
import com.trendz.mvp.model.Trend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalDataService {
    private final WebClient webClient;

    @Value("${external.amazon.api.url}")
    private String amazonApiUrl;
    @Value("${external.amazon.api.key}")
    private String amazonApiKey;
    @Value("${external.junglescout.api.url}")
    private String jungleScoutApiUrl;
    @Value("${external.junglescout.api.key}")
    private String jungleScoutApiKey;
    @Value("${external.googletrends.api.url}")
    private String googleTrendsApiUrl;

    public ExternalDataService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable(value = "amazonProducts", key = "#keyword")
    public Mono<List<Product>> getAmazonProducts(String keyword) {
        return webClient.get()
                .uri(amazonApiUrl + "/search?query=" + keyword)
                .header("Authorization", "Bearer " + amazonApiKey)
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList();
    }

    @Cacheable(value = "jungleScoutProducts", key = "#keyword")
    public Mono<List<Product>> getJungleScoutProducts(String keyword) {
        return webClient.get()
                .uri(jungleScoutApiUrl + "/search?query=" + keyword)
                .header("Authorization", "Bearer " + jungleScoutApiKey)
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList();
    }

    @Cacheable(value = "googleTrends", key = "#keyword")
    public Mono<List<Trend>> getGoogleTrends(String keyword) {
        return webClient.get()
                .uri(googleTrendsApiUrl + "/trends?query=" + keyword)
                .retrieve()
                .bodyToFlux(Trend.class)
                .collectList();
    }
}
