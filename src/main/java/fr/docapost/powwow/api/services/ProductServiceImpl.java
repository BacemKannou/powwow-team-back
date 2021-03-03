package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.dao.CategoryRepository;
import fr.docapost.powwow.api.dao.ProductRepository;
import fr.docapost.powwow.api.entities.Category;
import fr.docapost.powwow.api.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
