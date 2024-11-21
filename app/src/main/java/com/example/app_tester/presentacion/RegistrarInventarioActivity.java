package com.example.app_tester.presentacion;

import android.database.Cursor;
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
import com.example.app_tester.datos.entities.Usuario;
import com.example.app_tester.negocio.InventarioNegocio;
import com.example.app_tester.negocio.UsuarioNegocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Intent;

public class RegistrarInventarioActivity extends AppCompatActivity {
    private InventarioNegocio inventarioNegocio;
    private UsuarioNegocio usuarioNegocio;

    // Declarar los campos y botones
    private EditText editDescripcion, editUnidad, editCantidad, editFecha;
    private Button btnRegistrar;
    private Spinner spinnerUsuarios;
    private List<Usuario> listaUsuarios;
    private int selectedUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_inventario);

        // Inicializar InventarioNegocio y UsuarioNegocio
        inventarioNegocio = new InventarioNegocio(this);
        usuarioNegocio = new UsuarioNegocio(this);
        // Inicializar los campos de texto y botones
        editDescripcion = findViewById(R.id.editDescripcion);
        editUnidad = findViewById(R.id.editUnidad);
        editCantidad = findViewById(R.id.editCantidad);
        editFecha = findViewById(R.id.editFecha);
        spinnerUsuarios = findViewById(R.id.spinnerUsuarios);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // Establecer la fecha actual por defecto
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaActual = sdf.format(new Date());
        editFecha.setText(fechaActual);

        // Cargar los usuarios en el Spinner
        cargarUsuariosEnSpinner();



        // Evento de click en el botón para registrar inventario
        btnRegistrar.setOnClickListener(v -> {
            try {
                String descripcion = editDescripcion.getText().toString();
                String unidad = editUnidad.getText().toString();
                int cantidad = Integer.parseInt(editCantidad.getText().toString());
                String fecha = editFecha.getText().toString();
                int id_user = selectedUserId;

                long resultado = inventarioNegocio.registrarInventario(descripcion, unidad, cantidad, fecha, id_user);

                if (resultado != -1) {
                    Toast.makeText(RegistrarInventarioActivity.this, "Inventario registrado con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrarInventarioActivity.this, VerTodoInventarioActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistrarInventarioActivity.this, "Error al registrar el inventario", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(RegistrarInventarioActivity.this, "Error en la cantidad ingresada", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(RegistrarInventarioActivity.this, "Ocurrió un error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void cargarUsuariosEnSpinner() {
        listaUsuarios = usuarioNegocio.obtenerTodosLosUsuarios();
        List<String> nombresUsuarios = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            nombresUsuarios.add(usuario.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresUsuarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuarios.setAdapter(adapter);

        spinnerUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUserId = listaUsuarios.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}


