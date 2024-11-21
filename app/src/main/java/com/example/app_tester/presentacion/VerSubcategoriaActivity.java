package com.example.app_tester.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tester.R;
import com.example.app_tester.datos.entities.Producto;
import com.example.app_tester.datos.entities.Subcategoria;
import com.example.app_tester.negocio.ProductoNegocio;
import com.example.app_tester.negocio.SubcategoriaNegocio;
import com.example.app_tester.presentacion.adapter.ProductoAdapter;
import com.example.app_tester.presentacion.adapter.SubcategoriaAdapter;

import java.util.List;

public class VerSubcategoriaActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSubcategorias;
    private SubcategoriaAdapter subcategoriaAdapter;
    private SubcategoriaNegocio subcategoriaNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_subcategorias);

        recyclerViewSubcategorias = findViewById(R.id.recyclerSubcategorias);
        subcategoriaNegocio = new SubcategoriaNegocio(this);

        // Configurar RecyclerView
        recyclerViewSubcategorias.setLayoutManager(new LinearLayoutManager(this));

        // Obtener subcategorías y configurar adaptador
        List<Subcategoria> subcategorias = subcategoriaNegocio.obtenerTodasLasSubcategorias();
        subcategoriaAdapter = new SubcategoriaAdapter(this, subcategorias, subcategoria -> {
            // Ir a la actividad de productos con el ID de la subcategoría seleccionada
            Intent intent = new Intent(VerSubcategoriaActivity.this, ListaProductosActivity.class);
            intent.putExtra("idSubcategoria", subcategoria.getId());
            startActivity(intent);
        });
        recyclerViewSubcategorias.setAdapter(subcategoriaAdapter);
    }

}
