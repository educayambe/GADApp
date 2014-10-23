package danna.net.gadapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TourismDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TourismDetail#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TourismDetail extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final List<Site> ARG_PARAM3 = new ArrayList<Site>();

   private Context context=null;
    private ListView SitiosList;
    private TouristSites Tou_Sites;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Site> Sites = new ArrayList<Site>();
    String filename="Sitios.xml";
    View view=null;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TourismDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static TourismDetail newInstance(String param1, String param2) {
        TourismDetail fragment = new TourismDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }
    public TourismDetail() {
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

        mParam1= getArguments().getString("Type_site");

        view = inflater.inflate(R.layout.fragment_tourism_detail, container, false);
        SitiosList = (ListView)view.findViewById(R.id.SiteLists);

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        context = view.getContext();
        UtilsFiles files= new UtilsFiles(view.getContext(),filename);
        files.CopyFile();
        InputStream filen=null;
        try {
            filen = view.getContext().getAssets().open("files/"+filename);
        }catch(IOException ioe){
            System.out.printf("Open File Error %s", ioe.toString());
        }
        Tou_Sites = new TouristSites();
        try {
            Tou_Sites.parse(filen);
        }catch(Exception e){
            System.out.printf("Open File Error %s", e.toString());
        }

        if(mParam1.equals("North")){
            Sites = Tou_Sites.GetSitesbySector(1);
        }else if(mParam1.equals("DownTown")) {
            Sites = Tou_Sites.GetSitesbySector(2);
        }else if(mParam1.equals("South")) {
            Sites = Tou_Sites.GetSitesbySector(3);
        }else if(mParam1.equals("Mountain")) {
            Sites = Tou_Sites.GetSitesbySector(4);
        }else if(mParam1.equals("Map")) {
            Sites = Tou_Sites.GetSitesbySector(5);
        }

        CustomListAdapter lista =new CustomListAdapter(context,R.layout.fragment_tourism_detail,Sites);
        SitiosList.setAdapter(lista);
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

}
