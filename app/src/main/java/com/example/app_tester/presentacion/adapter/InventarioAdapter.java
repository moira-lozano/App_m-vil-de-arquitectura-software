package com.example.app_tester.presentacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_tester.R;
import com.example.app_tester.datos.entities.Inventario;
import java.util.List;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.InventarioViewHolder> {
    private List<Inventario> inventarioList;

    public InventarioAdapter(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
    }

    @NonNull
    @Override
    public InventarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inventario, parent, false);
        return new InventarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioViewHolder holder, int position) {
        Inventario inventario = inventarioList.get(position);

        holder.textDescripcion.setText(inventario.getDescripcion());
        holder.textUnidad.setText(inventario.getUnidad());
        holder.textCantidad.setText(String.valueOf(inventario.getCantidad()));
        holder.textFecha.setText(inventario.getFecha());
        holder.textNombreUsuario.setText(inventario.getNombreUsuario());  // Asegúrate de tener este campo
    }

    @Override
    public int getItemCount() {
        return inventarioList.size();
    }

    public static class InventarioViewHolder extends RecyclerView.ViewHolder {
        TextView textDescripcion;
        TextView textUnidad;
        TextView textCantidad;
        TextView textFecha;
        TextView textNombreUsuario;

        public InventarioViewHolder(@NonNull View itemView) {
            super(itemView);
            textDescripcion = itemView.findViewById(R.id.textDescripcion);
            textUnidad = itemView.findViewById(R.id.textUnidad);
            textCantidad = itemView.findViewById(R.id.textCantidad);
            textFecha = itemView.findViewById(R.id.textFecha);
            textNombreUsuario = itemView.findViewById(R.id.textNombreUsuario);  // Inicializa el TextView para el nombre del usuario
        }
    }
}
