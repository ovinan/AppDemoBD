package com.example.appdemobd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRegistros extends RecyclerView.Adapter<AdaptadorRegistros.HolderRegistro>{

    private Context contexto;
    private ArrayList<Registro> registros;

    public AdaptadorRegistros(Context contexto, ArrayList<Registro> registros) {
        this.registros = registros;
        this.contexto = contexto;
    }
    public void refrescar()
    {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderRegistro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_registro, parent, false);
        HolderRegistro pvh = new HolderRegistro(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRegistro holder, int position) {
        holder.lNombre.setText(registros.get(position).getNombre());
        holder.lEdad.setText(String.valueOf(registros.get(position).getEdad()));
        holder.lNumero.setText(String.valueOf(registros.get(position).getNumero()));
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public static class HolderRegistro extends RecyclerView.ViewHolder
    {
        TextView lNombre, lEdad, lNumero;
        HolderRegistro(View itemView)
        {
            super(itemView);
            lNombre = itemView.findViewById(R.id.txtNombre);
            lEdad = itemView.findViewById(R.id.txtEdad);
            lNumero = itemView.findViewById(R.id.txtNumero);
        }
    };
}
