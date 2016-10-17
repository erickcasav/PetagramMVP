package com.erickcasav.petagrammvp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickcasav.petagrammvp.R;
import com.erickcasav.petagrammvp.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ejcastaneda on 11/08/2016.
 */
public class MascotaPerfilAdaptador extends RecyclerView.Adapter<MascotaPerfilAdaptador.MascotaPerfilViewHolder>  {

    ArrayList<Mascota> mascotas;
    Activity activity;
    ViewGroup parent;

    public MascotaPerfilAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaPerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_perfil, parent, false);
        return new MascotaPerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaPerfilViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFotoCVPerfil.setImageResource(mascota.getFotoMascota());
        mascotaViewHolder.tvCantidadLikePerfil.setText(String.valueOf(mascota.getCantidadLikes()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaPerfilViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgFotoCVPerfil;
        public TextView tvCantidadLikePerfil;

        public MascotaPerfilViewHolder(View itemView) {
            super(itemView);

            imgFotoCVPerfil = (ImageView) itemView.findViewById(R.id.imgFotoCVPerfil);
            tvCantidadLikePerfil = (TextView) itemView.findViewById(R.id.tvCantidadLikePerfil);
        }
    }
}
