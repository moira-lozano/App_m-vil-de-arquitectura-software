package com.example.app_tester.presentacion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tester.R;
import com.example.app_tester.datos.entities.Inventario;
import com.example.app_tester.negocio.InventarioNegocio;
import com.example.app_tester.presentacion.adapter.InventarioAdapter;

import java.util.List;

public class VerTodoInventarioActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private InventarioAdapter inventarioAdapter;
    private InventarioNegocio inventarioNegocio;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ver_todos_inventarios);

            // Inicializa el RecyclerView
            recyclerView = findViewById(R.id.recyclerViewInventarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Layout para lista vertical

            // Inicializa la capa de negocio
            inventarioNegocio = new InventarioNegocio(this);

            // Obtiene los inventarios desde la base de datos
            List<Inventario> inventarios = inventarioNegocio.obtenerTodosLosInventarios();

            // Asigna el Adapter al RecyclerView
            inventarioAdapter = new InventarioAdapter(inventarios);
            recyclerView.setAdapter(inventarioAdapter);
        }
}
