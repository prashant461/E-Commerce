package com.prashant.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    public void testSearchProduct() {
        // Mocking the behavior of the repository's searchProducts method
        when(productRepository.searchProducts("query")).thenReturn(Arrays.asList(
                new Product(1, "Product1", "Description1", "Category1", 100),
                new Product(2, "Product2", "Description2", "Category2", 150)
        ));

        // Calling the searchProduct method
        ResponseEntity<List<Product>> responseEntity = productService.searchProduct("query");

        // Verifying the results
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Product> products = responseEntity.getBody();
        assertEquals(2, products.size()); 
    }
    
}
