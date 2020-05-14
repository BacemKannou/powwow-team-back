package fr.myProject.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.myProject.dao.CategoryRepository;
import fr.myProject.entities.Category;

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
