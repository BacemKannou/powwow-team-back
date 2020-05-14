package fr.myProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.myProject.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
