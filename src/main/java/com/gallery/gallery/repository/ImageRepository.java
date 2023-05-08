package com.gallery.gallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gallery.gallery.model.Folder;
import com.gallery.gallery.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer>{
  List<Image> findByFolder(Folder folder); 
}
