package com.trendz.mvp.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    // Caching is enabled via @EnableCaching annotation
    // Caffeine cache configuration is handled in application.properties
} 