package com.prashant.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.ecommerce.model.Product;
import com.prashant.ecommerce.service.ProductService;



@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// adding new product in database
	@PostMapping("/add-product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	// getting all products from database
	// take params for pagination as page size and page number
	
	@GetMapping("/all-products")
	public ResponseEntity<List<Product>> findAllProducts(
	        @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
	        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber
	) {
	    return productService.findAllProducts(pageNumber, pageSize);
	}

	
	//getting product with specific productId
	@GetMapping("/{productId}")
	public ResponseEntity<Product> findBook(@PathVariable int productId) {
		return productService.findProduct(productId);
	}
	
	//updating record in database for specific productId
	@PutMapping("update/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product product) {
		return productService.updateProduct(productId, product);
		
	}
	
	// delete by specific id
	@DeleteMapping("delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
		return productService.deleteProduct(productId);
	}
	
	// search by content
	@GetMapping("search")
	public ResponseEntity<List<Product>> searchProducts(
			@RequestParam(name = "query", required = true) String query
			) {
		
		return productService.searchProduct(query);
	}
	
	
//	//search by name
//	@GetMapping("search-by-name/{name}")
//	public ResponseEntity<List<Product>> searchProductByName(@PathVariable String name) {
//		return productService.searchProductByName(name);
//		
//	}
//	
//	// search by content of product
//	@GetMapping("search-by-content")
//	public ResponseEntity<List<Product>> searchProductByContent(
//			@RequestParam(name = "name", required = true ) String name,
//			@RequestParam(name = "description", required = false) String description,
//			@RequestParam(name = "category", required = false) String category
//			) {
//		return productService.searchProductByContent(name, description, category);
//	}
}
