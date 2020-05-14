package fr.myProject.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.myProject.dao.CategoryRepository;
import fr.myProject.dao.ProductRepository;
import fr.myProject.entities.Category;
import fr.myProject.entities.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List <Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product saveProduct(String productName) {
		Product product= new Product();
		product.setProductName(productName);
		return productRepository.save(product);
	}
	
	public Product saveProductWithCategory(Long categoryId, String productName) {
		Optional<Category> category= categoryRepository.findById(categoryId);
		Product product= new Product();
		product.setProductName(productName);
		category.get().addProduct(product);
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) {
		return productRepository.findById(id).get();
	}

}
