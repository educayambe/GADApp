package danna.net.gadapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class cameraFragment extends Fragment {
    private static final int CAMERA_PIC_REQUEST = 1337;

    Uri fileUri = null;
    ImageView photoImage = null;
    private Bitmap bitmap;

    private View view;
    public cameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =
                inflater.inflate(R.layout.fragment_camera, container, false);        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("EagleEyes is on paused", "In the onStop() event");
    }
    @Override
    public void onStart(){
        super.onStart();
        photoImage =(ImageView) view.findViewById(R.id.imgCam);
        CallCamera();



    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void CallCamera(){
        //create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //start the image capture Intent
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if( requestCode == CAMERA_PIC_REQUEST )
        {

            //get the picture as a bitmap
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");


            //update the image of the camera icon to now show the picture that was taken
            photoImage.setImageBitmap(thumbnail);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
