package danna.net.gadapp;

/**
 * Created by javier on 10/5/2014.
 */
public class Personaje {
   private String nombre_personaje;
   private byte[] image;

    public Personaje(){
           }


    public byte[] getImage(){
        return image;
    }
    public String getNombre(){
        return nombre_personaje;
    }
    public void setNombre(String nombre_personaje){
        this.nombre_personaje = nombre_personaje;

    }
    public void setImage(byte[]image){
        this.image = image;
    }

}
