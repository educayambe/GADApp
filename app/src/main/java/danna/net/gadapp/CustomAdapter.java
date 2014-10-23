package danna.net.gadapp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Personaje> data = new ArrayList<Personaje>();

    public CustomAdapter(Context context, int layoutResourceId,
                           ArrayList<Personaje> data) {

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
    public Personaje getItem(int position) {
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
            row = inflater.inflate(R.layout.program_list, null);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.image = (ImageView) row.findViewById(R.id.imgPersona);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Personaje item = data.get(position);
       holder.imageTitle.setText(item.getNombre());
        Bitmap img = BitmapFactory.decodeByteArray(item.getImage(),0,item.getImage().length);
        holder.image.setImageBitmap(img);
        return row;
    }

    public static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}