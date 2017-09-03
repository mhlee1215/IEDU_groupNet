package groupnet.iedu.com.groupnetandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroupDelete;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroupJoin;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroupJoinActivity;

public class GroupDetailActivity extends AppCompatActivity {

    GroupDetailActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        context = this;

//        intent.putExtra("ownerName", mDataset.get(position).getOwnerName());
//        intent.putExtra("groupName", mDataset.get(position).getName());
//        intent.putExtra("descriptor", mDataset.get(position).getDescription());
//        intent.putExtra("isJoin", mDataset.get(position).getIsJoin());
//        intent.putExtra("isFavorite", mDataset.get(position).getIsFavorite());

        //Receiving title text
        int userId = getIntent().getIntExtra("userId", 0);
        int groupId = getIntent().getIntExtra("groupId", 0);
        int ownerId = getIntent().getIntExtra("ownerId", 0);
        String groupName = getIntent().getStringExtra("groupName");
        String ownerName = getIntent().getStringExtra("ownerName");
        String descriptor = getIntent().getStringExtra("descriptor");
        String isJoin = getIntent().getStringExtra("isJoin");
        String isFavorite = getIntent().getStringExtra("isFavorite");



        //Set to activity
        TextView groupNameTextView = (TextView) findViewById(R.id.group_name);
        groupNameTextView.setText(groupName);

        TextView ownerNameTextView = (TextView) findViewById(R.id.owner_name);
        ownerNameTextView.setText(ownerName);

        TextView descriptorTextView = (TextView) findViewById(R.id.group_desc);
        descriptorTextView.setText(descriptor);

//        //Receiving image byte
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        //Convert from byte to bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView imageView = (ImageView) findViewById(R.id.image_viewer);
        imageView.setImageBitmap(bitmap);


        Button button_join = (Button)findViewById(R.id.button_join);
        Button button_leave = (Button)findViewById(R.id.button_leave);
        Button button_delete = (Button)findViewById(R.id.button_delete);

        if("Y".equalsIgnoreCase(isJoin)) {
            button_leave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showLoading(true);
                    ConnectionGroupJoinActivity cl = new ConnectionGroupJoinActivity(view, context);
                    cl.execute(userId, groupId, "leave");

                    button_join.setVisibility(View.VISIBLE);
                    button_leave.setVisibility(View.GONE);
                }
            });
            button_join.setVisibility(View.GONE);
        }else if("N".equalsIgnoreCase(isJoin)) {
            button_join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showLoading(true);
                    ConnectionGroupJoinActivity cl = new ConnectionGroupJoinActivity(view, context);
                    cl.execute(userId, groupId, "join");

                    button_leave.setVisibility(View.VISIBLE);
                    button_join.setVisibility(View.GONE);
                }
            });
            button_leave.setVisibility(View.GONE);
        }

        if(userId == ownerId && userId > 0){
            button_delete.setVisibility(View.VISIBLE);
            button_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showLoading(true);

                    ConnectionGroupDelete cl = new ConnectionGroupDelete(view, context);
                    cl.execute(groupId);

                }
            });
        }else{
            button_delete.setVisibility(View.GONE);
        }



    }

    public void postDeleteGroup(){
        Toast.makeText(this, "Delete success", Toast.LENGTH_LONG).show();
        finish();
    }
}
