package danna.net.gadapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Biography extends Activity {
    GridView grid;
    Context context;
    private MyDatabase database;
    FragmentManager fm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biography);
        grid=(GridView)findViewById(R.id.grdViewPersonajes);
        fm = this.getFragmentManager();
        new PostTask().execute(1);

    }


    private final class PostTask extends    AsyncTask<Integer, Void, ArrayList<Personaje>> {

        protected ArrayList<Personaje> doInBackground(Integer... args) {

            ArrayList<Personaje> Personajes = new ArrayList<Personaje>();
            // run only once... ?
            // copy assets DB to app DB.
            try {
                database = new MyDatabase(getApplicationContext());
                Personajes = database.getPersonas();

                database.close();
            } catch (Exception ioe) {
                System.out.print(ioe.toString());
            }
            return Personajes;
        }

        protected void onPostExecute(final ArrayList<Personaje> Personajes) {

            grid.setAdapter(new CustomAdapter(Biography.this, R.layout.activity_biography, Personajes));
            grid.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                    Toast.makeText(getApplicationContext(), Personajes.get(position).getNombre(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent (Biography.this, Detail.class);
                    i.putExtra("nombre", Personajes.get(position).getNombre());
                    startActivity(i);

                }
            });



        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.biography, menu);
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
        if (id == R.id.about) {
            DialogFragment dialog = new DialogFragment();

            dialog.show(fm,"Biografia");
            return true;
        }if (id == R.id.Salir) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
