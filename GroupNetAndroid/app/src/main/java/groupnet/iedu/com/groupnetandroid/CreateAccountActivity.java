package groupnet.iedu.com.groupnetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionLogin;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionNameLookup;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionSignup;
import groupnet.iedu.com.groupnetandroid.samples.components.SampleActivity;

public class CreateAccountActivity extends AppCompatActivity {
    CreateAccountActivity context;
    AVLoadingIndicatorView loadingMark;
    EditText userId;
    boolean nameUniqueChecked = false;
    Button buttonCreate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        context = this;
        loadingMark = (AVLoadingIndicatorView)findViewById(R.id.avloadingIndicatorView);

        userId = (EditText)findViewById(R.id.user_id);

        userId.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                ConnectionNameLookup cl = new ConnectionNameLookup(context);
                cl.execute(userId.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        buttonCreate = (Button)findViewById(R.id.button_create_account);
        buttonCreate.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                nextButton();
            }});
        buttonCreate.setEnabled(false);
    }

    public void setNameUnique(boolean isUnique){
        nameUniqueChecked = isUnique;

        if(nameUniqueChecked){
            buttonCreate.setEnabled(true);
        }else{
            buttonCreate.setEnabled(false);
        }
    }

    public void nextButton () {

        //EditText userId = (EditText)findViewById(R.id.user_id);
        System.out.println(userId.getText());

        EditText userPassword = (EditText)findViewById(R.id.user_password);
        System.out.println(userPassword.getText());

        EditText userEmail = (EditText)findViewById(R.id.user_email);
        System.out.println(userEmail.getText());

        showLoading(true);
        ConnectionSignup cl = new ConnectionSignup(this);
        cl.execute(userId.getText().toString(), userPassword.getText().toString(), userEmail.getText().toString());


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
