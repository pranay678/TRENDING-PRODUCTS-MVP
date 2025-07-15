package com.trendz.mvp.config;

import com.trendz.mvp.model.Product;
import com.trendz.mvp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only load data if no products exist
        if (productRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        Product[] sampleProducts = new Product[8];
        
        // Create products using the default constructor
        for (int i = 0; i < 8; i++) {
            sampleProducts[i] = new Product();
        }

        // Product 1: Wireless Bluetooth Headphones
        sampleProducts[0].setId(1);
        sampleProducts[0].setName("Wireless Bluetooth Headphones");
        sampleProducts[0].setPrice(new BigDecimal("450"));
        sampleProducts[0].setCurrency("INR");
        sampleProducts[0].setPlatform("Amazon");
        sampleProducts[0].setImageUrl("https://via.placeholder.com/300x300?text=Headphones");
        sampleProducts[0].setCountry("India");
        sampleProducts[0].setState("Maharashtra");
        sampleProducts[0].setCity("Mumbai");
        sampleProducts[0].setWholesalePrice(new BigDecimal("300"));
        sampleProducts[0].setWholesaleSupplier("TechWholesale India");
        sampleProducts[0].setTrendScore(85);
        sampleProducts[0].setSearchVolume(15000);
        sampleProducts[0].setCategory("Electronics");
        sampleProducts[0].setProductUrl("https://amazon.in/product1");

        // Product 2: Smart Fitness Band
        sampleProducts[1].setId(2);
        sampleProducts[1].setName("Smart Fitness Band");
        sampleProducts[1].setPrice(new BigDecimal("399"));
        sampleProducts[1].setCurrency("INR");
        sampleProducts[1].setPlatform("Flipkart");
        sampleProducts[1].setImageUrl("https://via.placeholder.com/300x300?text=Fitness+Band");
        sampleProducts[1].setCountry("India");
        sampleProducts[1].setState("Karnataka");
        sampleProducts[1].setCity("Bangalore");
        sampleProducts[1].setWholesalePrice(new BigDecimal("250"));
        sampleProducts[1].setWholesaleSupplier("FitnessGear Pro");
        sampleProducts[1].setTrendScore(92);
        sampleProducts[1].setSearchVolume(22000);
        sampleProducts[1].setCategory("Health & Wellness");
        sampleProducts[1].setProductUrl("https://flipkart.com/product2");

        // Product 3: Portable Power Bank
        sampleProducts[2].setId(3);
        sampleProducts[2].setName("Portable Power Bank 10000mAh");
        sampleProducts[2].setPrice(new BigDecimal("499"));
        sampleProducts[2].setCurrency("INR");
        sampleProducts[2].setPlatform("Amazon");
        sampleProducts[2].setImageUrl("https://via.placeholder.com/300x300?text=Power+Bank");
        sampleProducts[2].setCountry("India");
        sampleProducts[2].setState("Delhi");
        sampleProducts[2].setCity("New Delhi");
        sampleProducts[2].setWholesalePrice(new BigDecimal("350"));
        sampleProducts[2].setWholesaleSupplier("PowerTech Solutions");
        sampleProducts[2].setTrendScore(78);
        sampleProducts[2].setSearchVolume(12000);
        sampleProducts[2].setCategory("Electronics");
        sampleProducts[2].setProductUrl("https://amazon.in/product3");

        // Product 4: Wireless Mouse
        sampleProducts[3].setId(4);
        sampleProducts[3].setName("Wireless Mouse");
        sampleProducts[3].setPrice(new BigDecimal("299"));
        sampleProducts[3].setCurrency("INR");
        sampleProducts[3].setPlatform("Flipkart");
        sampleProducts[3].setImageUrl("https://via.placeholder.com/300x300?text=Mouse");
        sampleProducts[3].setCountry("India");
        sampleProducts[3].setState("Tamil Nadu");
        sampleProducts[3].setCity("Chennai");
        sampleProducts[3].setWholesalePrice(new BigDecimal("180"));
        sampleProducts[3].setWholesaleSupplier("Computer Accessories Hub");
        sampleProducts[3].setTrendScore(65);
        sampleProducts[3].setSearchVolume(8000);
        sampleProducts[3].setCategory("Electronics");
        sampleProducts[3].setProductUrl("https://flipkart.com/product4");

        // Product 5: USB Type-C Cable
        sampleProducts[4].setId(5);
        sampleProducts[4].setName("USB Type-C Cable");
        sampleProducts[4].setPrice(new BigDecimal("199"));
        sampleProducts[4].setCurrency("INR");
        sampleProducts[4].setPlatform("Amazon");
        sampleProducts[4].setImageUrl("https://via.placeholder.com/300x300?text=USB+Cable");
        sampleProducts[4].setCountry("India");
        sampleProducts[4].setState("Gujarat");
        sampleProducts[4].setCity("Ahmedabad");
        sampleProducts[4].setWholesalePrice(new BigDecimal("120"));
        sampleProducts[4].setWholesaleSupplier("CableCorp India");
        sampleProducts[4].setTrendScore(45);
        sampleProducts[4].setSearchVolume(5000);
        sampleProducts[4].setCategory("Electronics");
        sampleProducts[4].setProductUrl("https://amazon.in/product5");

        // Product 6: Phone Stand Holder
        sampleProducts[5].setId(6);
        sampleProducts[5].setName("Phone Stand Holder");
        sampleProducts[5].setPrice(new BigDecimal("150"));
        sampleProducts[5].setCurrency("INR");
        sampleProducts[5].setPlatform("Flipkart");
        sampleProducts[5].setImageUrl("https://via.placeholder.com/300x300?text=Phone+Stand");
        sampleProducts[5].setCountry("India");
        sampleProducts[5].setState("West Bengal");
        sampleProducts[5].setCity("Kolkata");
        sampleProducts[5].setWholesalePrice(new BigDecimal("80"));
        sampleProducts[5].setWholesaleSupplier("Mobile Accessories Plus");
        sampleProducts[5].setTrendScore(55);
        sampleProducts[5].setSearchVolume(6000);
        sampleProducts[5].setCategory("Electronics");
        sampleProducts[5].setProductUrl("https://flipkart.com/product6");

        // Product 7: Laptop Cooling Pad
        sampleProducts[6].setId(7);
        sampleProducts[6].setName("Laptop Cooling Pad");
        sampleProducts[6].setPrice(new BigDecimal("350"));
        sampleProducts[6].setCurrency("INR");
        sampleProducts[6].setPlatform("Amazon");
        sampleProducts[6].setImageUrl("https://via.placeholder.com/300x300?text=Cooling+Pad");
        sampleProducts[6].setCountry("India");
        sampleProducts[6].setState("Telangana");
        sampleProducts[6].setCity("Hyderabad");
        sampleProducts[6].setWholesalePrice(new BigDecimal("220"));
        sampleProducts[6].setWholesaleSupplier("LaptopGear Pro");
        sampleProducts[6].setTrendScore(70);
        sampleProducts[6].setSearchVolume(9000);
        sampleProducts[6].setCategory("Electronics");
        sampleProducts[6].setProductUrl("https://amazon.in/product7");

        // Product 8: Bluetooth Speaker
        sampleProducts[7].setId(8);
        sampleProducts[7].setName("Bluetooth Speaker");
        sampleProducts[7].setPrice(new BigDecimal("480"));
        sampleProducts[7].setCurrency("INR");
        sampleProducts[7].setPlatform("Flipkart");
        sampleProducts[7].setImageUrl("https://via.placeholder.com/300x300?text=Speaker");
        sampleProducts[7].setCountry("India");
        sampleProducts[7].setState("Rajasthan");
        sampleProducts[7].setCity("Jaipur");
        sampleProducts[7].setWholesalePrice(new BigDecimal("320"));
        sampleProducts[7].setWholesaleSupplier("AudioTech Solutions");
        sampleProducts[7].setTrendScore(88);
        sampleProducts[7].setSearchVolume(18000);
        sampleProducts[7].setCategory("Electronics");
        sampleProducts[7].setProductUrl("https://flipkart.com/product8");

        productRepository.saveAll(Arrays.asList(sampleProducts));
        System.out.println("Sample data loaded successfully!");
    }
} 