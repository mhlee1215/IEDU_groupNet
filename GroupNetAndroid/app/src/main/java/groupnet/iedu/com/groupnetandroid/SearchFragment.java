package groupnet.iedu.com.groupnetandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.iedu.domain.Group;

import java.util.ArrayList;
import java.util.List;

import groupnet.iedu.com.groupnetandroid.Connections.ConnectionGroup;
import groupnet.iedu.com.groupnetandroid.Connections.ConnectionNameLookup;
import groupnet.iedu.com.groupnetandroid.samples.tab.DemoAdapter;

/**
 *
 */
public class SearchFragment extends Fragment implements MainFragment {

	private Fragment fragment;
	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private int userId;
	private SearchFragmentAdapter adapter;

	private EditText search_keyword;

	/**
	 * Create a new instance of the fragment
	 */
	public static SearchFragment newInstance(int index) {
		SearchFragment fragment = new SearchFragment();
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
		Log.e("GroupNet", "SEARCH_FRAGMENT_USERID:"+userId);

		fragment = this;

		View view = inflater.inflate(R.layout.fragment_search, container, false);

		recyclerView = (RecyclerView) view.findViewById(R.id.fragment_demo_recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		search_keyword = (EditText) view.findViewById(R.id.search_keyword);

		search_keyword.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				// you can call or do what you want with your EditText here
				ConnectionGroup cl = new ConnectionGroup(view, fragment);
				cl.execute(userId, Group.ACCESS_PUBLIC, 0, search_keyword.getText().toString());
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});



		return view;
	}


	public void postExecute(View view, Object groupData) {
		if(adapter == null) {
			adapter = new SearchFragmentAdapter((List<Group>)groupData, this);
			recyclerView.setAdapter(adapter);
		}else{
			adapter = new SearchFragmentAdapter((List<Group>)groupData, this);
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
