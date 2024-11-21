package com.example.app_tester.presentacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.app_tester.R;
import com.google.android.material.navigation.NavigationView;

public class MenuPrincipalActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Eliminar el título de la Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");  // Título vacío
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Manejo de los clics en el menú de navegación
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_perfil:
                    // Ir a la pantalla de perfil
                    startActivity(new Intent(MenuPrincipalActivity.this, PerfilActivity.class));
                    break;

                case R.id.nav_list:
                    // Ir al inventario (Registrar Inventario)
                    startActivity(new Intent(MenuPrincipalActivity.this, RegistrarInventarioActivity.class));
                    break;

                case R.id.nav_logout:
                    // Cerrar sesión
                    startActivity(new Intent(MenuPrincipalActivity.this, LoginActivity.class));
                    finish();
                    break;

                case R.id.nav_crear_inventario:
                    // Ir a la actividad de creación de inventario
                    startActivity(new Intent(MenuPrincipalActivity.this, RegistrarInventarioActivity.class));
                    break;

                case R.id.nav_ver_historial:
                    // Ir a la actividad de ver historial de inventarios
                    startActivity(new Intent(MenuPrincipalActivity.this, VerTodoInventarioActivity.class));
                    break;

                case R.id.nav_crear_categoria:
                    // Ir a la actividad de crear categorias
                    startActivity(new Intent(MenuPrincipalActivity.this, RegistrarCategoriaActivity.class));
                    break;

                case R.id.nav_crear_subcategoria:
                    // Ir a la actividad de crear subcategorias
                    startActivity(new Intent(MenuPrincipalActivity.this, RegistrarSubcategoriaActivity.class));
                    break;

                case R.id.nav_ver_subcategoria:
                    // Ir a la actividad de crear subcategorias
                    startActivity(new Intent(MenuPrincipalActivity.this, VerSubcategoriaActivity.class));
                    break;

                case R.id.nav_crear_producto:
                    // Ir a la actividad de crear subcategorias
                    startActivity(new Intent(MenuPrincipalActivity.this, RegistrarProductoActivity.class));
                    break;


            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }
}
