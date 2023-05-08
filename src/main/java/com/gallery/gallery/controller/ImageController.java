package com.gallery.gallery.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gallery.gallery.model.Folder;
import com.gallery.gallery.model.Image;
import com.gallery.gallery.service.CloudinaryService;
import com.gallery.gallery.service.FolderService;
import com.gallery.gallery.service.ImageService;
import com.gallery.gallery.utils.JWTUtil;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    FolderService folderService;

    @Autowired 
    CloudinaryService cloudinaryService;

    @Autowired
    JWTUtil jwtUtil;
    
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam(required = false) String folderId, @RequestParam MultipartFile multipartFile,@RequestHeader(value="Authorization") String token) throws IOException{
        String usuarioId = jwtUtil.getKey(token); //si es null esta mal autenticado
        if(usuarioId == null){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        //folder exist??
        int id = Integer.parseInt(folderId);
        Optional<Folder> folderOpt = folderService.get(id);
        if(!folderOpt.isPresent()){
            return new ResponseEntity<>("The folder does not exist", HttpStatus.NOT_FOUND);
        }
        Folder folder = folderOpt.get();

        //connect with cloudinary
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null){ //is it an image??
            return new ResponseEntity("Image does not valid", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);

        //save in the bd
        Image img = new Image(
        (String)result.get("original_filename"),
                    (String)result.get("url"),
                    (String)result.get("public_id"));

        img.setFolder(folder);
        imageService.save(img);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/list/{idCarpeta}")
    public ResponseEntity<List<Image>> listByFolder(@PathVariable int idCarpeta,@RequestHeader(value="Authorization") String token){
        String usuarioId = jwtUtil.getKey(token); //si es null esta mal autenticado
        if(usuarioId == null){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        if (!folderService.exist(idCarpeta)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Folder folder = folderService.get(idCarpeta).get();
        List<Image> images = imageService.findByFolder(folder);
        return new ResponseEntity<>(images,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader(value="Authorization") String token) throws IOException{
        String usuarioId = jwtUtil.getKey(token); //si es null esta mal autenticado
        if(usuarioId == null){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        if (!imageService.exist(id)){
            return new ResponseEntity<>("The image does not exist", HttpStatus.NOT_FOUND);
        }
        //Delete of cloudinary
        Image image = imageService.get(id).get();// obtengo la imagen de la base de datos para luego obtener el imagenId de cloudinary
        cloudinaryService.delete(image.getPublicId());

        imageService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
    
}
