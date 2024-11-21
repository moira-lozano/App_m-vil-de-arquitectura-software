package com.example.app_tester.presentacion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tester.R;
import com.example.app_tester.datos.entities.Subcategoria;

import java.util.List;

public class SubcategoriaAdapter extends RecyclerView.Adapter<SubcategoriaAdapter.SubcategoriaViewHolder> {

    private Context context;
    private List<Subcategoria> subcategorias;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Subcategoria subcategoria);
    }

    public SubcategoriaAdapter(Context context, List<Subcategoria> subcategorias, OnItemClickListener listener) {
        this.context = context;
        this.subcategorias = subcategorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubcategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subcategoria, parent, false);
        return new SubcategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoriaViewHolder holder, int position) {
        Subcategoria subcategoria = subcategorias.get(position);
        holder.textNombre.setText(subcategoria.getNombre());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(subcategoria);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subcategorias.size();
    }

    public static class SubcategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre;

        public SubcategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombreSubcategoria);
        }
    }
}


