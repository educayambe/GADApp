package danna.net.gadapp;

/**
 * Created by javier on 10/20/2014.
 */
public class NewsClass {
    String Title;
    String Details;
    String FileName;
    NewsClass(String title, String details,String filename){
        this.Title=title;
        this.Details = details;
        this.FileName = filename;
    }
    NewsClass(){}
    public void setTitle(String title){
        this.Title = title;
    }
    public  void setDetails(String details){
        this.Details = details;
    }
    public  void setFileName(String fileName){
        this.FileName = fileName;
    }
    public String getTitle(){
        return Title;
    }
    public String getDetails(){
        return Details;
    }
    public String getFileName(){
        return FileName;
    }
}
