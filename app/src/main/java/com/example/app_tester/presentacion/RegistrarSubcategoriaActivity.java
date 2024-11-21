package com.example.app_tester.presentacion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tester.R;
import com.example.app_tester.datos.entities.Categoria;
import com.example.app_tester.negocio.CategoriaNegocio;
import com.example.app_tester.negocio.SubcategoriaNegocio;

import java.util.ArrayList;
import java.util.List;

public class RegistrarSubcategoriaActivity extends AppCompatActivity {

    private CategoriaNegocio categoriaNegocio;
    private SubcategoriaNegocio subcategoriaNegocio;
    private Button btnRegistrar;
    private Spinner spinnerCategorias;
    private EditText editNombreSubcategoria;

    private EditText editEstadoSubcategoria;
    private List<Categoria> listaCategorias;
    private int selectedCategoriaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_subcategoria); // Asegúrate de que el layout exista

        // Inicializar las instancias de negocio
        categoriaNegocio = new CategoriaNegocio(this);
        subcategoriaNegocio = new SubcategoriaNegocio(this);

        // Inicializar las vistas
        btnRegistrar = findViewById(R.id.btnRegistrar);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        editNombreSubcategoria = findViewById(R.id.editNombreSubcategoria);
        editEstadoSubcategoria = findViewById(R.id.editEstadoSubcategoria);

        // Cargar las categorías en el spinner
        cargarCategoriasEnSpinner();

        // Evento para registrar subcategorías
        btnRegistrar.setOnClickListener(v -> registrarSubcategoria());
    }

    private void cargarCategoriasEnSpinner() {
        listaCategorias = categoriaNegocio.obtenerCategorias(); // Implementa este método para obtener las categorías
        List<String> nombreCategorias = new ArrayList<>();
        for (Categoria categoria : listaCategorias) {
            nombreCategorias.add(categoria.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombreCategorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(adapter);

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoriaId = listaCategorias.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void registrarSubcategoria() {
        String nombreSubcategoria = editNombreSubcategoria.getText().toString();
        String estadoSubcategoria = editEstadoSubcategoria.getText().toString();

        if (nombreSubcategoria.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un nombre para la subcategoría", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registrar la subcategoría usando SubcategoriaNegocio
        long resultado = subcategoriaNegocio.registrarSubategoria(nombreSubcategoria,estadoSubcategoria);

        if (resultado != -1) {
            Toast.makeText(this, "Subcategoría registrada con éxito", Toast.LENGTH_SHORT).show();
            finish(); // Cerrar la actividad después de registrar
        } else {
            Toast.makeText(this, "Error al registrar la subcategoría", Toast.LENGTH_SHORT).show();
        }
    }
}
