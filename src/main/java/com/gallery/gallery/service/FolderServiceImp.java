package com.gallery.gallery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gallery.gallery.model.Folder;
import com.gallery.gallery.repository.FolderRepository;

@Service
public class FolderServiceImp implements FolderService{

    @Autowired
    FolderRepository carpetaRepository;

    @Override
    public Folder save(Folder carpeta) {
        return carpetaRepository.save(carpeta);
    }

    @Override
    public Optional<Folder> get(Integer id) {
        return carpetaRepository.findById(id); 
    }

    @Override
    public void update(Folder carpeta) {
       carpetaRepository.save(carpeta); 
    }

    @Override
    public void delete(Integer id) {
       carpetaRepository.deleteById(id); 
    }

    @Override
    public List<Folder> findAll() {
        return carpetaRepository.findAll();
    }

    @Override
    public Boolean exist(Integer id) {
        return carpetaRepository.existsById(id);
    }
    
}
