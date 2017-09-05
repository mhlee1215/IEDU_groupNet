package groupnet.iedu.com.groupnetandroid;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.iedu.domain.Group;

import java.util.ArrayList;
import java.util.List;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroup;
import groupnet.iedu.com.groupnetandroid.samples.gridview.GridViewAdapter;
import groupnet.iedu.com.groupnetandroid.samples.gridview.ImageItem;
import groupnet.iedu.com.groupnetandroid.samples.tab.DemoAdapter;

import static groupnet.iedu.com.groupnetandroid.R.id.gridView;

/**
 *
 */
public class GroupFragment extends Fragment implements MainFragment {

	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	//private GridView gridView;
	private GroupFragmentAdapter adapter;
	private int userId;

	/**
	 * Create a new instance of the fragment
	 */
	public static GroupFragment newInstance(int index) {
		GroupFragment fragment = new GroupFragment();
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("preferences", 0); // 0 - for private mode
        userId = pref.getInt("USER_ID", -1);
		String userName = pref.getString("USER_NAME", "");
        Log.e("GroupNet", "GROUP_FRAGMENT_USERID:"+userId);

		View view = inflater.inflate(R.layout.fragment_group, container, false);


		recyclerView = (RecyclerView) view.findViewById(R.id.fragment_demo_recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);


		ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
		int color2 = generator.getColor(userName);

		TextDrawable drawable = TextDrawable.builder()
				.beginConfig()
				.width(40)  // width in px
				.height(40) // height in px
				.endConfig()
				.buildRoundRect(userName.substring(0, 1), color2, 30);

		ImageView image = (ImageView) view.findViewById(R.id.image_view);
		image.setImageDrawable(drawable);


		TextView userIdView = (TextView)view.findViewById(R.id.user_id_view);
		userIdView.setText(userName);

		//initDemoList(view);
		ConnectionGroup cl = new ConnectionGroup(view, this);
		cl.execute(userId, "", userId);
		return view;

//		gridView = (GridView) view.findViewById(R.id.grou_gridView);
//		gridAdapter = new GroupFragmentViewAdapter(getActivity(), R.layout.group_fragment_item_layout, getData());
//		gridView.setAdapter(gridAdapter);

		//return view;
	}

	// Prepare some dummy data for gridview
	private ArrayList<ImageItem> getData() {
		final ArrayList<ImageItem> imageItems = new ArrayList<>();
		TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
		for (int i = 0; i < imgs.length(); i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
			imageItems.add(new ImageItem(bitmap, "Image#" + i));
		}
		return imageItems;
	}



	public void postExecute(View view, Object groupData) {
		if(adapter == null) {
			adapter = new GroupFragmentAdapter((List<Group>)groupData, this);
			recyclerView.setAdapter(adapter);
		}else{
			adapter = new GroupFragmentAdapter((List<Group>)groupData, this);
			recyclerView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			//swipeRefreshLayout.setRefreshing(false);
		}
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
