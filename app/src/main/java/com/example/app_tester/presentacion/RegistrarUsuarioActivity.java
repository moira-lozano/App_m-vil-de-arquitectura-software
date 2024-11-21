package com.example.app_tester.presentacion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tester.R;
import com.example.app_tester.negocio.UsuarioNegocio;


public class RegistrarUsuarioActivity extends AppCompatActivity {
    private UsuarioNegocio usuarioNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        usuarioNegocio = new UsuarioNegocio(this);

        EditText editNombre = findViewById(R.id.editNombre);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPassword = findViewById(R.id.editPassword);
        EditText editCelular = findViewById(R.id.editCelular);
        Button btnGuardarUsuario = findViewById(R.id.btnGuardarUsuario);

        btnGuardarUsuario.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString();
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();
            String celular = editCelular.getText().toString();

            // Registrar el nuevo usuario en la base de datos
            long resultado = usuarioNegocio.registrarUsuario(nombre, email, password, celular);
            if (resultado != -1) {
                Toast.makeText(RegistrarUsuarioActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                finish(); // Volver a la pantalla de login
            } else {
                Toast.makeText(RegistrarUsuarioActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

