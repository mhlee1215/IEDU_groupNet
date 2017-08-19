package groupnet.iedu.com.groupnetandroid.Connections;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.iedu.client.MediaClient;

import java.io.File;

import groupnet.iedu.com.groupnetandroid.AddFragment;
import groupnet.iedu.com.groupnetandroid.samples.imageLoad.ImageLoadActivity;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionFileUploadFragment extends AsyncTask {

    private AddFragment context;
    String returnID = "";

    public ConnectionFileUploadFragment(Fragment context){
        this.context = (AddFragment)context;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {

            File file = (File)arg0[0];
            System.out.println(">>>>>"+file);
            returnID = MediaClient.fileUpload("http://52.34.169.106:8080/GroupNetWeb/mediaUpload2.do", file, "text...");
            System.out.println("uploaded id :"+returnID);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        context.uploadFinished(returnID);

    }
}