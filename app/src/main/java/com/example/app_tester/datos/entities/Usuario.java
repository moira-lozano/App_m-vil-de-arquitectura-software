package com.example.app_tester.datos.entities;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String password;
    private String celular;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String password, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
