package fr.docapost.powwow.api.dao;

import fr.docapost.powwow.api.entities.PictureModel;
import fr.docapost.powwow.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PictureModelRepository extends JpaRepository<PictureModel, Long> {
    Optional<PictureModel> findByName(String name);
    Optional<PictureModel> findByUserId(Long userId);
}
