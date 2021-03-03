package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.entities.Category;

import java.util.List;

public interface CategoryService {

	List <Category>getCategories();
	Category saveCategory(String categoryName) ;
}
