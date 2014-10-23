package danna.net.gadapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by javier on 10/20/2014.
 */
public class CustomListAdapterNews extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<NewsClass> data = new ArrayList<NewsClass>();

    public CustomListAdapterNews(Context context, int layoutResourceId,
                             ArrayList<NewsClass> data) {

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
    public NewsClass getItem(int position) {
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
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(R.layout.newsrow, null);
            holder = new ViewHolder();
              holder.imageTitle = (TextView) row.findViewById(R.id.lblTitlen);
            holder.Desc = (EditText) row.findViewById(R.id.txtNews);
            holder.image = (ImageView) row.findViewById(R.id.imgMaps);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        NewsClass item = new NewsClass();
        item= data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.Desc.setText(item.getDetails());
        holder.Desc.setBackgroundDrawable(null);
        int path= getContext().getResources().getIdentifier("danna.net.gadapp:drawable/"+item.getFileName(), null, null);
        holder.image.setImageResource(path);
        return row;
    }

    public static class ViewHolder {
        //title
        TextView imageTitle;
        //Description
        EditText Desc;
        //Ubicacion
        ImageView image;
    }
}
