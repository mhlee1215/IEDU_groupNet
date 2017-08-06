package groupnet.iedu.com.groupnetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionLogin;
import groupnet.iedu.com.groupnetandroid.samples.components.SampleActivity;

public class LoginPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }
    //public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    public void loginButton (View view) {

        EditText userId = (EditText)findViewById(R.id.user_id);
        System.out.println(userId.getText());

        EditText userPassword = (EditText)findViewById(R.id.user_password);
        System.out.println(userPassword.getText());

        ConnectionLogin cl = new ConnectionLogin(this);

        cl.execute("user", "password");


        Intent intent = new Intent(this, SampleActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void loginSuccess(){
        System.out.println("success");
    }

    public void loginFail(){
        System.out.println("fail");
    }
}
