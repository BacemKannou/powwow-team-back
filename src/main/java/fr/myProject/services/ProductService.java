package fr.myProject.services;

import java.util.List;

import fr.myProject.entities.Product;


public interface ProductService {
	List <Product>getAllProducts();
	
	Product saveProduct(String productName) ;
	
	Product saveProductWithCategory(Long categoryId, String productName);
	
	Product findProductById(Long id);
}
