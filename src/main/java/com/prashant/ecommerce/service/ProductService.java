package com.prashant.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prashant.ecommerce.model.Product;
import com.prashant.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<Product> addProduct(Product product) {
		try {
            Product savedProduct = productRepository.save(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	

	public ResponseEntity<List<Product>> findAllProducts(int pageNumber, int pageSize) {
		// pagination
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Product> productsPage = productRepository.findAll(pageable);
		List<Product> products = productsPage.getContent();
		
		return new ResponseEntity<>(products, HttpStatus.OK) ;
	}
	

	public ResponseEntity<Product> findProduct(int producId) {
		Optional<Product> product = productRepository.findById(producId);
		if(product.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product.get(), HttpStatus.OK);
	}
	

	public ResponseEntity<Product> updateProduct(int producId, Product product) {
		Optional<Product> existingOptional = productRepository.findById(producId);
		
		if(existingOptional.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Product existingProduct = existingOptional.get();
		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setPrice(product.getPrice());
		
		Product updatedBook = productRepository.save(existingProduct);
		
		return new ResponseEntity<>(updatedBook, HttpStatus.OK);
	}

	public ResponseEntity<String> deleteProduct(int producId) {
		Optional<Product> book = productRepository.findById(producId);
		
		if(book.isEmpty()) {
			return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
		}
		
		productRepository.deleteById(producId);
		
		return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
	}
	
	public ResponseEntity<List<Product>> searchProduct(String query) {
		List<Product> products = productRepository.searchProducts(query);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}


//	public ResponseEntity<List<Product>> searchProductByName(String name) {
//		List<Product> products = productRepository.findByNameContaining(name);
//		return new ResponseEntity<> (products, HttpStatus.OK);
//	}
//
//
//	public ResponseEntity<List<Product>> searchProductByContent(String name, String description, String category) {
//		List<Product> products = productRepository.findByNameContainingOrDescriptionContainingOrCategoryContaining(name, description, category);
//		return new ResponseEntity<> (products, HttpStatus.OK);
//	}


}
