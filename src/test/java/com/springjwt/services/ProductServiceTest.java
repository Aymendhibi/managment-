package com.springjwt.services;

import com.springjwt.entities.Product;
import com.springjwt.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public  class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
   @Mock
    private ProductRepository productRepository;
   @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(25.0);
       Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
       Product createdProduct = productService.createProduct(product);
         assertEquals("Test Product", createdProduct.getName());
        assertEquals(25.0, createdProduct.getPrice(), 0.01); }
    @Test
    public void testGetProductById() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Product Name");
        mockProduct.setDescription("Product Description");
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        Product retrievedProduct = productService.getProductById(1L);
        assertNotNull(retrievedProduct);
        assertEquals(1L, retrievedProduct.getId());
        assertEquals("Product Name", retrievedProduct.getName());
        assertEquals("Product Description", retrievedProduct.getDescription());
    }

}
