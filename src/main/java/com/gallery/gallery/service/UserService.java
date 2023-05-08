package com.gallery.gallery.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gallery.gallery.model.User;
import com.gallery.gallery.repository.UserReposistory;

@Service
public class UserService {
    @Autowired
    UserReposistory userReposistory;

    @PersistenceContext
    EntityManager entityManager;



    public User getUserByCredentials(User user) {
        System.out.println(user.getName());
        String query = "FROM User WHERE name = :name AND password = :password"; //va el nombre de la clase, NO EL DE LA TABLA
        List<User> lista = entityManager.createQuery(query).setParameter("name", user.getName()).setParameter("password", user.getPassword()).getResultList(); //Devuelve una lista con todos los usuarios que cumplen
        if (lista.isEmpty())
            return null;

        return lista.get(0);
        
       
    }
    
}
