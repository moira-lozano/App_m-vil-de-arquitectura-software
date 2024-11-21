package com.example.app_tester.datos.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;  // Suponiendo que tienes un helper para manejar la base de datos

    // Constructor que recibe el contexto
    public UsuarioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getReadableDatabase();
    }

    // Método para listar usuarios desde la base de datos
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nombre, email, password, celular FROM usuario", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                String celular = cursor.getString(cursor.getColumnIndex("celular"));
                usuarios.add(new Usuario(id, nombre, email, password, celular));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return usuarios;
    }

}
