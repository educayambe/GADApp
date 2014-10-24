package danna.net.gadapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;


public class Download extends Activity  implements DownloadFragmentDetails.OnFragmentInteractionListener
        ,DownloadFragmentList.OnFragmentInteractionListener
{
    private FragmentManager fragmentManager=null;
    private FragmentTransaction fragmentTransaction=null;


    Bundle bundle =null;
    DownloadFragmentList downloadFragmentList =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Configuration config = getResources().getConfiguration();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            downloadFragmentList = new DownloadFragmentList();
            fragmentTransaction.replace(android.R.id.content,downloadFragmentList);

        }
        bundle = new Bundle();
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.download, menu);
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
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.btnApp:{
                Log.d("Activity", "Boton App pressed'");
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, downloadFragmentList);
                bundle.putString("Type", "Apps");
                DownloadFragmentDetails Appsfragment = new DownloadFragmentDetails();
                Appsfragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frdwdetails,Appsfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            };break;
            case R.id.btnbook: {

                Log.d("Activity", "Boton Book pressed'");
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, downloadFragmentList);
                bundle.putString("Type", "Books");
                DownloadFragmentDetails Booksfragment = new DownloadFragmentDetails();
                Booksfragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frdwdetails,Booksfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
             }

        }

    }



}
