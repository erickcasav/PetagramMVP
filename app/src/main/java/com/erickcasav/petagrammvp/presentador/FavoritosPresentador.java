package com.erickcasav.petagrammvp.presentador;

import android.content.Context;

import com.erickcasav.petagrammvp.IFavoritos;
import com.erickcasav.petagrammvp.db.ConstructorMascotas;
import com.erickcasav.petagrammvp.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ejcastaneda on 13/10/2016.
 */

public class FavoritosPresentador implements IFavoritosPresentador {

    private IFavoritos iFavoritos;
    private ConstructorMascotas constructorMascotas;
    private Context context;
    private ArrayList<Mascota> mascotas;

    public FavoritosPresentador(IFavoritos iFavoritos, Context context) {
        this.iFavoritos = iFavoritos;
        this.context = context;
        obtenerFavoritos();
    }

    @Override
    public void obtenerFavoritos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerMascotasFavoritos();
        mostrarFavoritos();
    }

    @Override
    public void mostrarFavoritos() {
        iFavoritos.inicializarAdaptadorRV(iFavoritos.crearAdaptador(mascotas));
        iFavoritos.generarLinearLayoutVertical();
    }
}
