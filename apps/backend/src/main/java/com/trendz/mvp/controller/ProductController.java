package com.trendz.mvp.controller;

import com.trendz.mvp.model.Product;
import com.trendz.mvp.model.Trend;
import com.trendz.mvp.service.AmazonProductService;
import com.trendz.mvp.service.JungleScoutService;
import com.trendz.mvp.service.GoogleTrendsService;
import com.trendz.mvp.service.WholesaleSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/trending-products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
  @Autowired private AmazonProductService amazonProductService;
  @Autowired private JungleScoutService jungleScoutService;
  @Autowired private GoogleTrendsService googleTrendsService;
  @Autowired private WholesaleSupplierService wholesaleSupplierService;

  @GetMapping("/search")
  public ResponseEntity<?> searchProducts(
      @RequestParam String keyword,
      @RequestParam(required = false) String country,
      @RequestParam(required = false) BigDecimal maxPrice,
      @RequestParam(required = false) String region) {
    List<Product> amazonProducts = amazonProductService.searchAmazonProducts(keyword, country, maxPrice);
    List<Product> jungleProducts = jungleScoutService.searchJungleScoutProducts(keyword);
    List<Product> indiamartProducts = wholesaleSupplierService.searchIndiaMart(keyword, region);
    List<Product> alibabaProducts = wholesaleSupplierService.searchAlibaba(keyword, region);

    // Merge and deduplicate products as needed
    List<Product> allProducts = new ArrayList<>();
    allProducts.addAll(amazonProducts);
    allProducts.addAll(jungleProducts);
    allProducts.addAll(indiamartProducts);
    allProducts.addAll(alibabaProducts);

    // Fetch trends
    List<Trend> trends = googleTrendsService.getTrends(keyword, region);

    Map<String, Object> result = new HashMap<>();
    result.put("products", allProducts);
    result.put("trends", trends);
    return ResponseEntity.ok(result);
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
