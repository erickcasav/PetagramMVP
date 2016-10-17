package com.erickcasav.petagrammvp.fragment;

import com.erickcasav.petagrammvp.adapter.MascotaAdaptador;
import com.erickcasav.petagrammvp.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ejcastaneda on 11/10/2016.
 */

public interface IMascotasListado {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
