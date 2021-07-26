package fr.docapost.powwow.api.controller;


import fr.docapost.powwow.api.dto.PictureModelDTO;
import fr.docapost.powwow.api.services.PictureModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/Picture")
@RestController
public class PictureController {

    @Autowired
    PictureModelService pictureModelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file, @RequestParam Long userId) throws IOException {
        pictureModelService.uploadImage(file, userId);
        return new ResponseEntity<String>("Your File is uploaded" ,HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<PictureModelDTO> getImage(@RequestParam("userId") Long userId) throws IOException {
        return new ResponseEntity<PictureModelDTO>(pictureModelService.getImage(userId), HttpStatus.OK);
    }
}
