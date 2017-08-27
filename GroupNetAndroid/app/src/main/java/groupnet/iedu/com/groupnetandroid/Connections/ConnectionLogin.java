package groupnet.iedu.com.groupnetandroid.Connections;

import android.content.Context;
import android.os.AsyncTask;

import com.iedu.client.GroupClient;
import com.iedu.client.UserClient;
import com.iedu.domain.Group;
import com.iedu.domain.Signal;
import com.iedu.domain.User;

import java.util.List;

import groupnet.iedu.com.groupnetandroid.LoginPageActivity;
import groupnet.iedu.com.groupnetandroid.samples.components.SampleActivity;

/**
 * Created by mhlee on 7/21/17.
 */

public class ConnectionLogin extends AsyncTask {

    private LoginPageActivity context;
    int returnCode;

    public ConnectionLogin(Context context){
        this.context = (LoginPageActivity)context;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            User user = new User();
            user.setName((String)arg0[0]); // user name
            user.setPassword((String)arg0[1]); // user password
            returnCode = UserClient.login(user);


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
        if( returnCode == Signal.LOGIN_FAIL) {
            context.loginFail();
        }
        else{
            context.loginSuccess(returnCode);

        }


    }
}