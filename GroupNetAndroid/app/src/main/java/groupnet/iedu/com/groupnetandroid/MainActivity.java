package groupnet.iedu.com.groupnetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iedu.client.*;
import com.iedu.domain.Group;

import groupnet.iedu.com.groupnetandroid.samples.connection.ConnectionSample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readGroupInfo(View view){
        System.out.println("Run Connection!");
        ConnectionSample cs = new ConnectionSample();
        //This will call doInBackground() function
        cs.execute("param1", "param2", "param3");
    }

}
