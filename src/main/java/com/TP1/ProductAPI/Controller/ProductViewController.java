package com.TP1.ProductAPI.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.TP1.ProductAPI.Services.ProductServices;
import com.TP1.ProductAPI.model.Product;

/**
 * Controleur Thymeleaf (interface web).
 * Ne touche pas a l'API REST "/api/products" : ce controleur utilise "/products".
 */
@Controller
public class ProductViewController {

    private final ProductServices productServices;

    public ProductViewController(ProductServices productServices) {
        this.productServices = productServices;
    }

    // Affiche la liste des produits + un formulaire d'ajout
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productServices.GetAllProducts());
        model.addAttribute("newProduct", new Product());
        return "products";
    }

    // Recoit le formulaire, enregistre le produit, puis revient a la liste
    @PostMapping("/products")
    public String addProduct(@ModelAttribute("newProduct") Product product) {
        productServices.CreateProduct(product);
        return "redirect:/products";
    }
}
