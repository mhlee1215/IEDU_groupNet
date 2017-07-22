package groupnet.iedu.com.groupnetandroid.samples.connection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;

import com.iedu.client.GroupClient;
import com.iedu.domain.Group;

import java.util.List;

import groupnet.iedu.com.groupnetandroid.Utils.Logger;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionSample extends AsyncTask {

    public ConnectionSample(){
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {
        try {
            //Parameter receiving example
            for(int i = 0 ; i < arg0.length ; i++)
                System.out.println((String)arg0[i]);

            //ClientCall example
            List<Group> groupList = GroupClient.readGroups();
            System.out.println("Group Number:"+groupList);


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        //If any... you have thing to do after work

    }
}