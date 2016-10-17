package com.erickcasav.petagrammvp.presentador;

import android.content.Context;

import com.erickcasav.petagrammvp.db.ConstructorMascotas;
import com.erickcasav.petagrammvp.fragment.IMascotasListado;
import com.erickcasav.petagrammvp.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ejcastaneda on 11/10/2016.
 */

public class MascotasListadoPresenter implements IMascotasListadoPresenter {

    private IMascotasListado iMascotasListadoView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public MascotasListadoPresenter(IMascotasListado iMascotasListadoView, Context context) {
        this.iMascotasListadoView = iMascotasListadoView;
        this.context = context;
        obtenerMascotas();
    }

    @Override
    public void obtenerMascotas() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotas();
    }

    @Override
    public void mostrarMascotas() {
        iMascotasListadoView.inicializarAdaptadorRV(iMascotasListadoView.crearAdaptador(mascotas));
        iMascotasListadoView.generarLinearLayoutVertical();
    }
}
