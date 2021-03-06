package groupnet.iedu.com.groupnetandroid.Connections;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;

import com.iedu.client.GroupClient;
import com.iedu.domain.Group;

import java.util.List;

import groupnet.iedu.com.groupnetandroid.AddFragment;
import groupnet.iedu.com.groupnetandroid.GroupDetailActivity;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionGroupDelete extends AsyncTask {

    private GroupDetailActivity context;
    //int returnCode;
    List<Group> groupList = null;
    View view;

    public ConnectionGroupDelete(View view, GroupDetailActivity context){
        this.context = (GroupDetailActivity)context;
        this.view = view;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            System.out.println(" delete group called with "+arg0[0]);
            GroupClient.deleteGroup((int)arg0[0]);


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        context.postDeleteGroup();

        //If login success
//        if( groupList != null) {
//            context.initDemoList(view, groupList);
//           // context.loginFail();
//        }


    }
}