package com.example.app_tester.presentacion;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tester.R;
import com.example.app_tester.datos.entities.Producto;
import com.example.app_tester.negocio.ProductoNegocio;
import com.example.app_tester.presentacion.adapter.ProductoAdapter;

import java.util.List;

public class ListaProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private ProductoNegocio productoNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        recyclerViewProductos = findViewById(R.id.recyclerViewProductos);
        productoNegocio = new ProductoNegocio(this);

        // Obtener ID de la subcategoría del Intent
        int idSubcategoria = getIntent().getIntExtra("idSubcategoria", -1);

        // Configurar RecyclerView
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));

        // Obtener productos por subcategoría y configurar el adaptador
        if (idSubcategoria != -1) {
            List<Producto> productos = productoNegocio.obtenerProductosPorSubcategoria(idSubcategoria);
            productoAdapter = new ProductoAdapter(this, productos);
            recyclerViewProductos.setAdapter(productoAdapter);
        }
    }
}
