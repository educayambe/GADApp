package danna.net.gadapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class Login extends Activity implements OnClickListener {
    private EditText  username=null;
    private EditText  password=null;
    private ImageButton login;
    private ImageButton forgot;
    private ImageButton new_user;
    private Cursor cuentas;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (ImageButton) findViewById(R.id.btnlogin);
        forgot = (ImageButton) findViewById(R.id.btnforgot);
        new_user = (ImageButton) findViewById(R.id.btnnew);
        login.setOnClickListener(this);
        forgot.setOnClickListener(this);
        new_user.setOnClickListener(this);

    }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnlogin:{new PostTask().execute(username.getText().toString(), password.getText().toString());break;}
                case R.id.btnforgot:{startActivity(new Intent( Login.this, Forgot.class));  break;}
                case R.id.btnnew: {startActivity(new Intent( Login.this, Register.class)); break;}
            }
        }



    private final class PostTask extends    AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... args) {

            Boolean valido=false;
            // run only once... ?
            // copy assets DB to app DB.
            try {
                       database= new MyDatabase(getApplicationContext());
                        Cursor cursor = database.getCuenta(args[0]);
                        String nombre;
                        String contrasena;

                        do{
                                nombre = cursor.getString(0);
                                contrasena = cursor.getString(1);
                                if (args[0].equals(nombre)&& args[1].equals(contrasena)){
                                                valido=true;

                                }
                        }while(cursor.moveToNext());
                cuentas.close();
                database.close();
            } catch (Exception ioe) {
                System.out.print(ioe.toString());
            }

        return valido;
        }

        protected void onProgressUpdate(Boolean... progress) {
            System.out.print(progress);
        }

        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(), "Ingresando...",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent( Login.this, Principal.class));
            }
            else
                Toast.makeText(getApplicationContext(), "Contrasena Incorrecta",
                        Toast.LENGTH_SHORT).show();

        }





    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
