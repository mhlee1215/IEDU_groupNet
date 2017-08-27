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

public class ConnectionSignup extends AsyncTask {

    private CreateAccountActivity context;
    String returnCode;

    public ConnectionSignup(Context context){
        this.context = (CreateAccountActivity)context;
    }

    //This function will be called after call execution()
    @Override
    protected Object doInBackground(Object... arg0) {

        try {
            User user = new User();
            user.setName((String)arg0[0]); // user name
            user.setPassword((String)arg0[1]); // user password
            returnCode = UserClient.signup(user);


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
        if("fail".equals(returnCode)) {
            context.signupFail();
        }
        else{
            context.signupSuccess(returnCode);

        }


    }
}