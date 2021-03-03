package fr.docapost.powwow.api.dao;

import fr.docapost.powwow.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bacem
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	 Optional<Product> findById(Long id);
}
