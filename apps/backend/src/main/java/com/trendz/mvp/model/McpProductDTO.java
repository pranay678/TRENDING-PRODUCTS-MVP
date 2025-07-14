package com.trendz.mvp.model;

import lombok.Data;

@Data
public class McpProductDTO {
    private String id;
    private String name;
    private String imageUrl;
    private String platform;
    private Double price;
}
