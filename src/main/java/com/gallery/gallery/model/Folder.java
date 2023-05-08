package com.gallery.gallery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "folder", cascade={CascadeType.REMOVE})
    private List<Image> images;

    public Folder(){}

    public Folder(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setNombre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Carpeta [id=" + id + ", imagenes=" + images + ", nombre=" + name + "]";
    }
}
