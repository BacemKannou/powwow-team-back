package fr.myProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.myProject.entities.Product;
import fr.myProject.services.AccountService;
import fr.myProject.services.ProductService;


@RestController
public class ProductController {

	@Autowired
	AccountService accountService;

	@Autowired
	ProductService productService;

	@GetMapping("/Products")
	public List<Product> getAllProducts() {	
		return productService.getAllProducts();
	}

	@GetMapping("/Products/{id}")
	Product getProductById(@PathVariable Long id) {	
		return productService.findProductById(id);

	}






}
