package groupnet.iedu.com.groupnetandroid.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import groupnet.iedu.com.groupnetandroid.samples.imageLoad.ImageLoadActivity;

public class FileFromBitmap extends AsyncTask<Void, Integer, String> {
    File file;
    Context context;
    Bitmap bitmap;
    //String path_external = Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg";

    public FileFromBitmap(Bitmap bitmap, Context context) {
        this.bitmap = bitmap;
        this.context= context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // before executing doInBackground
        // update your UI
        // exp; make progressbar visible
    }

    @Override
    protected String doInBackground(Void... params) {

        System.out.println("bitmap??? : "+bitmap);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        file = new File(context.getCacheDir(), "tmp.jpg");


        try {
            file.createNewFile();

            //Bitmap bitmap = your bitmap;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();

            System.out.println("file size?"+file);

//            FileOutputStream fo = new FileOutputStream(file);
//            fo.write(bytes.toByteArray());
//            fo.flush();
//            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // back to main thread after finishing doInBackground
        // update your UI or take action after
        // exp; make progressbar gone

        if(file != null)
            ((ImageLoadActivity)context).uploadFile(file);
         //sendFile(file);

    }
}