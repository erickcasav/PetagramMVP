package com.erickcasav.petagrammvp.pojo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by ejcastaneda on 11/08/2016.
 */
public class EnviaCorreo extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String correo;
    private String titulo;
    private String mensaje;

    private ProgressDialog progressDialog;

    private Session session;


    public EnviaCorreo(Context context, String correo, String titulo, String mensaje){
        this.context = context;
        this.correo = correo;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(context, "Enviando correo", "Por favor espere...", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        progressDialog.dismiss();
        Toast.makeText(context, "Correo enviado", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try {
            MimeMessage mm = new MimeMessage(session);

            mm.setFrom(new InternetAddress(Config.EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mm.setSubject(titulo);
            mm.setText(mensaje);

            //Envia el mensaje
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return null;
    }
}
