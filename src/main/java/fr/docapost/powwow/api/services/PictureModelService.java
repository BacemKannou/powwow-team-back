package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.dto.PictureModelDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureModelService {
    void uploadImage(MultipartFile file, Long userId) throws IOException;
    PictureModelDTO getImage(Long userId);

}
