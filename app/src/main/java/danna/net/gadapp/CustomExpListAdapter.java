package danna.net.gadapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by javier on 10/21/2014.
 */
public class CustomExpListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<AppsClass> data = new ArrayList<AppsClass>();

    public  CustomExpListAdapter( ArrayList<AppsClass> data) {

        this.layoutResourceId=layoutResourceId;
        this.context=context;
        this.data=data;
   }



    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.childrow, parent, false);
        }

        TextView itemName = (TextView) v.findViewById(R.id.lblAppName);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        ImageButton button=(ImageButton)v.findViewById(R.id.btndownload);
        image.setImageResource(R.drawable.aplicaciones);

        AppsClassChild det = data.get(groupPosition).getChild(childPosition);

        itemName.setText(det.getName());
        button.setTag(det.getUrl().toString());
        return v;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listrow_group, parent, false);
        }

        TextView groupName = (TextView) v.findViewById(R.id.lblCategory);
        ImageView groupimg = (ImageView) v.findViewById(R.id.imagecat);


        AppsClass cat = data.get(groupPosition);

        groupName.setText(cat.getNameApp());
        groupimg.setImageResource(R.drawable.carpetas);

        return v;

    }

}
