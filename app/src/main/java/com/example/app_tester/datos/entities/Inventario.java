package com.example.app_tester.datos.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inventario {
    private int id;
    private String descripcion;
    private String unidad;
    private int cantidad;

    private Date fecha; // atributo tipo Date
    private int id_user;

    private String nombreUsuario;  // Campo auxiliar para el nombre del usuario

    // Formato para manejar las fechas
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato en que la fecha está almacenada en la DB
    private static final SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato para mostrar la fecha

    // Constructor que recibe un String y lo convierte a Date
    public Inventario(int id, String descripcion, String unidad, int cantidad, String fechaString, int id_user) {
        this.id = id;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.cantidad = cantidad;
        try {
            // Convertir el String de la base de datos a un Date
            this.fecha = dateFormat.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
            this.fecha = null; // Manejo de error
        }
        this.id_user = id_user;
    }

    // Método para obtener la fecha como String en formato dd/MM/yyyy
    public String getFormattedFecha() {
        return fecha != null ? displayFormat.format(fecha) : "Sin fecha";
    }

    // Otros getters y setters (opcional)
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        if (fecha != null) {
            return displayFormat.format(fecha); // Formatea la fecha a String
        } else {
            return ""; // Retorna una cadena vacía si la fecha es null
        }
    }


    public int getId_user() {
        return id_user;
    }

    // Métodos getter y setter para nombreUsuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return descripcion;  // Aquí puedes devolver el campo que quieras mostrar en el Spinner
    }

}
