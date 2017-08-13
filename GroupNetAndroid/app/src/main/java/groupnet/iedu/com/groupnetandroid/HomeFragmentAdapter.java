package groupnet.iedu.com.groupnetandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import groupnet.iedu.com.groupnetandroid.Domain.GroupItem;

/**
 *
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder> {

	private ArrayList<GroupItem> mDataset = new ArrayList<>();

	private HashMap<String, Bitmap> imageMap = new HashMap<String, Bitmap>();

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTitleView;
		public ImageView mImageView;
		public ViewHolder(View v) {
			super(v);
			mTitleView = (TextView) v.findViewById(R.id.layout_item_home_title);
			mImageView = (ImageView) v.findViewById(R.id.layout_item_home_image);
		}
	}

	public HomeFragmentAdapter(ArrayList<GroupItem> dataset) {
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
		holder.mTitleView.setText(mDataset.get(position).getTitle());
		holder.mImageView.setImageBitmap(mDataset.get(position).getImage());

//		Bitmap image = imageMap.get(position+"");
//		if( image != null)
//			holder.mImageView.setImageBitmap(image);
//		else
//			new DownloadImageTask((ImageView)holder.mImageView)
//					.execute("http://52.52.168.137/think-forum-web/mediaDownloadWeb.do?path=20170810_023626_ad05c2dd-e5a9-40cd-a664-27f116a23164", position+"");
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