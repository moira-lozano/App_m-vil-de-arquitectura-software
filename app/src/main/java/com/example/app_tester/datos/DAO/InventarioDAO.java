package com.example.app_tester.datos.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.entities.Inventario;

import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public InventarioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    // Método para obtener todos los inventarios
    public List<Inventario> obtenerTodosLosInventarios() {
        List<Inventario> inventarios = new ArrayList<>();

        String query = "SELECT inventario.id, inventario.descripcion, inventario.unidad, " +
                "inventario.cantidad, inventario.fecha, inventario.id_user, usuario.nombre AS nombre_usuario " +
                "FROM inventario " +
                "JOIN usuario ON inventario.id_user = usuario.id";

        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                String unidad = cursor.getString(cursor.getColumnIndexOrThrow("unidad"));
                int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                int id_user = cursor.getInt(cursor.getColumnIndexOrThrow("id_user"));
                String nombreUsuario = cursor.getString(cursor.getColumnIndexOrThrow("nombre_usuario"));

                // Crear el objeto Inventario con el id_user como entero
                Inventario inventario = new Inventario(id, descripcion, unidad, cantidad, fecha, id_user);

                // Aquí puedes agregar el nombre del usuario a la lista que alimentará el RecyclerView
                inventario.setNombreUsuario(nombreUsuario);  // Método que puedes agregar para este propósito
                inventarios.add(inventario);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return inventarios;
    }



}

