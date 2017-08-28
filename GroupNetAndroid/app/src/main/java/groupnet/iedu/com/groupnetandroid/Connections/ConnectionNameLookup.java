package groupnet.iedu.com.groupnetandroid.Connections;

import android.content.Context;
import android.os.AsyncTask;

import com.iedu.client.UserClient;
import com.iedu.domain.Signal;
import com.iedu.domain.User;

import groupnet.iedu.com.groupnetandroid.CreateAccountActivity;
import groupnet.iedu.com.groupnetandroid.LoginPageActivity;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionNameLookup extends AsyncTask {

    private CreateAccountActivity context;
    int returnCode;

    public ConnectionNameLookup(Context context){
        this.context = (CreateAccountActivity)context;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            User user = new User();
            user.setName((String)arg0[0]); // user name
            returnCode = UserClient.userNameLookup(user);


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        context.showLoading(false);
        //If login success
        if( returnCode == 1) {
            context.setNameUnique(false);
        }
        else{
            context.setNameUnique(true);

        }


    }
}