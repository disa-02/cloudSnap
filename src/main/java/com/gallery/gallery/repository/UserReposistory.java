package com.gallery.gallery.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gallery.gallery.model.User;

@Repository
public interface UserReposistory extends JpaRepository<User,Integer>{
    public Optional<User> findByName(String name);
    
}
