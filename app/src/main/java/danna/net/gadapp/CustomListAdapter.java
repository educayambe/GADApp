package danna.net.gadapp;

/**
 * Created by javier on 10/18/2014.
 */

        import android.app.Activity;
        import android.app.Fragment;
        import android.graphics.drawable.Drawable;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;



public class CustomListAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Site> data = new ArrayList<Site>();

    public CustomListAdapter(Context context, int layoutResourceId,
                         ArrayList<Site> data) {

        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Site getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder ;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.listrow_details, null);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.lblTitle);
            holder.Desc = (EditText) row.findViewById(R.id.lbldesc);
            holder.Location= (EditText) row.findViewById(R.id.lblLocation);
            holder.How= (EditText) row.findViewById(R.id.lblHow);
            holder.Services =(EditText) row.findViewById(R.id.lblServices);
            holder.Contacts = (EditText) row.findViewById(R.id.lblContacts);
            holder.image = (ImageView) row.findViewById(R.id.imgsite);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Site item = new Site();
        item= data.get(position);
        holder.imageTitle.setText(item.getName());
        holder.Desc.setText(item.getDesc());
        holder.Desc.setBackgroundDrawable(null);
        holder.Location.setText(item.getLocation());
        holder.Location.setBackgroundDrawable(null);
        holder.How.setText(item.getHow());
        holder.How.setBackgroundDrawable(null);

        String content="" ;
        for (int i=0;i<item.getServices().length;i++){
            content= content + "--";
            content= content +item.getServices()[i].toString();
            content= content +"\n";
        }

        holder.Services.setText(content);
        holder.Services.setBackgroundDrawable(null);
        content="" ;
        for (int i=0;i<item.getContacts().length;i++){
            content= content + "--";
            content= content +item.getContacts()[i].toString();
            content= content +"\n";
        }

        holder.Contacts.setText(content);
        holder.Contacts.setBackgroundDrawable(null);
        int path= getContext().getResources().getIdentifier("danna.net.gadapp:drawable/"+item.getImage_name(), null, null);


        holder.image.setImageResource(path);
        return row;
    }

    public static class ViewHolder {
        //title
        TextView imageTitle;
        //Description
        EditText Desc;
        //Ubicacion
        EditText Location;
        //How
        EditText How;
        //Service
        EditText Services;
        //Contacts
        EditText Contacts;

        ImageView image;
    }
}