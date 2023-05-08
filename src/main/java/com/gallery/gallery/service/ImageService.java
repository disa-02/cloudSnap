package com.gallery.gallery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gallery.gallery.model.Folder;
import com.gallery.gallery.model.Image;

@Service
public interface ImageService{
    public Image save(Image imagen);
    public Optional<Image> get(Integer id);
    public void update(Image imagen);
    public void delete(Integer id);
    public List<Image> findAll();
    public Boolean exist(Integer id);
    public List<Image> findByFolder(Folder folder);


  
}
