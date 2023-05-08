package com.gallery.gallery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gallery.gallery.model.Folder;

@Service
public interface FolderService {
    public Folder save(Folder carpeta);
    public Optional<Folder> get(Integer id);
    public void update(Folder carpeta);
    public void delete(Integer id);
    public List<Folder> findAll();
    public Boolean exist(Integer id);
    
    
    
}
