package danna.net.gadapp;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by javier on 10/19/2014.
 */
public class UtilsFiles {
    private Context context=null;
    private String filename="";
    public UtilsFiles(Context context,String filename){
        this.context=context;
        this.filename=filename;

    }
 public void CopyFile() {
     String DestinationFile = context.getFilesDir().getPath() + File.separator + filename.toString();

     if (!new File(DestinationFile).exists()) {
         try {
             CopyFromAssetsToStorage(context, "files/"+filename.toString(), DestinationFile);
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }
 }
    public void CopyFile(String DestinationFile) {

        //String DestinationFile = context.getFilesDir().getPath() + File.separator + filename.toString();

        if (!new File(DestinationFile).exists()) {
            try {
                CopyFromAssetsToStorage(context, "files/"+filename.toString(), DestinationFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    private void CopyFromAssetsToStorage(Context Context, String SourceFile, String DestinationFile) throws IOException {
        InputStream IS = Context.getAssets().open(SourceFile);
        OutputStream OS = new FileOutputStream(DestinationFile);
        CopyStream(IS, OS);
        OS.flush();
        OS.close();
        IS.close();
    }

    private void CopyStream(InputStream Input, OutputStream Output) throws IOException {
        byte[] buffer = new byte[5120];
        int length = Input.read(buffer);
        while (length > 0) {
            Output.write(buffer, 0, length);
            length = Input.read(buffer);
        }
    }
}
