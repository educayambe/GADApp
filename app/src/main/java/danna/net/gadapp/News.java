package danna.net.gadapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class News extends Activity {
    private Context context=null;
    private ListView News_list;
    private XMLParseNews NewsList;
    private ArrayList<NewsClass> ListaNoticias = new ArrayList<NewsClass>();
    String filename="Noticias.xml";
    View view=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        News_list = (ListView)findViewById(R.id.LstNews);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news, menu);

        return true;
    }
    @Override
    public void onStart(){
        super.onStart();
        context = getApplicationContext();
        UtilsFiles files= new UtilsFiles(context,filename);
        files.CopyFile();
        InputStream filen=null;
        try {
            filen = context.getAssets().open("files/"+filename);
        }catch(IOException ioe){
            System.out.printf("Open File Error %s", ioe.toString());
        }
        NewsList = new XMLParseNews();
        try {
            NewsList.parse(filen);
        }catch(Exception e){
            System.out.printf("Open File Error %s", e.toString());
        }



        CustomListAdapterNews lista = new CustomListAdapterNews(context,R.layout.activity_news,NewsList.entries);
        News_list.setAdapter(lista);
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
        return super.onOptionsItemSelected(item);
    }
}
