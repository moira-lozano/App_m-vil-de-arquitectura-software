package com.example.app_tester.datos.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.entities.Subcategoria;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriaDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public SubcategoriaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public List<Subcategoria> obtenerTodasLasSubcategorias() {
        List<Subcategoria> subcategorias = new ArrayList<>();
        String query = "SELECT * FROM subcategoria";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String estado = cursor.getString(cursor.getColumnIndexOrThrow("estado"));
                int id_categoria = cursor.getInt(cursor.getColumnIndexOrThrow("id_categoria"));
                Subcategoria subcategoria = new Subcategoria(id, nombre, estado, id_categoria);
                subcategorias.add(subcategoria);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return subcategorias;
    }
}

