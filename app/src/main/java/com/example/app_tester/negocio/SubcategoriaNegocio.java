package com.example.app_tester.negocio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.entities.Subcategoria;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriaNegocio {
    private DatabaseHelper dbHelper;

    public SubcategoriaNegocio(Context context){
        this.dbHelper = new DatabaseHelper(context);
    }

    //Funcion para registar SUBcategoria
    public long registrarSubategoria(String nombre, String estado){

        // Abrir la conexión a la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long resultado;

        if(subcategoriaExistente(nombre, db)){
            db.close();
            return -2;
        } else {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("estado", estado);

            resultado = db.insert("subcategoria", null, values);
            db.close();
        }
        return resultado;
    }

    private boolean subcategoriaExistente(String nombre, SQLiteDatabase db) {
        String query = "SELECT * FROM subcategoria WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nombre});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    // Método para obtener subcategorías si es necesario
    public List<Subcategoria> obtenerTodasLasSubcategorias() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Subcategoria> subcategorias = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM subcategoria", null);
        if (cursor.moveToFirst()) {
            do {
                Subcategoria subcategoria = new Subcategoria();
                subcategoria.setId(cursor.getInt(cursor.getColumnIndex("id")));
                subcategoria.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                subcategoria.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                subcategorias.add(subcategoria);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return subcategorias;
    }

}
