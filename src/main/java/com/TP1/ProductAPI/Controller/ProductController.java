package com.TP1.ProductAPI.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TP1.ProductAPI.Services.ProductServices;
import com.TP1.ProductAPI.model.Product;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productServices.GetAllProducts();
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productServices.CreateProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable Integer id) {
        return productServices.GetProductByID(id);
    }
}
