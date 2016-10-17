package com.erickcasav.petagrammvp;

import com.erickcasav.petagrammvp.adapter.MascotaAdaptador;
import com.erickcasav.petagrammvp.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ejcastaneda on 13/10/2016.
 */

public interface IFavoritos {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
