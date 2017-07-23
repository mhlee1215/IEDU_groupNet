package groupnet.iedu.com.groupnetandroid.samples.components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.iedu.client.*;
import com.iedu.domain.Group;

import java.util.List;

import groupnet.iedu.com.groupnetandroid.R;
import groupnet.iedu.com.groupnetandroid.samples.connection.ConnectionSample;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_example);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.example_spinner_array_item, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void readGroupInfo(View view){
        System.out.println("Run Connection!");
        ConnectionSample cs = new ConnectionSample(this);
        //This will call doInBackground() function
        cs.execute("param1", "param2", "param3");
        //System.out.println("ggggg"+groupList);
    }

    public void onLoadGroup(List<Group> groupList){
        System.out.println("ggggg"+groupList);
    }

}
