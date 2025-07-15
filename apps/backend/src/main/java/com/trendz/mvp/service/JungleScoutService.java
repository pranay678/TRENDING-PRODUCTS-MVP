package com.trendz.mvp.service;

import com.trendz.mvp.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JungleScoutService {
    @Value("${junglescout.api.key:}")
    private String apiKey;

    public List<Product> searchJungleScoutProducts(String keyword) {
        // TODO: Call JungleScout API and map to List<Product>
        return List.of(); // placeholder
    }
} 