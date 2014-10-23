package danna.net.gadapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class Forgot extends Activity {
    private EditText mail;
    private Cursor cuentas;
    private MyDatabase database;
    ImageButton recovery ;
    ImageButton cancel;
    String nombre="";
    String contrasena="";
    String message="";
    String[] attachs={""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        mail = (EditText)findViewById(R.id.txtrecovery);
        recovery = (ImageButton)findViewById(R.id.btnrecovery);
        cancel = (ImageButton)findViewById(R.id.btncancel);


    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnrecovery:   {
                new PostTask().execute(mail.getText().toString());
                finish();

            }break;
            case R.id.btncancel :finish(); ;break;
        }
    }

    private final class PostTask extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... args) {


             Boolean valido=true;
            // run only once... ?
            // copy assets DB to app DB.
            nombre= args[0];

            try {
                database= new MyDatabase(getApplicationContext());
                Cursor cursor = database.getCuenta(args[0]);
                cursor.moveToFirst();
                 if (cursor.getCount()>0){
                     contrasena = cursor.getString(1);
                     message ="La contrasena del usuario" + nombre + "es :"+contrasena;

               //   SendMail sender = new SendMail();
                    GMailSender sender = new GMailSender("educayambe1@gmail.com","educayambe2021");
                  new Sender_Email(message,nombre).doInBackground(sender);

                     valido = true;
                 }
                cuentas.close();
                database.close();
              //build the message


            } catch (Exception ioe) {
                System.out.print(ioe.toString());
            }

            return valido;
         }

        protected void onProgressUpdate(Boolean... progress) {
            System.out.print(progress);
        }

        protected void onPostExecute(Boolean result) {

            if (result){
                Toast.makeText(getApplicationContext(), "Se ha enviado un correo a su cuenta, con su contrasena",
                        Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(getApplicationContext(), "Usuario no existe, verifique la direccion de correo",
                        Toast.LENGTH_SHORT).show();
            }

        }





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.forgot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean sendEmail(String to, String from, String subject,
                                    String message,String[] attachements) throws Exception {
        SendMail mail = new SendMail();
        if (subject != null && subject.length() > 0) {
            mail.setSubject(subject);
        } else {
            mail.setSubject("Subject");
        }

        if (message != null && message.length() > 0) {
            mail.setBody(message);
        } else {
            mail.setBody("Message");
        }

        mail.setTo(new String[] {to});

        if (attachements != null) {
            for (String attachement : attachements) {
                mail.addAttachment(attachement);
            }
        }
        return mail.send();
    }
}
