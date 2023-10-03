package com.springjwt.services;

import com.springjwt.entities.Product;
import com.springjwt.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Ajoutez les méthodes pour la mise à jour et la suppression ici
}

