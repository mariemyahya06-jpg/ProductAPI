package com.TP1.ProductAPI.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP1.ProductAPI.Repository.ProductRepository;
import com.TP1.ProductAPI.exception.ResourceNotFoundException;
import com.TP1.ProductAPI.model.Product;
@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> GetAllProducts() {
        return productRepository.findAll();
    }
    public Product CreateProduct(Product product) {
        return productRepository.save(product);
    }
    public Product GetProductByID(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: "+id));
    }
}
