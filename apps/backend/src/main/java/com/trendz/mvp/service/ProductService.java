package com.trendz.mvp.service;

import com.trendz.mvp.model.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;

@Service
public class ProductService {
    public List<Product> getTrendingProducts() {
        return Arrays.asList(
            new Product(1, "Wireless Earbuds", 499, "Amazon", "https://via.placeholder.com/150"),
            new Product(2, "Fashion Watch", 299, "Flipkart", "https://via.placeholder.com/150")
        );
    }
}
