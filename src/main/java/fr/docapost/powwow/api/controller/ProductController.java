package fr.docapost.powwow.api.controller;

import fr.docapost.powwow.api.entities.Product;
import fr.docapost.powwow.api.services.AccountService;
import fr.docapost.powwow.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
