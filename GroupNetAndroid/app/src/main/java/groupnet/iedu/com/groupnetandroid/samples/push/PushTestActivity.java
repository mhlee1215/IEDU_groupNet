package groupnet.iedu.com.groupnetandroid.samples.push;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import groupnet.iedu.com.groupnetandroid.R;

public class PushTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_test);

        FirebaseInstanceId.getInstance().getToken();

        String token = FirebaseInstanceId.getInstance().getToken();
        //Log.d("FCM_Token", token);
        System.out.println("token:"+token);

    }
}
