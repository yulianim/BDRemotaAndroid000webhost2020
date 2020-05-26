package com.ittg.bdremota22.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ittg.bdremota22.R;
import com.ittg.bdremota22.model.Usuario;

import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder> {
    List<Usuario> listaUsuarios;
    public UsuariosAdapter(List<Usuario> listaUsuarios){
        this.listaUsuarios=listaUsuarios;
    }
    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_usuarios, parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosHolder holder, int position) {
        holder.txtNombre.setText(listaUsuarios.get(position).getNombre().toString());
        holder.txtApellidos.setText(listaUsuarios.get(position).getApellidos().toString());
        holder.txtEdad.setText(listaUsuarios.get(position).getEdad().toString());

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtApellidos, txtEdad;
        public UsuariosHolder(View itemView){
            super(itemView);
            txtNombre=(TextView)itemView.findViewById(R.id.txtNombre);
            txtApellidos=(TextView)itemView.findViewById(R.id.txtApellidos);
            txtEdad=(TextView)itemView.findViewById(R.id.txtEdad);
        }
    }
}
