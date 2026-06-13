package com.TP1.ProductAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.TP1.ProductAPI.Repository.ProductRepository;
import com.TP1.ProductAPI.Services.ProductServices;
import com.TP1.ProductAPI.exception.ResourceNotFoundException;
import com.TP1.ProductAPI.model.Product;

@ExtendWith(MockitoExtension.class)
public class ProductServicesTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServices productServices;

    @Test
    void createProduct_shouldSaveProduct() {
        Product product = new Product();
        product.setName("Keyboard");
        product.setPrice(12000.0);
        product.setDescription("Clavier sans fil");

        when(productRepository.save(product)).thenReturn(product);

        Product result = productServices.CreateProduct(product);

        assertEquals("Keyboard", result.getName());
        assertEquals(12000.0, result.getPrice());
        assertEquals("Clavier sans fil", result.getDescription());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setPrice(80000.0);
        product1.setDescription("Ordinateur Portable");

        Product product2 = new Product();
        product2.setName("Phone");
        product2.setPrice(90000.0);
        product2.setDescription("Smart Phone Android");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productServices.GetAllProducts();

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals("Phone", result.get(1).getName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_shouldReturnProduct_whenProductExists() {
        Product product = new Product();
        product.setName("Mouse");
        product.setPrice(8000.0);
        product.setDescription("Souris sans fil");

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product result = productServices.GetProductByID(1);

        assertEquals("Mouse", result.getName());
        assertEquals(8000.0, result.getPrice());
        assertEquals("Souris sans fil", result.getDescription());

        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void getProductById_shouldThrowException_whenProductDoesNotExist() {
        when(productRepository.findById(99)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            productServices.GetProductByID(99);
        });

        assertEquals("Product not found with id: 99", exception.getMessage());

        verify(productRepository, times(1)).findById(99);
    }
}