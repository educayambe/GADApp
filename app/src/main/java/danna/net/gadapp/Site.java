package danna.net.gadapp;

import android.app.Service;

/**
 * Created by javier on 10/18/2014.
 */
public  class Site {
    public final String Name;
    public final String Desc;
    public final String Location;
    public final String How;
    public final String[] Services;
    public final String[] Contacts;
    public final String Image_name;
    public final Integer Sector;
    public Site(String name, String desc, String location,String how,String[] services,String[] contacts, String image_name,Integer sector) {
        this.Name = name;
        this.Desc = desc;
        this.Location = location;
        this.How = how;
        this.Services = services;
        this.Contacts = contacts;
        this.Image_name = image_name;
        this.Sector = sector;
    }
    public Site(){
        this.Name = "";
        this.Desc = "";
        this.Location ="";
        this.How = "";
        this.Services =null;
        this.Contacts = null;
        this.Image_name = "";
        this.Sector = 0;
    }
    public String getName(){
        return Name;
    }
    public String getLocation(){
        return Location;
    }
    public String getDesc(){
        return Desc;
    }
    public String getHow(){
        return How;
    }
    public String[] getServices(){
        return Services;
    }
    public String[] getContacts(){
        return Contacts;
    }
    public Integer getSector(){
        return Sector;
    }
    public String getImage_name(){
        return Image_name;
    }
}

