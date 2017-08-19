package groupnet.iedu.com.groupnetandroid.Connections;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;

import com.iedu.client.GroupClient;
import com.iedu.domain.Group;

import java.util.List;

import groupnet.iedu.com.groupnetandroid.AddFragment;
import groupnet.iedu.com.groupnetandroid.HomeFragment;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionGroupAdd extends AsyncTask {

    private AddFragment context;
    //int returnCode;
    List<Group> groupList = null;
    View view;

    public ConnectionGroupAdd(View view, Fragment fragment){
        this.context = (AddFragment)fragment;
        this.view = view;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            System.out.println(" add group called with "+(String)arg0[0]+", "+(String)arg0[1]+", "+(String)arg0[2]);
            GroupClient.addGroup((String)arg0[0], (String)arg0[1], (String)arg0[2]);


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        context.postAddGroup();

        //If login success
//        if( groupList != null) {
//            context.initDemoList(view, groupList);
//           // context.loginFail();
//        }


    }
}