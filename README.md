# 🛍️ Spring Boot E-Commerce Platform 🛍️

## 🌐 Overview
Welcome to the Spring Boot E-Commerce Platform! This project provides a RESTful API for managing products in an e-commerce setting. It includes basic security using Spring Security and uses Elasticsearch for product data storage.

## 🏗️ Project Structure

### 🎮 Controller
- **ProductController:** Manages API endpoints related to product operations.

### 📋 Model
- **Product:** Entity class representing a product with attributes (id, name, description, category, price).

### ⚙️ Service
- **ProductService:** Service layer containing business logic for product operations.

### 📁 Repository
- **ProductRepository:** Elasticsearch repository for interacting with the database.

### 🔒 Security
- **WebSecurityConfig:** Spring Security configuration class with basic authentication.

### 📝 Search
- **ProductSearchService:** Service for handling product search queries.

### 🧪 Test
- **ProductControllerTest:** JUnit and Mockito-based tests for the ProductController.

## 🚀 Features
  ### Functionalities Supported
  - **Add a new product (POST):** `/api/product/add-product`
  - **Get all products with pagination (GET):** `/api/product/all-products`
  - **Get a product by its ID (GET):** `/api/product/{productId}`
  - **Update a product (PUT):** `/api/product/update/{productId}`
  - **Delete a product (DELETE):** `/api/product/delete/{productId}`
  - **Search products by content (GET):** `/api/product/search?query=sample`

  ### 🚦 HTTP Status Codes and Error Messages
  - The API responds with appropriate HTTP status codes and error messages for different scenarios.

  ### 🔐 Security
  - Basic security is implemented using Spring Security with in-memory authentication.

  ### 🗄️ Database
  - Product data is persisted in Elasticsearch using Spring Data Elasticsearch.

  ### 🔍 Search
  - Products can be searched based on their name, description, and category.

  ### 🧪 Unit Tests
  - Unit tests for API endpoints are implemented using JUnit and Mockito.

# 🚀 Getting Started 🚀

### Prerequisites
- Clone the project or download the ZIP file.
- Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).

### Configuration ⚙️
- Configure your Elasticsearch connection in `application.properties`.
- Default Elasticsearch URL: `localhost:9200`

### Run the Application ▶️
- Build and run the application.

## 📖 API Documentation 📖

### Default Credentials 🔐
- The application is secured using Spring Security with basic authentication.
- Use the following default credentials for testing purposes:
  - **Username:** `user`
  - **Password:** `password`

### Swagger API Documentation
- Swagger has been integrated to provide interactive API documentation and testing capabilities.
- Access the Swagger UI at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Explore and test the available API endpoints using Swagger.
  
### CRUD Operations

#### Add a new product (POST) ➕
- **Endpoint:** `/api/product/add-product`
- **Request Body:** Product details (JSON)
- **Payload:**
  ```json
  {
      "id" : 123,
      "name": "Sample Product",
      "description": "A sample product description",
      "category": "Electronics",
      "price": 99.99
  }
  ```
- **Response:** Product details with HTTP status.

#### Get all products with Pagination (GET)
- **Endpoint:** `/api/product/all-products`
- **Parameters:**
  - `pageSize` (optional): Number of products per page (default: 5)
  - `pageNumber` (optional): Page number (default: 0)
- **Response:** Paginated list of products with HTTP status.

#### Get a product by its ID (GET)
- **Endpoint:** `/api/product/{productId}`
- **Path Variable:** `productId`
- **Response:** Product details with HTTP status.

#### Update a product (PUT) 🔄
- **Endpoint:** `/api/product/update/{productId}`
- **Path Variable:** `productId`
- **Request Body:** Updated product details (JSON)
- **Payload:**
  ```json
  {
      "name": "Updated Product Name",
      "description": "Updated product description",
      "category": "Updated Category",
      "price": 149.99
  }
  ```
- **Response:** Updated product details with HTTP status.

#### Delete a product (DELETE) ❌
- **Endpoint:** `/api/product/delete/{productId}`
- **Path Variable:** `productId`
- **Response:** Success message with HTTP status.

#### Search products by content (GET)
- **Endpoint:** `/api/product/search?query=sample`
- **Query Parameter:** `query`
- **Response:** List of products matching the search query with HTTP status.

## Contributing 🤝
Feel free to contribute, suggest improvements, or report issues! 🚀

## Happy Shopping! 🛍️
