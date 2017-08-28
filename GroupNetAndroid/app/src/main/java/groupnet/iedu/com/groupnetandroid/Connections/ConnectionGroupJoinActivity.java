package groupnet.iedu.com.groupnetandroid.Connections;

import android.os.AsyncTask;
import android.view.View;

import com.iedu.client.MembershipClient;

import groupnet.iedu.com.groupnetandroid.GroupDetailActivity;
import groupnet.iedu.com.groupnetandroid.HomeFragment;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionGroupJoinActivity extends AsyncTask {

    private GroupDetailActivity context;
    //int returnCode;
    //List<Group> groupList = null;
    View view;

    public ConnectionGroupJoinActivity(View view, GroupDetailActivity fragment){
        this.context = (GroupDetailActivity)fragment;
        this.view = view;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            if("join".equalsIgnoreCase((String)arg0[2]))
                MembershipClient.Groupjoin(arg0[0]+"", arg0[1]+"");
            else if("leave".equalsIgnoreCase((String)arg0[2]))
                MembershipClient.Groupleave(arg0[0]+"", arg0[1]+"");

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        //context.postGroupJoin();
        //If login success
//        if( groupList != null) {
//            context.initDemoList(view, groupList);
//           // context.loginFail();
//        }


    }
}