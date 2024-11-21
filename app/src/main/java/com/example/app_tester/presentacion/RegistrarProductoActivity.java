package com.example.app_tester.presentacion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tester.R;
import com.example.app_tester.datos.DAO.InventarioDAO;
import com.example.app_tester.datos.DAO.SubcategoriaDAO;
import com.example.app_tester.datos.entities.Inventario;
import com.example.app_tester.datos.entities.Subcategoria;
import com.example.app_tester.negocio.ProductoNegocio;
import com.example.app_tester.presentacion.adapter.FotosAdapter;

import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RegistrarProductoActivity extends AppCompatActivity {
    private static final int PICK_IMAGES = 1; // Código único para identificar la selección de imágenes
    private static final int REQUEST_PERMISSION = 100; // Código de permisos

    private ProductoNegocio productoNegocio;
    private InventarioDAO inventarioDAO;
    private SubcategoriaDAO subcategoriaDAO;
    private Spinner spinnerInventario, spinnerSubcategoria;
    private List<Uri> fotosSeleccionadas = new ArrayList<>();
    private FotosAdapter fotosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_producto);

        solicitarPermisos();

        // Inicializar DAOs y lógica de negocio
        productoNegocio = new ProductoNegocio(this);
        inventarioDAO = new InventarioDAO(this);
        subcategoriaDAO = new SubcategoriaDAO(this);

        // Configuración de spinners
        spinnerInventario = findViewById(R.id.spinnerInventario);
        spinnerSubcategoria = findViewById(R.id.spinnerSubcategoria);
        cargarSpinnerInventario();
        cargarSpinnerSubcategoria();

        // Configuración de RecyclerView para imágenes
        RecyclerView recyclerFotos = findViewById(R.id.recyclerFotos);
        recyclerFotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        fotosAdapter = new FotosAdapter(fotosSeleccionadas);
        recyclerFotos.setAdapter(fotosAdapter);

        // Botones
        Button btnAgregarFoto = findViewById(R.id.btnAgregarFoto);
        btnAgregarFoto.setOnClickListener(v -> seleccionarImagenes());

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(v -> registrarProducto());
    }

    private void cargarSpinnerInventario() {
        List<Inventario> inventarios = inventarioDAO.obtenerTodosLosInventarios();
        ArrayAdapter<Inventario> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, inventarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInventario.setAdapter(adapter);
    }

    private void cargarSpinnerSubcategoria() {
        List<Subcategoria> subcategorias = subcategoriaDAO.obtenerTodasLasSubcategorias();
        ArrayAdapter<Subcategoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subcategorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcategoria.setAdapter(adapter);
    }

    private void seleccionarImagenes() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Selecciona imágenes"), PICK_IMAGES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    fotosSeleccionadas.add(imageUri);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                fotosSeleccionadas.add(imageUri);
            }
            fotosAdapter.notifyDataSetChanged();
        }
    }

    private void registrarProducto() {
        try {
            // Recopilar datos
            String nombre = ((EditText) findViewById(R.id.editNombre)).getText().toString();
            String descripcion = ((EditText) findViewById(R.id.editDescripcion)).getText().toString();
            double precio = Double.parseDouble(((EditText) findViewById(R.id.editPrecio)).getText().toString());
            String unidad = ((EditText) findViewById(R.id.editUnidad)).getText().toString();
            int cantidad = Integer.parseInt(((EditText) findViewById(R.id.editCantidad)).getText().toString());
            int id_inventario = ((Inventario) spinnerInventario.getSelectedItem()).getId();
            int id_subcategoria = ((Subcategoria) spinnerSubcategoria.getSelectedItem()).getId();
            String estado = "activo";

            // Guardar imágenes seleccionadas
            if (fotosSeleccionadas.isEmpty()) {
                Toast.makeText(this, "Selecciona al menos una imagen", Toast.LENGTH_SHORT).show();
                return;
            }
            String fotosGuardadas = guardarFotos();

            // Registrar producto
            long resultado = productoNegocio.registrarProducto(nombre, descripcion, precio, unidad, cantidad, estado, fotosGuardadas, id_inventario, id_subcategoria);

            if (resultado != -1) {
                Toast.makeText(this, "Producto creado exitosamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrarProductoActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error al crear el producto", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Por favor, completa todos los campos correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    private String guardarFotos() {
        StringBuilder fotosUris = new StringBuilder();
        for (Uri uri : fotosSeleccionadas) {
            // Verifica y guarda las URIs de las fotos seleccionadas
            fotosUris.append(uri.toString()).append(";"); // Separa las URIs con un ";"
        }
        return fotosUris.toString(); // Devuelve todas las URIs en un único String
    }


    private String guardarImagen(Uri uri) {
        try {
            File directorio = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Productos");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            String nombreArchivo = "IMG_" + System.currentTimeMillis() + ".jpg";
            File archivoImagen = new File(directorio, nombreArchivo);

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            try (FileOutputStream outputStream = new FileOutputStream(archivoImagen)) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
            }

            return archivoImagen.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void solicitarPermisos() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
