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

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;


public class Download extends Activity  implements DownloadFragmentDetails.OnFragmentInteractionListener,DownloadFragmentList.OnFragmentInteractionListener {
    private FragmentManager fragmentManager=null;
    private FragmentTransaction fragmentTransaction=null;
    private XMLParseApps ParseApps;
    String filename="Apps.xml";
    String filename2="Books.xml";

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
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnApp : {
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
            case R.id.btnbook : {
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

            }break;

        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void LoadExpListView(View view){
        MyExpandableListAdapter listAdapter;
        ExpandableListView expListView;
        InputStream filen=null;
        expListView = (ExpandableListView) findViewById(R.id.ListDescargas);
        switch(view.getId()) {
            case R.id.btnApp:{
                try {
                    UtilsFiles files = new UtilsFiles(view.getContext(), filename);
                    files.CopyFile();
                    filen = view.getContext().getAssets().open("files/" + filename);
                } catch (IOException ioe) {
                    System.out.printf("Open File Error %s", ioe.toString());
                }
                ParseApps = new XMLParseApps();
                try {
                    ParseApps.parse(filen);
                } catch (Exception e) {
                    System.out.printf("Open File Error %s", e.toString());
                }
                };break;
            case R.id.btnbook: {

                    try {
                        UtilsFiles files = new UtilsFiles(view.getContext(), filename2);
                        files.CopyFile();
                        filen = view.getContext().getAssets().open("files/" + filename2);
                    } catch (IOException ioe) {
                        System.out.printf("Open File Error %s", ioe.toString());
                    }
                    ParseApps = new XMLParseApps();
                    try {
                        ParseApps.parse(filen);
                    } catch (Exception e) {
                        System.out.printf("Open File Error %s", e.toString());
                    }
             }

        }

        listAdapter = new MyExpandableListAdapter(this, ParseApps.entries);
        expListView.setAdapter(listAdapter);
    }
}
