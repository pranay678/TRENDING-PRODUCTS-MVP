package com.trendz.mvp.service;

import com.trendz.mvp.model.Product;
import com.trendz.mvp.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<List<Product>> getTrendingProducts(String country, String state, String city, BigDecimal maxPrice, String category) {
        return Mono.fromCallable(() -> {
            List<Product> products = productRepository.findTrendingProducts(country, state, city, maxPrice, category);
            return products.stream()
                .filter(product -> product.getTrendScore() != null && product.getTrendScore() > 50)
                .sorted((p1, p2) -> p2.getTrendScore().compareTo(p1.getTrendScore()))
                .limit(20)
                .collect(Collectors.toList());
        });
    }

    public Mono<List<Product>> getWholesaleOpportunities(String country, String state, String city, BigDecimal maxWholesalePrice, String category) {
        return Mono.fromCallable(() -> {
            List<Product> products = productRepository.findWholesaleOpportunities(country, state, city, maxWholesalePrice, category);
            return products.stream()
                .filter(product -> product.getWholesalePrice() != null && product.getWholesalePrice().compareTo(maxWholesalePrice) <= 0)
                .sorted((p1, p2) -> {
                    // Sort by profit margin (retail price - wholesale price)
                    BigDecimal margin1 = p1.getPrice().subtract(p1.getWholesalePrice());
                    BigDecimal margin2 = p2.getPrice().subtract(p2.getWholesalePrice());
                    return margin2.compareTo(margin1);
                })
                .limit(20)
                .collect(Collectors.toList());
        });
    }

    public Mono<Product> saveProduct(Product product) {
        return Mono.fromCallable(() -> productRepository.save(product));
    }

    public Mono<List<Product>> getAllProducts() {
        return Mono.fromCallable(() -> productRepository.findAll());
    }

    public Mono<Product> getProductById(int id) {
        return Mono.fromCallable(() -> productRepository.findById(id).orElse(null));
    }
}
