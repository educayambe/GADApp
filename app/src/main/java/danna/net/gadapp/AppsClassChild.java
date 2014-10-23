package danna.net.gadapp;

/**
 * Created by javier on 10/21/2014.
 */
public class AppsClassChild {
    private String Name;
    private String url;
     AppsClassChild(String Name,String url){
         this.Name=Name;
         this.url = url;
     }

    AppsClassChild(){}

    public void setName(String Name){
        this.Name = Name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getName(){
        return Name;
    }

    public String getUrl() {
        return url;
    }
}
