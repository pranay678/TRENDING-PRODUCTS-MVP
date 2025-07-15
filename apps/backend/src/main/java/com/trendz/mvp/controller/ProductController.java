package com.trendz.mvp.controller;

import com.trendz.mvp.model.Product;
import com.trendz.mvp.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.Collections;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trending-products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/search")
  public Mono<ResponseEntity<HashMap<String, Object>>> searchProducts(
      @RequestParam(required = false) String query,
      @RequestParam(required = false) String country,
      @RequestParam(required = false) String state,
      @RequestParam(required = false) String city,
      @RequestParam(required = false) String region,
      @RequestParam(required = false) BigDecimal maxPrice,
      @RequestParam(required = false) String currency,
      @RequestParam(required = false) String category,
      @RequestParam(required = false) Boolean wholesaleOnly) {
    
    // Default to INR and 500 INR if not specified
    final String finalCurrency = currency != null ? currency : "INR";
    final BigDecimal finalMaxPrice = maxPrice != null ? maxPrice : new BigDecimal("500");
    final String finalCountry = country;
    final String finalState = state;
    final String finalCity = city;
    final String finalCategory = category;
    final Boolean finalWholesaleOnly = wholesaleOnly;
    
    // For now, just return products from the database
    return productService.getAllProducts()
      .map(products -> {
        List<Product> filteredProducts = products.stream()
          .filter(product -> {
            // Filter by price
            if (product.getPrice() != null && product.getPrice().compareTo(finalMaxPrice) > 0) {
              return false;
            }
            // Filter by country
            if (finalCountry != null && !finalCountry.isEmpty() && !finalCountry.equals(product.getCountry())) {
              return false;
            }
            // Filter by state
            if (finalState != null && !finalState.isEmpty() && !finalState.equals(product.getState())) {
              return false;
            }
            // Filter by city
            if (finalCity != null && !finalCity.isEmpty() && !finalCity.equals(product.getCity())) {
              return false;
            }
            // Filter by category
            if (finalCategory != null && !finalCategory.isEmpty() && !finalCategory.equals(product.getCategory())) {
              return false;
            }
            // Filter by wholesale
            if (finalWholesaleOnly != null && finalWholesaleOnly && product.getWholesalePrice() == null) {
              return false;
            }
            return true;
          })
          .collect(Collectors.toList());

        // Sort by trend score
        filteredProducts.sort((p1, p2) -> {
          Integer score1 = p1.getTrendScore() != null ? p1.getTrendScore() : 0;
          Integer score2 = p2.getTrendScore() != null ? p2.getTrendScore() : 0;
          return score2.compareTo(score1);
        });

        HashMap<String, Object> result = new HashMap<>();
        result.put("query", query);
        
        Map<String, Object> filters = new HashMap<>();
        filters.put("country", finalCountry);
        filters.put("state", finalState);
        filters.put("city", finalCity);
        filters.put("region", region);
        filters.put("maxPrice", finalMaxPrice);
        filters.put("currency", finalCurrency);
        filters.put("category", finalCategory);
        filters.put("wholesaleOnly", finalWholesaleOnly);
        result.put("filters", filters);
        
        result.put("timestamp", Instant.now().toString());
        result.put("products", filteredProducts);
        result.put("trends", Collections.emptyList()); // Empty trends for now
        result.put("totalProducts", filteredProducts.size());
        return ResponseEntity.ok(result);
      });
  }

  @GetMapping("/trending")
  public Mono<ResponseEntity<HashMap<String, Object>>> getTrendingProducts(
      @RequestParam(required = false) String country,
      @RequestParam(required = false) String state,
      @RequestParam(required = false) String city,
      @RequestParam(required = false) BigDecimal maxPrice,
      @RequestParam(required = false) String category) {
    
    final BigDecimal finalMaxPrice = maxPrice != null ? maxPrice : new BigDecimal("500");
    final String finalCountry = country != null ? country : "India";
    final String finalState = state;
    final String finalCity = city;
    final String finalCategory = category;
    
    return productService.getTrendingProducts(finalCountry, finalState, finalCity, finalMaxPrice, finalCategory)
      .map(products -> {
        HashMap<String, Object> result = new HashMap<>();
        result.put("trendingProducts", products);
        
        Map<String, Object> filters = new HashMap<>();
        filters.put("country", finalCountry);
        filters.put("state", finalState);
        filters.put("city", finalCity);
        filters.put("maxPrice", finalMaxPrice);
        filters.put("category", finalCategory);
        result.put("filters", filters);
        
        result.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(result);
      });
  }

  @GetMapping("/wholesale-opportunities")
  public Mono<ResponseEntity<HashMap<String, Object>>> getWholesaleOpportunities(
      @RequestParam(required = false) String country,
      @RequestParam(required = false) String state,
      @RequestParam(required = false) String city,
      @RequestParam(required = false) BigDecimal maxWholesalePrice,
      @RequestParam(required = false) String category) {
    
    final BigDecimal finalMaxWholesalePrice = maxWholesalePrice != null ? maxWholesalePrice : new BigDecimal("300");
    final String finalCountry = country != null ? country : "India";
    final String finalState = state;
    final String finalCity = city;
    final String finalCategory = category;
    
    return productService.getWholesaleOpportunities(finalCountry, finalState, finalCity, finalMaxWholesalePrice, finalCategory)
      .map(products -> {
        HashMap<String, Object> result = new HashMap<>();
        result.put("wholesaleOpportunities", products);
        
        Map<String, Object> filters = new HashMap<>();
        filters.put("country", finalCountry);
        filters.put("state", finalState);
        filters.put("city", finalCity);
        filters.put("maxWholesalePrice", finalMaxWholesalePrice);
        filters.put("category", finalCategory);
        result.put("filters", filters);
        
        result.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(result);
      });
  }
}
