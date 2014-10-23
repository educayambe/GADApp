package danna.net.gadapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.util.ArrayList;


public class Detail extends Activity {
    private  EditText contenido;
    private Personaje Persona;
    private MyDatabase database;
    private TextView title;
    private ImageView imagen;
    private  String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Persona = new Personaje();
        contenido = (EditText) findViewById(R.id.detalle);
        title = (TextView) findViewById(R.id.lblTitle);
        imagen = (ImageView) findViewById(R.id.imgPersona);
        data = new String();
        data = getIntent().getStringExtra("nombre");
        new PostTask().execute(data);


    }

    private final class PostTask extends AsyncTask<String, Void, Personaje> {

        protected Personaje doInBackground(String... args) {

           Persona = new Personaje();
            // run only once... ?
            // copy assets DB to app DB.
            try {
                database = new MyDatabase(getApplicationContext());
                Persona = database.getPersona(args[0]);
                database.close();
            } catch (Exception ioe) {
                System.out.print(ioe.toString());
            }
            return Persona;
        }

        protected void onPostExecute(final Personaje Persona) {

            title.setText(data);
            Bitmap img = BitmapFactory.decodeByteArray(Persona.getImage(), 0, Persona.getImage().length);
            imagen.setImageBitmap(img);
            contenido.setText(Persona.getNombre());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
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

        if (id == R.id.Salir) {
            finish();
            return true;
        }
        if (id == R.id.updateC) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
