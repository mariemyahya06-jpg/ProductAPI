package com.TP1.ProductAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TP1.ProductAPI.model.Product;

@Repository
public interface  ProductRepository extends JpaRepository<Product, Integer>{
    
}
