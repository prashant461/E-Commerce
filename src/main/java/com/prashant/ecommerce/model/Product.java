package com.prashant.ecommerce.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
//	name, description, category, and price.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Product_Id")
	private int productId;
	
	@NotBlank(message = "Name cannot be blank")
	@Column(name="Name")
	private String name;
	
	@Column(name="Description")
	private String description;
	
	@NotBlank(message = "category name cannot be blank")
	@Column(name="Category")
	private String category;
	
	@Min(value = 10,message = "Price must be a non-negative value and greater than 10")
	@Column(name="Price")
    private int price; 
    
}
