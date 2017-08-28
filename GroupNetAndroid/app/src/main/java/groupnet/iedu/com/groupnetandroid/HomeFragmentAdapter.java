package groupnet.iedu.com.groupnetandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iedu.domain.Group;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroupJoin;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionLogin;
import groupnet.iedu.com.groupnetandroid.Domain.GroupItem;

/**
 *
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {

	HomeFragment fragment;
	int userId;

	private ArrayList<Group> mDataset = new ArrayList<>();

	private HashMap<String, Bitmap> imageMap = new HashMap<String, Bitmap>();

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTitleView;
		public TextView mOwnerView;
		public TextView mDescView;
		public TextView mMyGroupMarkView;

		public ImageButton mBtnFavorite;
		public ImageButton mBtnAdd;
		public ImageButton mBtnRemove;


		public ImageView mImageView;
		public ViewHolder(View v) {
			super(v);
			mTitleView = (TextView) v.findViewById(R.id.GroupName);
			mOwnerView = (TextView) v.findViewById(R.id.userName);
			mDescView = (TextView) v.findViewById(R.id.editGroupDescription);
			mMyGroupMarkView = (TextView) v.findViewById(R.id.MyGroupMark);

			mImageView = (ImageView) v.findViewById(R.id.layout_item_home_image);

			mBtnFavorite = (ImageButton) v.findViewById(R.id.button_group_favorite);
			mBtnAdd = (ImageButton) v.findViewById(R.id.button_group_add);
			mBtnRemove = (ImageButton) v.findViewById(R.id.button_group_remove);
		}
	}

	public void postGroupJoin(){
		//Toast.makeText(this, "post group join?", Toast.LENGTH_LONG).show();
	}

	public HomeFragmentAdapter(List<Group> dataset, HomeFragment fragment) {
		this.fragment = fragment;

		SharedPreferences pref = fragment.getActivity().getApplicationContext().getSharedPreferences("preferences", 0); // 0 - for private mode
		userId = pref.getInt("USER_ID", -1);
		Log.e("GroupNet", "MAIN_ACTIVITY_USERID:"+userId);

		mDataset.clear();
		mDataset.addAll(dataset);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_home, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.mOwnerView.setText(mDataset.get(position).getOwnerName());
		holder.mTitleView.setText(mDataset.get(position).getName());
		holder.mDescView.setText(mDataset.get(position).getDescription());


		holder.mImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(fragment.getActivity(), GroupDetailActivity.class);

				intent.putExtra("userId", userId);
				intent.putExtra("groupId", mDataset.get(position).getId());
				intent.putExtra("ownerName", mDataset.get(position).getOwnerName());
				intent.putExtra("ownerId", mDataset.get(position).getOwnerId());
				intent.putExtra("groupName", mDataset.get(position).getName());
				intent.putExtra("descriptor", mDataset.get(position).getDescription());
				intent.putExtra("isJoin", mDataset.get(position).getIsJoin());
				intent.putExtra("isFavorite", mDataset.get(position).getIsFavorite());

				//Convert bitmap to byte array
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				if(imageMap.get(position+"") != null) {
					imageMap.get(position + "").compress(Bitmap.CompressFormat.JPEG, 30 , stream);
					byte[] byteArray = stream.toByteArray();
					//Then transfer
					intent.putExtra("image", byteArray);
				}

				fragment.getActivity().startActivity(intent);
			}
		});

		holder.mBtnFavorite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.e("GroupNet", "favorite selected");
			}
		});


		if("Y".equalsIgnoreCase(mDataset.get(position).getIsJoin())){
			holder.mBtnRemove.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.e("GroupNet", "add selected"+mDataset.get(position).getId());
					//showLoading(true);
					ConnectionGroupJoin cl = new ConnectionGroupJoin(view, fragment);
					cl.execute(userId, mDataset.get(position).getId(), "leave");

					holder.mBtnAdd.setVisibility(View.VISIBLE);
					holder.mBtnRemove.setVisibility(View.GONE);
				}
			});
			holder.mBtnAdd.setVisibility(View.GONE);

		}else{
			holder.mBtnAdd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.e("GroupNet", "add selected"+mDataset.get(position).getId());
					//showLoading(true);
					ConnectionGroupJoin cl = new ConnectionGroupJoin(view, fragment);
					cl.execute(userId, mDataset.get(position).getId(), "join");

					holder.mBtnRemove.setVisibility(View.VISIBLE);
					holder.mBtnAdd.setVisibility(View.GONE);
				}
			});
			holder.mBtnRemove.setVisibility(View.GONE);
		}



		//holder.mImageView.setImageBitmap(mDataset.get(position).getImage());

		Bitmap image = imageMap.get(position+"");
		if( image != null)
			holder.mImageView.setImageBitmap(image);
		else {

			String id = "";
			String defaultId = "20170816_200822_075c99ad-d1b2-4981-9e1b-69e35844f20d";
			String url = mDataset.get(position).getUrl();
			System.out.println("group?:"+mDataset.get(position));
			System.out.println("url?:"+url);
			if(mDataset.get(position).getUrl() != null && !mDataset.get(position).getUrl().isEmpty())
				id = mDataset.get(position).getUrl();
			else
				id = defaultId;

			new DownloadImageTask((ImageView) holder.mImageView)
					//.execute("http://52.52.168.137/think-forum-web/mediaDownloadWeb.do?path=20170810_023626_ad05c2dd-e5a9-40cd-a664-27f116a23164", position+"");
					.execute("http://52.34.169.106:8080/GroupNetWeb/mediaDownloadWeb.do?path="+id, position + "");
		}

		if(userId == mDataset.get(position).getOwnerId() && userId > 0){
			holder.mMyGroupMarkView.setVisibility(View.VISIBLE);
		}else{
			holder.mMyGroupMarkView.setVisibility(View.GONE);
		}
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String ... url) {
			String urldisplay = url[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}

			imageMap.put(url[1], mIcon11);

			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

}
