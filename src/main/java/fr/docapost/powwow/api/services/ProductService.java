package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.entities.Product;

import java.util.List;


public interface ProductService {
	List <Product>getAllProducts();
	
	Product saveProduct(String productName) ;
	
	Product saveProductWithCategory(Long categoryId, String productName);
	
	Product findProductById(Long id);
}
