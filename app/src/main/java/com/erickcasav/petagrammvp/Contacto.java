package com.erickcasav.petagrammvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erickcasav.petagrammvp.pojo.EnviaCorreo;

public class Contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBarContacto);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText etNombre = (EditText) findViewById(R.id.etNombre);
        final EditText etCorreo = (EditText) findViewById(R.id.etCorreo);
        final EditText etMensaje = (EditText) findViewById(R.id.etMensaje);

        Button btnEnviarComentario = (Button) findViewById(R.id.btnEnviarComentario);

        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarCorreo(etMensaje.getText().toString(), etCorreo.getText().toString(), etNombre.getText().toString());
            }
        });
    }

    private void enviarCorreo(String mensaje, String correo, String titulo)
    {
        EnviaCorreo enviaCorreo = new EnviaCorreo(this, correo, titulo, mensaje);

        enviaCorreo.execute();
    }
}
