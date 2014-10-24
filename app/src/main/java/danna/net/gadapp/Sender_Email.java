package danna.net.gadapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by javier on 10/21/2014.
 */
public class Sender_Email extends AsyncTask<GMailSender,Boolean,Integer> {
    private String from;
    private String message;
    Context context;
public Sender_Email(String from, String message,Context context){
    this.from=from;
    this.message=message;
    this.context = context;
}
    @Override
    protected Integer doInBackground(GMailSender... params) {
        try {
            params[0].sendMail("educayambe@municipiocayambe.gob.ec","Recuperacion de Contrasena", message, "educayambe@municipiocayambe.gob.ec", from);

        }    catch (Exception e) {
        System.out.print(e);
    }
        return 1;
    }

    protected Boolean doInBackground(SendMail... params) {
        Boolean valido=false;
        try {
            valido= params[0].send();

        }    catch (Exception e) {
            System.out.print(e);
        }
        return valido;
    }
    protected void onPostExecute(Boolean result) {

        if (result){
            Toast.makeText(context, "Se ha enviado un correo a su cuenta, con su contrasena",
                    Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(context, "Usuario no existe, verifique la direccion de correo",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
