package groupnet.iedu.com.groupnetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionLogin;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionSignup;
import groupnet.iedu.com.groupnetandroid.samples.components.SampleActivity;

public class CreateAccountActivity extends AppCompatActivity {

    AVLoadingIndicatorView loadingMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        loadingMark = (AVLoadingIndicatorView)findViewById(R.id.avloadingIndicatorView);
    }
    public void nextButton (View view) {

        EditText userId = (EditText)findViewById(R.id.user_id);
        System.out.println(userId.getText());

        EditText userPassword = (EditText)findViewById(R.id.user_password);
        System.out.println(userPassword.getText());

        showLoading(true);
        ConnectionSignup cl = new ConnectionSignup(this);
        cl.execute(userId.getText().toString(), userPassword.getText().toString());


    }

    public void showLoading(boolean visible){
        if(visible)
            loadingMark.setVisibility(View.VISIBLE);
        else
            loadingMark.setVisibility(View.GONE);
    }

    public void signupFail(){
        Toast.makeText(this, "signup failed", Toast.LENGTH_LONG).show();
    }

    public void signupSuccess(String returnCode){
        Toast.makeText(this, "signup success", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginPageActivity.class);
        startActivity(intent);
    }
}
