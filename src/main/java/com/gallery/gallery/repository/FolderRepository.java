package com.gallery.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gallery.gallery.model.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Integer>{
    
    
}
