package com.prashant.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prashant.ecommerce.model.Product;
import com.prashant.ecommerce.repository.ProductRepository;
import com.prashant.ecommerce.service.ProductService;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testSaveProduct() {
        // Arrange
    	Product product = new Product();
    	product.setName("Sample Name");
    	product.setDescription("Sample Description");
    	product.setCategory("Sample Category");
    	product.setPrice(100);
        

        when(productRepository.save(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productService.addProduct(product);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());

    }
    
    @Test
    public void testGetProductById() {
        // Arrange
        int productId = 1;
        Product product = new Product();
        product.setName("Sample Name");
        product.setDescription("Sample Description");
        product.setCategory("Sample Category");
        product.setPrice(100);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Product> response = productService.findProduct(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
    	int productId = 1;
    	
        Product existingProduct = new Product();
        existingProduct.setName("Sample Name");
        existingProduct.setDescription("Sample Description");
        existingProduct.setCategory("Sample Category");
        existingProduct.setPrice(100);
        

        Product updatedProduct = new Product();
        updatedProduct.setName("Sample Name");
        updatedProduct.setDescription("Sample Description");
        updatedProduct.setCategory("Sample Category");
        updatedProduct.setPrice(100);
        
       
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        // Act
        ResponseEntity<Product> response = productService.updateProduct(productId, updatedProduct);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }
    
    @Test
    public void testDeleteBook() {
    	int productId = 1;
        Product product = new Product();
        product.setName("Sample Name");
        product.setDescription("Sample Description");
        product.setCategory("Sample Category");
        product.setPrice(100);

        // Mock the product repository to return a non-empty Optional when findById is called
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<String> deletedResponse = productService.deleteProduct(productId);

        // Assert
        assertEquals("Deleted succesfully", deletedResponse.getBody());
        assertEquals(HttpStatus.OK, deletedResponse.getStatusCode());
    }
    
    
    @Test
    public void testSearchProducts() {
        // Arrange
        String query = "Sample Query";
        int pageNumber = 0;
        int pageSize = 5;

        List<Product> searchResultsList = new ArrayList<>();
        searchResultsList.add(new Product(123, "Sample Name", "Sample Description", "Sample Category", 100.00d));
        Page<Product> searchResultsPage = new PageImpl<>(searchResultsList);

        when(productRepository.search(query, Pageable.ofSize(pageSize))).thenReturn(searchResultsPage);

        // Act
        ResponseEntity<Iterable<Product>> response = productService.searchProducts(query, pageNumber, pageSize);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(searchResultsList, response.getBody());
    }
    
    
}
