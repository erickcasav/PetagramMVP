package com.erickcasav.petagrammvp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.erickcasav.petagrammvp.R;
import com.erickcasav.petagrammvp.db.ConstructorMascotas;
import com.erickcasav.petagrammvp.pojo.Mascota;
import java.util.ArrayList;

/**
 * Created by ejcastaneda on 11/08/2016.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;ViewGroup parent;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFotoCV.setImageResource(mascota.getFotoMascota());
        mascotaViewHolder.tvNombreMascota.setText(mascota.getNombreMascota());
        mascotaViewHolder.tvCantidadLike.setText(String.valueOf(mascota.getCantidadLikes()));

        if ((position % 2) == 0) {
            mascotaViewHolder.llImagen.setBackgroundColor(parent.getResources().getColor(R.color.colorAccent));
        }
        else{
            mascotaViewHolder.llImagen.setBackgroundColor(parent.getResources().getColor(R.color.colorPrimary));
        }

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);

                constructorMascotas.darLikeMascota(mascota);

                mascotaViewHolder.tvCantidadLike.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));

                //Toast.makeText(activity, "Diste Like a " + mascota.getNombreMascota(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgFotoCV;
        public TextView tvNombreMascota;
        public  TextView tvCantidadLike;
        public ImageButton btnLike;
        public LinearLayout llImagen;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFotoCV = (ImageView) itemView.findViewById(R.id.imgFotoCV);
            tvCantidadLike = (TextView) itemView.findViewById(R.id.tvCantidadLike);
            tvNombreMascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
            llImagen = (LinearLayout) itemView.findViewById(R.id.llImagen);


        }
    }
}
