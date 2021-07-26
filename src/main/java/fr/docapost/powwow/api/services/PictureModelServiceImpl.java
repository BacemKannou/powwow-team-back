package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.dao.PictureModelRepository;
import fr.docapost.powwow.api.dao.UserRepository;
import fr.docapost.powwow.api.dto.PictureModelDTO;
import fr.docapost.powwow.api.entities.Auditable;
import fr.docapost.powwow.api.entities.PictureModel;
import fr.docapost.powwow.api.entities.User;
import fr.docapost.powwow.api.security.SecurityInfo;
import fr.docapost.powwow.api.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class PictureModelServiceImpl implements PictureModelService {

    @Autowired
    SecurityInfo securityInfo;

    @Autowired
    PictureModelRepository pictureModelRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void uploadImage(MultipartFile file, Long userId) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        User currentUser= securityInfo.getCurrentUser();
        User user= userRepository.getOne(userId);
        PictureModel img = new PictureModel(file.getOriginalFilename(), file.getContentType(),
                FileUtils.compressBytes(file.getBytes()));
        if(user.getPictureModel()!=null){
            img.setAuditable(new Auditable(currentUser));
        }else{
            img.setAuditable(new Auditable(currentUser));
        }
        user.setPictureModel(img);
        userRepository.save(user);
    }

    @Override
    public PictureModelDTO getImage(Long userId) {
        User user=securityInfo.getCurrentUser();
        final Optional<PictureModel> retrievedImage = pictureModelRepository.findByUserId(userId);
        PictureModelDTO img = new PictureModelDTO(retrievedImage.get().getName(), retrievedImage.get().getType(),
                FileUtils.decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }
}
