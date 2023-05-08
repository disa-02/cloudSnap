package com.gallery.gallery.controller;

import java.util.List;

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

import com.gallery.gallery.model.Folder;
import com.gallery.gallery.service.FolderService;
import com.gallery.gallery.utils.JWTUtil;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin
public class FolderController {
    
    @Autowired
    FolderService carpetaService;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam String nombre,@RequestHeader(value="Authorization") String token ){
        String usuarioId = jwtUtil.getKey(token); //si es null esta mal autenticado
        if(usuarioId == null){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        Folder carpeta = new Folder(nombre);
        carpetaService.save(carpeta);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<?> upload(@RequestParam String nombre, @PathVariable int id,@RequestHeader(value="Authorization") String token ){
        String usuarioId = jwtUtil.getKey(token); //si es null esta mal autenticado
        if(usuarioId == null){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        if(!carpetaService.exist(id)){
            return new ResponseEntity<>("The folder is not exist", HttpStatus.NOT_FOUND);
        }
        Folder carpeta = carpetaService.get(id).get();
        carpeta.setNombre(nombre);
        carpetaService.update(carpeta);
        return new ResponseEntity<>(carpeta,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader(value="Authorization") String token ){
        String usuarioId = jwtUtil.getKey(token); //si es null esta mal autenticado
        if(usuarioId == null){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        if(!carpetaService.exist(id)){
            return new ResponseEntity<>("The folder is not exist", HttpStatus.NOT_FOUND);
        }
        carpetaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Folder>> get(@RequestHeader(value="Authorization") String token ){
        String usuarioId = jwtUtil.getKey(token); //si es null esta mal autenticado
        if(usuarioId == null){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        List<Folder> list = carpetaService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
