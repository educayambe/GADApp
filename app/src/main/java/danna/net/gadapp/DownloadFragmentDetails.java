package danna.net.gadapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DownloadFragmentDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DownloadFragmentDetails#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DownloadFragmentDetails extends Fragment implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener  {

    private List<String> listDataHeader;
    private HashMap<String, List<AppsClassChild>> listDataChild;
    private XMLParseApps ParseApps;
    String filename="Apps.xml";
    String filename2="Books.xml";
    MyExpandableListAdapter listAdapter;
    InputStream filen=null;

    private Context context=null;
    private ExpandableListView ListApps;

    private ArrayList<AppsClass> App = new ArrayList<AppsClass>();
    View view=null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DownloadFragmentDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static DownloadFragmentDetails newInstance(String param1, String param2) {
        DownloadFragmentDetails fragment = new DownloadFragmentDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public DownloadFragmentDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mParam1= getArguments().getString("Type");
        view = inflater.inflate(R.layout.fragment_fragment_download_details, container, false);

        ListApps = (ExpandableListView) view.findViewById(R.id.ListDescargas);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
         AppsClassChild childApp =(AppsClassChild)   parent.getSelectedItem();
        Log.d("Selected item",childApp.getName().toString());
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);

    }


    @Override
    public void onStart(){
        super.onStart();
        if (mParam1.equals("Apps")){
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
            Log.d("Activity", "Boton Apps pressed'");

        }else{
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
            Log.d("Activity", "Boton Book pressed'");
        }
        buildArrays();//build the array to the Expandeable List View
        listAdapter =new MyExpandableListAdapter(view.getContext(), listDataHeader, listDataChild);
        ListApps.setAdapter(listAdapter);
        ListApps.setOnChildClickListener(this);


    }

    public void buildArrays(){
        if (ParseApps.entries.size()>0){
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<AppsClassChild>>();
            for (int i=0;i<ParseApps.entries.size();i++){
                listDataHeader.add( ParseApps.entries.get(i).getNameApp());
                listDataChild.put(ParseApps.entries.get(i).getNameApp(),ParseApps.entries.get(i).getChild());
            }
        }

    }
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(view.getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
