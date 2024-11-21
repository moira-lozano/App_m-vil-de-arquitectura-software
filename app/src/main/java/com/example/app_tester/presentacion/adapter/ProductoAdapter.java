package com.example.app_tester.presentacion.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tester.R;
import com.example.app_tester.datos.entities.Producto;
import com.bumptech.glide.Glide;


import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private Context context;
    private List<Producto> productos;

    public ProductoAdapter(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);

        // Configurar el nombre del producto
        holder.textNombre.setText(producto.getNombre());

        // Obtener la primera imagen del producto (si hay varias)
        String[] fotos = producto.getFoto().split(";"); // Divide las fotos si están separadas por ';'
        if (fotos.length > 0 && !fotos[0].isEmpty()) {
            Glide.with(context)
                    .load(fotos[0]) // Carga la primera imagen
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(holder.imageProducto);
        } else {
            // Si no hay imágenes, muestra un placeholder
            holder.imageProducto.setImageResource(R.drawable.placeholder_image);
        }
    }



    @Override
    public int getItemCount() {

        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre;
        ImageView imageProducto;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombreProducto);
            imageProducto = itemView.findViewById(R.id.imageProducto);
        }
    }


}
