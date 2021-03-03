package fr.docapost.powwow.api.dao;

import fr.docapost.powwow.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
