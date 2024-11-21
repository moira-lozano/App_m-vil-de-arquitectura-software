package com.example.app_tester.datos.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.entities.Categoria;
import com.example.app_tester.datos.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Constructor que recibe el contexto
    public CategoriaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    public List<Categoria> listarCategoria(){
        List<Categoria> categorias = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nombre, estado FROM categoria", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String estado = cursor.getString(cursor.getColumnIndex("estado"));
                categorias.add(new Categoria(id, nombre, estado));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return categorias;
    }
}
