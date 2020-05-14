package fr.myProject.services;

import java.util.List;

import fr.myProject.entities.Category;

public interface CategoryService {

	List <Category>getCategories();
	Category saveCategory(String categoryName) ;
}
