package danna.net.gadapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class Register extends Activity {
    EditText correo ;
    EditText correoConfirm ;
    EditText names ;
    EditText cedula ;
    EditText password ;
    EditText passwordConfirm ;
    Spinner school ;
    EditText sector ;



    private MyDatabase database;
    private String array_spinner[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        correo = (EditText)findViewById(R.id.txtcorreo);
        correoConfirm = (EditText)findViewById(R.id.txtCorreoConfirm);
        names = (EditText)findViewById(R.id.txtPersonName);
        cedula = (EditText)findViewById(R.id.txtCedula);
        password = (EditText)findViewById(R.id.txtpassword);
        passwordConfirm = (EditText) findViewById(R.id.txtConfirmPassword);
        sector = (EditText)findViewById(R.id.txtPhones);
        school = (Spinner)findViewById(R.id.spinner);

        load_schools();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,array_spinner);//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        school.setAdapter(adapter);




    }
   public void onClick(View view){

    switch(view.getId()){

        case R.id.ibtnRegister: {
            switch (Valida_componentes()) {
                case 1: {
                    Toast.makeText(getApplicationContext(), "Existen campos vacíos por favor completelos y Vuelva a Intentarlo",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                case 2: {
                    Toast.makeText(getApplicationContext(), "Las direcciones de correo no coinciden,por favor corrija y vuelva a intentarlo",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                case 3: {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden, por favor corrija y vuelva a intentarlo",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                case 4: {
                    Toast.makeText(getApplicationContext(), "La dirección de correo es inválida, por favor corrija y vuelva a intentarlo",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                case 0: {
                    new PostTask().execute(correo.getText().toString(), password.getText().toString(),
                            names.getText().toString(), cedula.getText().toString(), sector.getText().toString(), school.getSelectedItem().toString());
                }
                break;
            }
        }break;
        case R.id.ibtncancel:{
                finish();
            }break;
       }
   }





    public void load_schools(){
        try {
            MyDatabase base = new MyDatabase(getApplicationContext());
            Integer count =0;
            Cursor cursor = base.getSchools();
            cursor.moveToFirst();
            if (cursor != null){
                array_spinner=new String[cursor.getCount()];
                do{
                    array_spinner[count]=cursor.getString(0);
                    count++;
                }while (cursor.moveToNext());

            }
        }
        catch (Exception e){
            System.out.print("Error al abrir la base de datos\n");
        }


    }

    public Integer Valida_componentes(){
      Integer valido = 0;
      if(correo.getText().toString().equals("") || correoConfirm.getText().toString().equals("")|| names.getText().toString().equals("") ||
              password.getText().toString().equals("") || passwordConfirm.getText().toString().equals("")
              || sector.getText().toString().equals("")  || cedula.getText().toString().equals("")
              ) {
          valido = 1;
          return valido;
      }

        if (!correo.getText().toString().equals(correoConfirm.getText().toString())) {
            valido = 2;
            return valido;
        }

        if(!isValidEmailAddress(correo.getText().toString())){
            valido=4;
            return valido;
        }

        if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
            valido = 3;
            return valido;
        }



        return valido;
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    private final class PostTask extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... args) {
            Cursor cursor=null;
            Boolean valido = false;
            // run only once... ?
            // copy assets DB to app DB.
            try {

                database = new MyDatabase(getApplicationContext());
                cursor = database.getCuenta(args[0]);
                cursor.moveToFirst();
                if (cursor.getCount()<=0){
                     database.insertCuenta(args);
                    valido=true;
                }
                else
                {
                    valido=false;
                }

                database.close();
            } catch (Exception ioe) {
                System.out.print(ioe.toString());
            }

            return valido;
        }
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(), "Creando cuenta...",
                        Toast.LENGTH_SHORT).show();
                finish();

            }
            else
                Toast.makeText(getApplicationContext(), "La cuenta ya existe. Corrija la información y Vuelva a Intentarlo'",
                        Toast.LENGTH_SHORT).show();

        }
    }


@Override
   public void onDestroy(){
    super.onDestroy();

   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
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
