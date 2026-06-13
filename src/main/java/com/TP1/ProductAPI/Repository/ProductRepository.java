package com.TP1.ProductAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TP1.ProductAPI.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
