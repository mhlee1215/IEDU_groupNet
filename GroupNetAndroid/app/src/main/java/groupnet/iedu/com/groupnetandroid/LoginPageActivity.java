package groupnet.iedu.com.groupnetandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionLogin;
import groupnet.iedu.com.groupnetandroid.samples.components.SampleActivity;

public class LoginPageActivity extends AppCompatActivity {
    AVLoadingIndicatorView loadingMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        loadingMark = (AVLoadingIndicatorView)findViewById(R.id.avloadingIndicatorView);
    }
    //public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    public void loginButton (View view) {

        EditText userId = (EditText)findViewById(R.id.user_id);
        System.out.println(userId.getText());

        EditText userPassword = (EditText)findViewById(R.id.user_password);
        System.out.println(userPassword.getText());

        showLoading(true);
        ConnectionLogin cl = new ConnectionLogin(this);
        cl.execute(userId.getText().toString(), userPassword.getText().toString());
    }

    public void showLoading(boolean visible){
        if(visible)
            loadingMark.setVisibility(View.VISIBLE);
        else
            loadingMark.setVisibility(View.GONE);
    }

    public void loginSuccess(int returnCode){
        System.out.println("success");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("USER_ID", returnCode);
        editor.commit();


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loginFail(){
        System.out.println("fail");
        Toast.makeText(this, "login failed", Toast.LENGTH_LONG).show();
    }
}
