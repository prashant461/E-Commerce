package com.prashant.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prashant.ecommerce.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	// JPQL query for searching products
	
	@Query("SELECT p FROM Product p WHERE "
	    + "p.name LIKE CONCAT('%', :query, '%') OR "
	    + "p.description LIKE CONCAT('%', :query, '%') OR "
	    + "p.category LIKE CONCAT('%', :query, '%')")
	List<Product> searchProducts(String query);


//	List<Product> findByNameContaining(String name);
//
//	List<Product> findByNameContainingOrDescriptionContainingOrCategoryContaining(String name, String description, String category);

}
