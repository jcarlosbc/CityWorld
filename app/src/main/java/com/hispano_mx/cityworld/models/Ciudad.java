package com.hispano_mx.cityworld.models;

import com.hispano_mx.cityworld.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jcblas on 3/9/2017.
 */

public class Ciudad extends RealmObject {
    @PrimaryKey
    private int id;
    private String nombre;
    private String descripcion;
    private String url_img;
    private float estrellas;

    public Ciudad() {
    }

    public Ciudad(String nombre, String descripcion, String url_img, float estrellas) {
        this.id= MyApplication.CiudadID.incrementAndGet();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url_img = url_img;
        this.estrellas = estrellas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public float getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }
}
