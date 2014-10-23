package danna.net.gadapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class EagleEyes extends Activity implements ListFragment.OnFragmentInteractionListener,FragmentDetail.OnFragmentInteractionListener
        ,cameraFragment.OnFragmentInteractionListener,TakePicture.OnFragmentInteractionListener {
    private FragmentManager fragmentManager=null;
    private FragmentTransaction fragmentTransaction=null;
    ListFragment Listfragment=null;
    private String filename;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eagle_eyes);
        fragmentManager = getFragmentManager();
        fragmentTransaction =  fragmentManager.beginTransaction();
        if (savedInstanceState==null) {
            Listfragment = new ListFragment();
            fragmentTransaction.add(android.R.id.content, Listfragment);
        }else{
             fragmentTransaction.replace(android.R.id.content, Listfragment);
            }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.eagle_eyes, menu);
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

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnfacebook : {
                Log.d("Activity", "Boton facebook pressed'");
                filename="guia_fb.pdf";

                try {
                    loadDocInReader(filename, view);
                } catch (ActivityNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                /*
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, Listfragment);
                FragmentDetail fbfragment = new FragmentDetail();
                fragmentTransaction.replace(R.id.frdetails,fbfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

*/
            };break;
            case R.id.bnttwitter : {
                Log.d("Activity", "Boton twitter pressed'");
                filename="guia_twt.pdf";
                try {
                        loadDocInReader(filename, view);
                    } catch (ActivityNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                /*
                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, Listfragment);
                FragmentDetail twfragment = new FragmentDetail();
                fragmentTransaction.replace(R.id.frdetails,twfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            */
            }break;
            case R.id.btncamera : {
                Log.d("Activity", "Boton camera pressed'");


                fragmentManager = getFragmentManager();
                fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, Listfragment);
                TakePicture camfragment = new TakePicture();
                fragmentTransaction.replace(R.id.frdetails,camfragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }break;
        }

    }

    @Override
    public void onButtonClicked(Integer id) {
        switch(id){
            case 0:{


            }break;
            case 1:break;

        }

    }
    @Override
    public  void onFragmentInteraction(Uri uri){
        Log.d("Message from Fragment 2","Loaded");
    }

    @Override
    public void getPicture(View view) {
        ImageView picture = (ImageView)view.findViewById(R.id.imgCam);
        TakePicture foto =  (TakePicture)
                fragmentManager.findFragmentById(R.id.camera_preview);
        Bundle args = new Bundle();
        String path="";
        args.getString("Path",path);
    }

    @Override




    public void onPause()
    {
        super.onPause();

        Log.d("EagleEyes is on Pause", "In the onPause() event");
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
