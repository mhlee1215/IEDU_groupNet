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
import groupnet.iedu.com.groupnetandroid.MainFragment;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionGroup extends AsyncTask {

    private MainFragment context;
    //int returnCode;
    List<Group> groupList = null;
    View view;

    public ConnectionGroup(View view, Fragment fragment){
        this.context = (MainFragment)fragment;
        this.view = view;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            Group g = new Group();
            g.setStatus("active");

            if(arg0.length > 0)
                g.setViewerId((int)arg0[0]);
            if(arg0.length > 1)
                g.setAccess((String)arg0[1]);
            if(arg0.length > 2)
                g.setOwnerId((int)arg0[2]);
            if(arg0.length > 3)
                g.setKeyword((String)arg0[3]);

            groupList = GroupClient.readGroups(g);

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
            context.postExecute(view, groupList);
           // context.loginFail();
        }


    }
}