package com.example.app_tester.datos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.entities.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ProductoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM producto";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));
                String unidad = cursor.getString(cursor.getColumnIndexOrThrow("unidad"));
                int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"));
                String estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"));
                String foto = cursor.getString(cursor.getColumnIndexOrThrow("foto"));
                int idInventario = cursor.getInt(cursor.getColumnIndexOrThrow("id_inventario"));
                int idSubcategoria = cursor.getInt(cursor.getColumnIndexOrThrow("id_subcategoria"));

                Producto producto = new Producto(id, nombre, descripcion, precio, unidad, cantidad, estado, foto, idInventario, idSubcategoria);
                productos.add(producto);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return productos;
    }

}
