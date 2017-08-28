package groupnet.iedu.com.groupnetandroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.iedu.domain.Group;

import java.util.ArrayList;
import java.util.List;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroup;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionLogin;
import groupnet.iedu.com.groupnetandroid.Domain.GroupItem;
import groupnet.iedu.com.groupnetandroid.samples.gridview.ImageItem;
import groupnet.iedu.com.groupnetandroid.samples.tab.BottomNav2Activity;


/**
 *
 */
public class HomeFragment extends Fragment implements MainFragment {

	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private SwipeRefreshLayout swipeRefreshLayout;
	private HomeFragmentAdapter adapter;
	private HomeFragment homeFragment;

	/**
	 * Create a new instance of the fragment
	 */
	public static MainFragment newInstance(int index) {
		HomeFragment fragment = new HomeFragment();
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b);
		return (MainFragment)fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		homeFragment = this;
		View view = inflater.inflate(R.layout.fragment_demo_list, container, false);

		swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefresh);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
//				System.out.println("refresh!!");
//				swipeRefreshLayout.setRefreshing(false);
				ConnectionGroup cl = new ConnectionGroup(view, homeFragment);
				cl.execute();
			}
		});

		fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);
		recyclerView = (RecyclerView) view.findViewById(R.id.fragment_demo_recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);


		//initDemoList(view);
		ConnectionGroup cl = new ConnectionGroup(view, this);
		cl.execute();

		return view;

	}



	private ArrayList<GroupItem> getData() {
		final ArrayList<GroupItem> imageItems = new ArrayList<>();
		TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
		for (int i = 0; i < imgs.length(); i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
			imageItems.add(new GroupItem(bitmap, "Image#" + i));
		}
		return imageItems;
	}

	/**
	 * Init the fragment
	 */
	public void initDemoList(View view, List<Group> groupData) {
		if(adapter == null) {
			adapter = new HomeFragmentAdapter(groupData);
			recyclerView.setAdapter(adapter);
		}else{
			adapter = new HomeFragmentAdapter(groupData);
			recyclerView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			swipeRefreshLayout.setRefreshing(false);
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
