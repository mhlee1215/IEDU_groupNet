package groupnet.iedu.com.groupnetandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionFileUpload;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionFileUploadFragment;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroup;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroupAdd;
import groupnet.iedu.com.groupnetandroid.Utils.FileFromBitmap;
import groupnet.iedu.com.groupnetandroid.Utils.FileFromBitmapFragment;
import groupnet.iedu.com.groupnetandroid.samples.tab.DemoAdapter;

import static android.app.Activity.RESULT_OK;

/**
 *
 */
public class AddFragment extends Fragment implements MainFragment {

	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	//AVLoadingIndicatorView loadingMark;
	RelativeLayout loadingLayout;

	EditText groupName;
	EditText groupDesc;

	ImageView targetImage;
	AddFragment addFragment = null;
	String uploadedImageId = "";

	/**
	 * Create a new instance of the fragment
	 */
	public static AddFragment newInstance(int index) {
		AddFragment fragment = new AddFragment();
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_add, container, false);

		addFragment = this;
		//loadingMark = (AVLoadingIndicatorView)view.findViewById(R.id.avloadingIndicatorView);
		loadingLayout = (RelativeLayout)view.findViewById(R.id.loading_background_layout);
		//showLoading(true);

		groupName = (EditText)view.findViewById(R.id.group_name);
		groupDesc = (EditText)view.findViewById(R.id.group_desc);

		targetImage = (ImageView)view.findViewById(R.id.targetimage);
		Button buttonLoadImage = (Button)view.findViewById(R.id.btn_loadimage);
		buttonLoadImage.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 0);
			}});

		Button buttonCreateGroup = (Button)view.findViewById(R.id.btn_create_group);
		buttonCreateGroup.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ConnectionGroupAdd cl = new ConnectionGroupAdd(view, addFragment);
				cl.execute(groupName.getText().toString(), groupDesc.getText().toString(), uploadedImageId);
//				Intent intent = new Intent(Intent.ACTION_PICK,
//						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//				startActivityForResult(intent, 0);
			}});

		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		showLoading(true);

		if (resultCode == RESULT_OK){
			Uri targetUri = data.getData();
			//textTargetUri.setText(targetUri.toString());
			Bitmap bitmap;
			try {

				bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
				targetImage.setImageBitmap(bitmap);

				//File file = new File(targetUri);
				//showLoading(true);
				FileFromBitmapFragment ffb = new FileFromBitmapFragment(bitmap, this);
				ffb.execute();
				//MediaClient.;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void uploadFile(File file){
		ConnectionFileUploadFragment cfu = new ConnectionFileUploadFragment(this);
		cfu.execute(file);
	}

	public void uploadFinished(String id){
		uploadedImageId = id;
		showLoading(false);
		Toast.makeText(getActivity(), "Upload Finished", Toast.LENGTH_LONG).show();
	}

	public void postAddGroup(){
		Toast.makeText(getActivity(), "Group Added..", Toast.LENGTH_LONG).show();
	}

	public void showLoading(boolean visible){
		if(visible)
			loadingLayout.setVisibility(View.VISIBLE);
		else
			loadingLayout.setVisibility(View.GONE);
	}

	/**
	 * Refresh
	 */
	public void refresh() {
		if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
			recyclerView.smoothScrollToPosition(0);
		}
	}

	/**
	 * Called when a fragment will be displayed
	 */
	public void willBeDisplayed() {
		// Do what you want here, for example animate the content
		if (fragmentContainer != null) {
			Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
			fragmentContainer.startAnimation(fadeIn);
		}
	}

	/**
	 * Called when a fragment will be hidden
	 */
	public void willBeHidden() {
		if (fragmentContainer != null) {
			Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
			fragmentContainer.startAnimation(fadeOut);
		}
	}
}
