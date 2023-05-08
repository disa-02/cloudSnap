package com.gallery.gallery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gallery.gallery.model.Folder;
import com.gallery.gallery.model.Image;
import com.gallery.gallery.repository.ImageRepository;
import com.gallery.gallery.utils.JWTUtil;

@Service
public class ImageServiceImp implements ImageService{

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image save(Image imagen) {
        return imageRepository.save(imagen);
    }

    @Override
    public Optional<Image> get(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public void update(Image imagen) {
       imageRepository.save(imagen); 
    }

    @Override
    public void delete(Integer id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Boolean exist(Integer id) {
        return imageRepository.existsById(id);
    }

    @Override
    public List<Image> findByFolder(Folder folder) {
        return imageRepository.findByFolder(folder);
    }

}
