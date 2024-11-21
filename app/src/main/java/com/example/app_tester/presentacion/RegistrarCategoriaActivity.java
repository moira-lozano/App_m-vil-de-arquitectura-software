package com.example.app_tester.presentacion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tester.R;
import com.example.app_tester.negocio.CategoriaNegocio;

public class RegistrarCategoriaActivity extends AppCompatActivity {

    private CategoriaNegocio categoriaNegocio;
    private EditText editNombreCategoria, editEstadoCategoria;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_categoria);  // Asegúrate de que este sea el layout correcto

        // Inicializa CategoriaNegocio
        categoriaNegocio = new CategoriaNegocio(this);  // Aquí inicializas el objeto

        // Inicializar vistas
        editNombreCategoria = findViewById(R.id.editNombreCategoria);
        editEstadoCategoria = findViewById(R.id.editEstadoCategoria);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // Configurar botón de registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCategoria();
            }
        });
    }

    private void registrarCategoria() {
        String nombre = editNombreCategoria.getText().toString();
        String estado = editEstadoCategoria.getText().toString();

        if (nombre.isEmpty() || estado.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        long resultado = categoriaNegocio.registrarCategoria(nombre, estado);  // Aquí se llama a registrarCategoria

        if (resultado != -1) {
            Toast.makeText(this, "Categoría registrada con éxito", Toast.LENGTH_SHORT).show();
            finish();  // Cierra la actividad después de registrar
        } else if (resultado == -2) {
            Toast.makeText(this, "La categoría ya existe", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al registrar la categoría", Toast.LENGTH_SHORT).show();
        }
    }
}
