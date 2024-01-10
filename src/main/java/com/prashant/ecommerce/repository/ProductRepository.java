package com.prashant.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.prashant.ecommerce.model.Product;


public interface ProductRepository extends ElasticsearchRepository<Product,Integer> {
	
	@Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name^3\", \"description\", \"category\"]}}")
    Page<Product> search(String query, Pageable pageable);
}
