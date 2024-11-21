package com.example.app_tester.negocio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.DAO.CategoriaDAO;
import com.example.app_tester.datos.entities.Categoria;

import java.util.List;

public class CategoriaNegocio {
    private DatabaseHelper dbHelper;
    private CategoriaDAO categoriaDAO;

    public CategoriaNegocio(Context context){

        this.dbHelper = new DatabaseHelper(context);
        this.categoriaDAO = new CategoriaDAO(context);
    }

    //Funcion para registar categoria
    public long registrarCategoria(String nombre, String estado){

        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long resultado;

        if(categoriaExistente(nombre, db)){
            db.close();
            return -2;
        } else {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("estado", estado);

            resultado = db.insert("categoria", null, values);
            db.close();
        }
        return resultado;
    }

    private boolean categoriaExistente(String nombre, SQLiteDatabase db) {
        String query = "SELECT * FROM categoria WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    /*public void llenarCategorias() {
        registrarCategoria("Lácteos", "Activo");
        registrarCategoria("Carnes", "Activo");
        registrarCategoria("Verduras", "Activo");
        registrarCategoria("Frutas", "Activo");
        registrarCategoria("Limpieza", "Inactivo");

    }*/

    public List<Categoria> obtenerCategorias() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return categoriaDAO.listarCategoria();
    }


}
