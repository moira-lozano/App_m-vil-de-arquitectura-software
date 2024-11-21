package com.example.app_tester.datos.entities;


public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String unidad;
    private int cantidad;
    private String estado;

    private  String foto;

    // Llaves foráneas
    private int id_inventario;
    private int id_subcategoria;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(int id, String nombre, String descripcion, double precio, String unidad, int cantidad, String estado, String foto, int id_inventario, int id_subcategoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.estado = estado;
        this.foto = foto;
        this.id_inventario = id_inventario;
        this.id_subcategoria = id_subcategoria;
    }

    // Getters y Setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public String getFoto() {
        return foto;
    }

    public int getIdInventario() {
        return id_inventario;
    }

    public int getIdSubcategoria() {
        return id_subcategoria;
    }
}
