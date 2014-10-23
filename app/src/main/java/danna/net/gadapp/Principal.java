package danna.net.gadapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;


import java.util.List;


public class Principal extends TabActivity  {

    FragmentManager fm ;
    TabHost tht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        fm = this.getFragmentManager();
       tht = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec spec = tht.newTabSpec("Tab1");
        spec.setContent(new Intent(this,News.class));
        spec.setIndicator("", getResources().getDrawable(R.drawable.noticias));

        tht.addTab(spec);

        TabHost.TabSpec spec1 = tht.newTabSpec("Tab2");
        spec1.setContent(new Intent(this,Biography.class));
        spec1.setIndicator("", getResources().getDrawable(R.drawable.biografias));

        tht.addTab(spec1);

        TabHost.TabSpec spec2 = tht.newTabSpec("Tab3");
        spec2.setContent(new Intent(this,EagleEyes.class));
        spec2.setIndicator("",getResources().getDrawable(R.drawable.comunicacion_digital));
        tht.addTab(spec2);

        TabHost.TabSpec spec3 = tht.newTabSpec("Tab4");
        spec3.setContent(new Intent(this,Tourism.class));
        spec3.setIndicator("",getResources().getDrawable(R.drawable.turismo_cayambe));
        tht.addTab(spec3);

        TabHost.TabSpec spec4 = tht.newTabSpec("Tab5");
        spec4.setContent(new Intent(this,Download.class));
        spec4.setIndicator("",getResources().getDrawable(R.drawable.descargas));
        tht.addTab(spec4);

        TabHost.TabSpec spec5 = tht.newTabSpec("Tab6");
        spec5.setContent(new Intent(this,Dictionary.class));
        spec5.setIndicator("",getResources().getDrawable(R.drawable.diccionario));
        tht.addTab(spec5);
        for (int i=0;i < tht.getTabWidget().getChildCount();i++){
            tht.getTabWidget().getChildAt(i).setPadding(0,0,0,0);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.about) {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent appInitIntent = new
                            Intent(Principal.this,About.class);
                    startActivity(appInitIntent);
                    onStop();
                }
            }, 500);

        }
        if (id == R.id.Salir) {

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
