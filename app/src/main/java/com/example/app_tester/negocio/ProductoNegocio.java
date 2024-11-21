package com.example.app_tester.negocio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.DAO.ProductoDAO;
import com.example.app_tester.datos.entities.Inventario;
import com.example.app_tester.datos.entities.Producto;

import java.util.ArrayList;
import java.util.List;


public class ProductoNegocio {

    private DatabaseHelper dbHelper;

    private ProductoDAO productoDAO;

    public ProductoNegocio(Context context) {

        this.dbHelper = new DatabaseHelper(context);
        productoDAO = new ProductoDAO(context);
    }

    // Método para registrar un producto
    public long registrarProducto(String nombre, String descripcion, double precio, String unidad, int cantidad, String estado, String foto, int id_inventario, int id_subcategoria) {
        // Validaciones de la lógica de negocio
        if (precio <= 0) {
            // No se puede registrar un producto con precio negativo o cero
            return -1; // Código de error
        }

        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long resultado;

        // Verificar si el producto ya existe
        if (productoExistente(nombre, db)) {
            db.close(); // Cerrar la conexión antes de salir
            return -2; // Indicar que ya existe un producto con el mismo nombre

        } else {
            // Crear un ContentValues con los datos del producto
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("descripcion", descripcion);
            values.put("precio", precio);
            values.put("unidad", unidad);
            values.put("cantidad", cantidad);
            values.put("estado", estado);
            values.put("foto", foto);
            values.put("id_inventario", id_inventario);
            values.put("id_subcategoria", id_subcategoria);

            // Insertar el producto en la base de datos
            resultado = db.insert("productos", null, values);

            // Cerrar la conexión a la base de datos
            db.close();
        }

        return resultado; // Retorna el ID del nuevo producto insertado o -1 si hubo un error
    }

    // Método para verificar si el producto ya existe
    private boolean productoExistente(String nombre, SQLiteDatabase db) {
        String query = "SELECT * FROM productos WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    public List<Producto> obtenerProductosPorSubcategoria(int idSubcategoria) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM productos WHERE id_subcategoria = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idSubcategoria)});

        if (cursor.moveToFirst()) {
            do {
                Producto producto = new Producto();
                producto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                producto.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                producto.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                producto.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                producto.setUnidad(cursor.getString(cursor.getColumnIndex("unidad")));
                producto.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                producto.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                producto.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
                producto.setId_inventario(cursor.getInt(cursor.getColumnIndex("id_inventario")));
                producto.setId_subcategoria(cursor.getInt(cursor.getColumnIndex("id_subcategoria")));
                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productos;
    }

}

