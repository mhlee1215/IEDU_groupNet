package groupnet.iedu.com.groupnetandroid.Connections;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;

import com.iedu.client.GroupClient;
import com.iedu.client.UserClient;
import com.iedu.domain.Group;
import com.iedu.domain.Signal;
import com.iedu.domain.User;

import java.util.List;

import groupnet.iedu.com.groupnetandroid.HomeFragment;
import groupnet.iedu.com.groupnetandroid.LoginPageActivity;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionGroup extends AsyncTask {

    private HomeFragment context;
    //int returnCode;
    List<Group> groupList = null;
    View view;

    public ConnectionGroup(View view, Fragment fragment){
        this.context = (HomeFragment)fragment;
        this.view = view;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            groupList = GroupClient.readGroups();


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        //If login success
        if( groupList != null) {
            context.initDemoList(view, groupList);
           // context.loginFail();
        }


    }
}