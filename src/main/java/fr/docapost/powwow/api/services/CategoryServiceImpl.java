package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.dao.CategoryRepository;
import fr.docapost.powwow.api.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getCategories() {
		return categoryRepository.findAll();
}

	@Override
	public Category saveCategory(String categoryName) {
		Category category = new Category();
		category.setName(categoryName);
		
		return category;
	}
	
	

}
