package danna.net.gadapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Tourism extends Activity  implements TourismList.OnFragmentInteractionListener,TourismDetail.OnFragmentInteractionListener,MapDetails.OnFragmentInteractionListener {


    private FragmentManager fragmentManager=null;
    private FragmentTransaction fragmentTransaction=null;

    Bundle bundle =null;
    TourismList Listfragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_tourism);
        fragmentManager = getFragmentManager();
        fragmentTransaction =  fragmentManager.beginTransaction();
        if (savedInstanceState==null) {
            Listfragment = new TourismList();
            fragmentTransaction.add(android.R.id.content, Listfragment);
        }else{
            fragmentTransaction.replace(android.R.id.content, Listfragment);
        }
        bundle = new Bundle();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tourism, menu);
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

        }if (id == R.id.Salir) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnNorte : {
                Log.d("Activity", "Boton Norte pressed'");
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, Listfragment);

                bundle.putString("Type_site", "North");
                TourismDetail Northfragment = new TourismDetail();
                Northfragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frtrdetails,Northfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            };break;
            case R.id.btnCentro : {
                Log.d("Activity", "Boton Centro pressed'");
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, Listfragment);
                bundle.putString("Type_site", "DownTown");
                TourismDetail DownTown = new TourismDetail();
                DownTown.setArguments(bundle);
                fragmentTransaction.replace(R.id.frtrdetails,DownTown);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }break;
            case R.id.btnSur : {
                Log.d("Activity", "Boton Sur pressed'");
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, Listfragment);
                bundle.putString("Type_site", "South");
                TourismDetail Southfragment = new TourismDetail();
                Southfragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frtrdetails,Southfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }break;
            case R.id.btnNevado : {
                Log.d("Activity", "Boton Mountain  pressed'");
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, Listfragment);
                bundle.putString("Type_site", "Mountain");
                TourismDetail Mountainfragment = new TourismDetail();
                Mountainfragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frtrdetails,Mountainfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }break;
            case R.id.btnmapa : {
                Log.d("Activity", "Boton map pressed'");
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                bundle.putString("Type_site", "Map");
                fragmentTransaction.replace(android.R.id.content, Listfragment);
                MapDetails MapCayambe = new MapDetails();
                MapCayambe.setArguments(bundle);
                fragmentTransaction.replace(R.id.frtrdetails,MapCayambe);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }break;
            case R.id.btnMapaCircuito : {
                Log.d("Activity", "Boton map circuitos pressed'");
                String filename;
                filename="mapa_circuitos.pdf";
                try {
                    loadDocInReader(filename, view);
                } catch (ActivityNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }break;
        }

    }
    @Override
    public void onPause(){
        super.onPause();
    }
    private void loadDocInReader(String doc,View view)

    {
        String file ="/mnt/sdcard"+ File.separator+ doc.toString();
        UtilsFiles files= new UtilsFiles(view.getContext(),doc);
        files.CopyFile(file);
        File filep = new File(file);
        if (filep.exists()) {
            Uri filepath = Uri.fromFile(filep);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(filepath, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (Exception e) {

                Log.e("error", "PDF File not open" + e);
            }


        } else {
            Log.e("error", "PDF File not open" );

        }


    }

}
