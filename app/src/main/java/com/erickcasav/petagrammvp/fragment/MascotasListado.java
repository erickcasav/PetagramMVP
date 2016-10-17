package com.erickcasav.petagrammvp.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erickcasav.petagrammvp.R;
import com.erickcasav.petagrammvp.adapter.MascotaAdaptador;
import com.erickcasav.petagrammvp.pojo.Mascota;
import com.erickcasav.petagrammvp.presentador.IMascotasListadoPresenter;
import com.erickcasav.petagrammvp.presentador.MascotasListadoPresenter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MascotasListado extends Fragment implements IMascotasListado {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private IMascotasListadoPresenter presenter;

    public MascotasListado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mascotas_listado, container, false);

        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotaLista);
/*
        FloatingActionButton fabCamera = (FloatingActionButton)v.findViewById(R.id.fabCamera);
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Foto", Toast.LENGTH_SHORT).show();
            }
        });
*/
        presenter = new MascotasListadoPresenter(this, getContext());
        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llmMascota = new LinearLayoutManager(getActivity());
        llmMascota.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llmMascota);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador (mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }
}
