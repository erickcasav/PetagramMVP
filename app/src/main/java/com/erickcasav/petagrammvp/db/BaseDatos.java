package com.erickcasav.petagrammvp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.erickcasav.petagrammvp.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by ejcastaneda on 11/10/2016.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se crea la estructura de la BD
        String qryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER " +
                ")";

        String qryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "( " +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + ")" +
                ")";

        db.execSQL(qryCrearTablaMascota);
        db.execSQL(qryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);

        onCreate(db);
    }

    public ArrayList<Mascota> obtenerMascotas()
    {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String qry = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(qry, null);

        while (registros.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setIdentificadorMascota(registros.getInt(0));
            mascota.setNombreMascota(registros.getString(1));
            mascota.setFotoMascota(registros.getInt(2));

            mascota.setCantidadLikes(obtenerLikesMascota(registros.getInt(0)));

            mascotas.add(mascota);
        }

        db.close();

        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritos()
    {
        ArrayList<Mascota> mascotas = new ArrayList<>();


        String qry = "SELECT COUNT(*) AS CUANTOS, ML." + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA
                + ", M." + ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE
                + ", M." + ConstantesBaseDatos.TABLE_MASCOTA_FOTO
                + " FROM " + ConstantesBaseDatos.TABLE_MASCOTA + " M, "
                + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + " ML"
                + " WHERE M." + ConstantesBaseDatos.TABLE_MASCOTA_ID
                + " = ML." + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA
                + " GROUP BY ML." + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA
                + " ORDER BY CUANTOS DESC"
                + " LIMIT 5";

        /*
        String qry = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA
                + " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_ID + " in ("
                + " SELECT " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " FROM ("
                + " SELECT COUNT(*) AS CUANTOS, " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA
                + " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA
                + " GROUP BY " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA
                + " ORDER BY CUANTOS DESC"
                + " LIMIT 5)"
                + ")";
/*


 */
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(qry, null);

        while (registros.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setIdentificadorMascota(registros.getInt(1));
            mascota.setNombreMascota(registros.getString(2));
            mascota.setFotoMascota(registros.getInt(3));

            mascota.setCantidadLikes(obtenerLikesMascota(registros.getInt(1)));

            mascotas.add(mascota);
        }

        db.close();

        return mascotas;
    }


    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(int idMascota){
        int cantidadLikes = 0;

        String qry = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES +
                ") FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA +
                " = " + idMascota;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(qry, null);

        if (cursor.moveToNext()){
            cantidadLikes = cursor.getInt(0);
        }

        db.close();

        return cantidadLikes;
    }

}
