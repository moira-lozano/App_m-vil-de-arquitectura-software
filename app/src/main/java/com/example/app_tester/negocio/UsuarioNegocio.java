package com.example.app_tester.negocio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_tester.database.DatabaseHelper;
import com.example.app_tester.datos.DAO.UsuarioDAO;
import com.example.app_tester.datos.entities.Usuario;

import java.util.List;

public class UsuarioNegocio {
    private DatabaseHelper dbHelper;
    private UsuarioDAO usuarioDAO;

    // Constructor que recibe el Context
    public UsuarioNegocio(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.usuarioDAO = new UsuarioDAO(context);  // Pasa el contexto aquí
    }

    // Método para registrar un usuario
    public long registrarUsuario(String nombre, String email, String password, String celular) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("email", email);
        values.put("password", password);
        values.put("celular", celular);

        long resultado = db.insert("usuario", null, values);
        db.close();

        return resultado;
    }

    // Método para validar el usuario al iniciar sesión
    public boolean validarUsuario(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM usuario WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return isValid;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.listarUsuarios();  // Asumiendo que tienes un método listarUsuarios() en UsuarioDAO
    }
}
