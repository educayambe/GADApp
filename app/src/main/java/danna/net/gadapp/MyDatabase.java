package danna.net.gadapp;

/**
 * Created by javier on 10/5/2014.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;


public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "EduCayambe.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }



    public Cursor getCuenta(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"correo", "contrasena"};
        String [] args = {name};
        String sqlTables = "cuenta";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, "correo=?", args,
                null, null, null);

        c.moveToFirst();
        return c;

    }
    public ArrayList<Personaje> getPersonas() {
        ArrayList <Personaje> Personas = new ArrayList <Personaje>() ;
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"title", "normal_image","status"};
        String sqlTables = "Content";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null,"status DESC");

        c.moveToFirst();
        try{
            do{
                Personaje persona = new Personaje();
                persona.setNombre(c.getString(0));
                persona.setImage(c.getBlob(1));
                try {
                    Personas.add(persona);
                }
                catch (Exception e){
                    System.out.print("Fallo la carga de la lista");
                    }


            }while(c.moveToNext());

        }catch(Exception ioe) {
            System.out.print(ioe.toString());}
        c.close();
        return Personas;

    }


    public Personaje getPersona(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"content", "normal_image"};
        String [] args = {name};
        String sqlTables = "Content";
        Personaje persona = new Personaje();

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, "title=?", args,
                null, null, null);

        c.moveToFirst();
        try{
            do{

                persona.setNombre(c.getString(0));
                persona.setImage(c.getBlob(1));
                try {

                    persona.setNombre(c.getString(0));
                    persona.setImage(c.getBlob(1));
                }
                catch (Exception e){
                    System.out.print("Fallo la carga de la lista");
                }


            }while(c.moveToNext());

        }catch(Exception ioe) {
            System.out.print(ioe.toString());}
        c.close();
        return persona;

    }

    public boolean insertCuenta(String... args){


        SQLiteDatabase db = getWritableDatabase();

        String query = "Insert into cuenta(correo,contrasena,nombres,cedula,sector, school) values('";
        query = query +args[0]+"','" ;
        query = query +args[1]+"','" ;
        query = query +args[2]+"','" ;
        query = query +args[3]+"','" ;
        query = query +args[4]+"','" ;
        query = query +args[5]+"')" ;

        try {
            db.execSQL(query);
            db.close();
        }
        catch(Exception e){
            System.out.print(e.toString());
        }

    return true;
    }
    public Cursor getSchools() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"Nombre"};

        String sqlTables = "schools";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToFirst();
        return c;

    }

}