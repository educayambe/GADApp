package danna.net.gadapp;

import java.util.ArrayList;

/**
 * Created by javier on 10/21/2014.
 */
public class AppsClass {
    private String NameApp;
    private Integer NumChild;
    private ArrayList<AppsClassChild> childs;
     public AppsClass(){
         NameApp="";
         childs =new ArrayList<AppsClassChild>();
     }
    public AppsClass(String Name,AppsClassChild child){
        this.NameApp = Name;
        this.childs.add(child);

    }
    public void setNameApp(String nameApp) {
        NameApp = nameApp;
    }

    public void setNumChild(Integer numChild) {
        NumChild = numChild;
    }

    public void setChilds(AppsClassChild child) {
        this.childs.add(child);
    }
    public int getSizeChild(){
        return childs.size();
    }
    public ArrayList<AppsClassChild> getChild(){
        return childs;
    }
    public String getNameApp(){
        return NameApp;
    }

}
