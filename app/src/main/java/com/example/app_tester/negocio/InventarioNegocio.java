package com.example.app_tester.negocio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.DAO.InventarioDAO;
import com.example.app_tester.datos.entities.Inventario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InventarioNegocio {
    private DatabaseHelper dbHelper;
    private InventarioDAO inventarioDAO;

    public InventarioNegocio(Context context) {

        this.dbHelper = new DatabaseHelper(context);
        inventarioDAO = new InventarioDAO(context);
    }

    //Funcion para registrar inventario
    public long registrarInventario(String descripcion, String unidad, int cantidad, String fechaString, int id_user) {
        // Validaciones de la lógica de negocio
        if (cantidad <= 0) {
            return -1; // Código de error
        }

        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Error en el formato de fecha
        }

        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long resultado;

        // Verificar si el producto ya existe
        if (inventarioExistente(descripcion, db)) {
            db.close(); // Cerrar la conexión antes de salir
            return -2; // Indicar que ya existe un producto con el mismo nombre

        } else {
            // Crear un ContentValues con los datos del producto
            ContentValues values = new ContentValues();
            values.put("descripcion", descripcion);
            values.put("unidad", unidad);
            values.put("cantidad", cantidad);

            // Formatear la fecha a String en formato YYYY-MM-DD
            String fechaParaDB = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(fecha);
            values.put("fecha", fechaParaDB);
            values.put("id_user", id_user);

            // Insertar el inventario en la base de datos
            resultado = db.insert("inventario", null, values);

            // Cerrar la conexión a la base de datos
            db.close();
        }

        return resultado; // Retorna el ID del nuevo producto insertado o -1 si hubo un error
    }

    // Método para verificar si el producto ya existe
    private boolean inventarioExistente(String nombre, SQLiteDatabase db) {
        String query = "SELECT * FROM productos WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    // Función para editar inventario
    public int editarInventario(int id, String descripcion, String unidad, int cantidad, int id_user, String fecha) {
        // Validaciones de la lógica de negocio
        if (cantidad <= 0) {
            return -1; // Código de error
        }

        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descripcion", descripcion);
        values.put("unidad", unidad);
        values.put("cantidad", cantidad);
        values.put("id_user", id_user);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = dateFormat.format(fecha);
        values.put("fecha", fechaString);

        // Actualizar el inventario en la base de datos
        int filasAfectadas = db.update("inventario", values, "id = ?", new String[]{String.valueOf(id)});

        // Cerrar la conexión
        db.close();

        return filasAfectadas; // Retorna el número de filas afectadas
    }

    // Función para ver un inventario específico
    public Cursor verInventario(int id) {
        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Realizar la consulta para obtener el inventario
        String query = "SELECT * FROM inventario WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        // El cursor se devuelve para que la actividad que llame esta función maneje los datos
        return cursor;
    }

    // Función para ver todos los inventarios (HISTORIAL)
    public Cursor verTodosInventarios() {
        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Realizar la consulta para obtener todos los inventarios
        String query = "SELECT * FROM inventario";
        return db.rawQuery(query, null);
    }

    // Función para eliminar inventario
    public int eliminarInventario(int id) {
        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Eliminar el inventario por su ID
        int filasEliminadas = db.delete("inventario", "id = ?", new String[]{String.valueOf(id)});

        // Cerrar la conexión
        db.close();

        return filasEliminadas; // Retorna el número de filas eliminadas
    }

    // Método para obtener todos los inventarios
    public List<Inventario> obtenerTodosLosInventarios() {
        return inventarioDAO.obtenerTodosLosInventarios();
    }

}
