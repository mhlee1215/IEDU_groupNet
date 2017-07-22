package groupnet.iedu.com.groupnetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import groupnet.iedu.com.groupnetandroid.samples.connection.ConnectionSample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View view) {
        System.out.print("logged in");
    }

    public void createAccount(View view) {
        System.out.print("creating account");
    }

    public void profileOne(View view) {
        System.out.print("profile page 1");
    }

    Intent intent = getIntent();
    //String message = intent.getStringExtra(LoginPageActivity.EXTRA_MESSAGE);
}
