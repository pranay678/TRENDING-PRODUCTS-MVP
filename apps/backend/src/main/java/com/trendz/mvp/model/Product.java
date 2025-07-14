package com.trendz.mvp.model;

public class Product {
  private int id;
  private String name;
  private int price;
  private String platform;
  private String imageUrl;

  public Product() {
  }

  public Product(int id, String name, int price, String platform, String imageUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.platform = platform;
    this.imageUrl = imageUrl;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
