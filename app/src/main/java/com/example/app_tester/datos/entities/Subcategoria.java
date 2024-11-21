package com.example.app_tester.datos.entities;

public class Subcategoria {
    private int id;
    private String nombre;
    private String estado;

    private int id_categoria;

    private String nombre_categoria;

    public Subcategoria(){

    }

    public Subcategoria(int id, String nombre, String estado, int id_categoria) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.id_categoria = id_categoria;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getEstado() {

        return estado;
    }

    public void setEstado(String estado) {

        this.estado = estado;
    }

    public int getId_categoria() {

        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {

        this.id_categoria = id_categoria;
    }

    public void setId(int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombreCategoria() {
        return nombre_categoria;
    }

    public void setNombreCategoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }


    @Override
    public String toString() {
        return nombre;  // Aquí puedes devolver el campo que quieras mostrar en el Spinner
    }
}
