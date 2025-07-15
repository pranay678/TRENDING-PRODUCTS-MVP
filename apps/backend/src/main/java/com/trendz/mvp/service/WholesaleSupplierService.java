package com.trendz.mvp.service;

import com.trendz.mvp.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WholesaleSupplierService {
    @Value("${indiamart.api.key:}")
    private String indiamartKey;
    @Value("${alibaba.api.key:}")
    private String alibabaKey;

    public List<Product> searchIndiaMart(String keyword, String region) {
        // TODO: Call IndiaMART API and map to List<Product>
        return List.of(); // placeholder
    }

    public List<Product> searchAlibaba(String keyword, String region) {
        // TODO: Call Alibaba API and map to List<Product>
        return List.of(); // placeholder
    }
} 