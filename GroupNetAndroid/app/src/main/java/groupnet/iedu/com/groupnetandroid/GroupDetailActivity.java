package groupnet.iedu.com.groupnetandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GroupDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
    }

    public void Join (View view){
        Toast.makeText(this, "Group Joined", Toast.LENGTH_LONG).show();
    }
}
