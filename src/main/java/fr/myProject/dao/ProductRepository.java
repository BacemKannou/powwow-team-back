package fr.myProject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.myProject.entities.Product;

/**
 * @author Bacem
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	 Optional<Product> findById(Long id);
}
