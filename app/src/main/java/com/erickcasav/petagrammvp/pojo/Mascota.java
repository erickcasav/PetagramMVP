package com.erickcasav.petagrammvp.pojo;

/**
 * Created by ejcastaneda on 11/08/2016.
 */
public class Mascota {

    private int identificadorMascota;
    private String nombreMascota;
    private int cantidadLikes;
    private int fotoMascota;

    public Mascota(int identificadorMascota, String nombreMascota, int cantidadLikes, int fotoMascota) {
        this.identificadorMascota = identificadorMascota;
        this.nombreMascota = nombreMascota;
        this.cantidadLikes = cantidadLikes;
        this.fotoMascota = fotoMascota;
    }

    public Mascota() {
    }

    public int getIdentificadorMascota() {
        return identificadorMascota;
    }

    public void setIdentificadorMascota(int identificadorMascota) {
        this.identificadorMascota = identificadorMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getCantidadLikes() {
        return cantidadLikes;
    }

    public void setCantidadLikes(int cantidadLikes) {
        this.cantidadLikes = cantidadLikes;
    }

    public int getFotoMascota() {
        return fotoMascota;
    }

    public void setFotoMascota(int fotoMascota) {
        this.fotoMascota = fotoMascota;
    }

}
