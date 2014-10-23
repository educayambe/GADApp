package danna.net.gadapp;

import android.os.AsyncTask;

/**
 * Created by javier on 10/21/2014.
 */
public class Sender_Email extends AsyncTask<GMailSender,Boolean,Integer> {
    private String from;
    private String message;
public Sender_Email(String from, String message){
    this.from=from;
    this.message=message;
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
}
