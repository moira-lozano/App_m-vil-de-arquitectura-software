package com.example.app_tester.presentacion.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_tester.R;

import java.util.List;

public class FotosAdapter extends RecyclerView.Adapter<FotosAdapter.FotoViewHolder> {
    private List<Uri> fotos;

    public FotosAdapter(List<Uri> fotos) {
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_foto, parent, false);
        return new FotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        Uri fotoUri = fotos.get(position);

        // Usar Glide para cargar la imagen desde el Uri
        Glide.with(holder.imageView.getContext())
                .load(fotoUri)
                .placeholder(R.drawable.placeholder_image) // Imagen por defecto mientras carga
                .error(R.drawable.error_image)             // Imagen en caso de error
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageFoto);
        }
    }
}
