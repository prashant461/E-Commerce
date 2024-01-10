package com.prashant.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "products")
public class Product {

    @Id
    private int id;

    private String name;
   	private String description;
    private String category;
    private double price;
    
}
