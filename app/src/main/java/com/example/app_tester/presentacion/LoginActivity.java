package com.example.app_tester.presentacion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_tester.R;
import com.example.app_tester.negocio.UsuarioNegocio;


public class LoginActivity extends AppCompatActivity {

    private UsuarioNegocio usuarioNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioNegocio = new UsuarioNegocio(this);

        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPassword = findViewById(R.id.editPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            // Verificar si las credenciales son correctas
            boolean isValid = usuarioNegocio.validarUsuario(email, password);
            if (isValid) {
                Intent intent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegistrar.setOnClickListener(v -> {
            Log.d("LoginActivity", "Botón Registrar Usuario clickeado");
            // Lógica para abrir la actividad de registro de usuario
            Intent intent = new Intent(LoginActivity.this, RegistrarUsuarioActivity.class);
            startActivity(intent);
        });
    }
}
