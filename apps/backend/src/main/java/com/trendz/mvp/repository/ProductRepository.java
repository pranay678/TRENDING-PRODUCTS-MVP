package com.trendz.mvp.repository;

import com.trendz.mvp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query("SELECT p FROM Product p WHERE " +
           "(:country IS NULL OR p.country = :country) AND " +
           "(:state IS NULL OR p.state = :state) AND " +
           "(:city IS NULL OR p.city = :city) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:category IS NULL OR p.category = :category) AND " +
           "p.isActive = true " +
           "ORDER BY p.trendScore DESC")
    List<Product> findTrendingProducts(
        @Param("country") String country,
        @Param("state") String state,
        @Param("city") String city,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("category") String category
    );

    @Query("SELECT p FROM Product p WHERE " +
           "(:country IS NULL OR p.country = :country) AND " +
           "(:state IS NULL OR p.state = :state) AND " +
           "(:city IS NULL OR p.city = :city) AND " +
           "(:maxWholesalePrice IS NULL OR p.wholesalePrice <= :maxWholesalePrice) AND " +
           "(:category IS NULL OR p.category = :category) AND " +
           "p.wholesalePrice IS NOT NULL AND " +
           "p.isActive = true " +
           "ORDER BY (p.price - p.wholesalePrice) DESC")
    List<Product> findWholesaleOpportunities(
        @Param("country") String country,
        @Param("state") String state,
        @Param("city") String city,
        @Param("maxWholesalePrice") BigDecimal maxWholesalePrice,
        @Param("category") String category
    );

    @Query("SELECT p FROM Product p WHERE " +
           "p.price <= :maxPrice AND " +
           "p.country = :country AND " +
           "p.isActive = true " +
           "ORDER BY p.trendScore DESC")
    List<Product> findProductsByPriceAndCountry(
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("country") String country
    );

    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.isActive = true")
    List<String> findAllCategories();

    @Query("SELECT DISTINCT p.country FROM Product p WHERE p.isActive = true")
    List<String> findAllCountries();

    @Query("SELECT DISTINCT p.state FROM Product p WHERE p.country = :country AND p.isActive = true")
    List<String> findStatesByCountry(@Param("country") String country);

    @Query("SELECT DISTINCT p.city FROM Product p WHERE p.state = :state AND p.isActive = true")
    List<String> findCitiesByState(@Param("state") String state);
}
