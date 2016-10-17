package com.erickcasav.petagrammvp.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erickcasav.petagrammvp.R;
import com.erickcasav.petagrammvp.adapter.MascotaPerfilAdaptador;
import com.erickcasav.petagrammvp.pojo.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilMascota extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;

    public PerfilMascota() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil_mascota, container, false);

        rvMascotas = (RecyclerView) v.findViewById(R.id.rvPerfilMascota);

        GridLayoutManager glmMascota = new GridLayoutManager(getActivity(), 3);

        rvMascotas.setLayoutManager(glmMascota);

        inicializarListaMscotas();
        inicializarAdaptador();

        FloatingActionButton fabCameraPerfil = (FloatingActionButton)v.findViewById(R.id.fabCameraPerfil);
        fabCameraPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Foto", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void inicializarAdaptador(){
        MascotaPerfilAdaptador adaptador = new MascotaPerfilAdaptador (mascotas, getActivity());
        rvMascotas.setAdapter(adaptador);
    }

    private void inicializarListaMscotas()
    {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(1, "Princeso", 5, R.drawable.mascota_91));
        mascotas.add(new Mascota(2, "Princeso", 4, R.drawable.mascota_92));
        mascotas.add(new Mascota(3, "Princeso", 3, R.drawable.mascota_93));
        mascotas.add(new Mascota(4, "Princeso", 2, R.drawable.mascota_94));
        mascotas.add(new Mascota(5, "Princeso", 7, R.drawable.mascota_95));
        mascotas.add(new Mascota(6, "Princeso", 9, R.drawable.mascota_96));
        mascotas.add(new Mascota(7, "Princeso", 22, R.drawable.mascota_97));
        mascotas.add(new Mascota(8, "Princeso", 1, R.drawable.mascota_98));
        mascotas.add(new Mascota(9, "Princeso", 15, R.drawable.mascota_99));

    }

}
