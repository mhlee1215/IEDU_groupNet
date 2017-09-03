package groupnet.iedu.com.groupnetandroid;

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
import android.widget.FrameLayout;

import com.iedu.domain.Message;

import java.util.ArrayList;
import java.util.List;

import groupnet.iedu.com.groupnetandroid.samples.tab.DemoAdapter;

/**
 *
 */
public class ChatFragment extends Fragment implements MainFragment {

	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;

	private RecyclerView mMessageRecycler;
	private ChatListAdapter mMessageAdapter;


	/**
	 * Create a new instance of the fragment
	 */
	public static ChatFragment newInstance(int index) {
		ChatFragment fragment = new ChatFragment();
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chat, container, false);

		List<Message> messageList = new ArrayList<Message>();

		{
			Message m = new Message();
			m.setSendID(123);
			m.setRecieveID(234);
			m.setText("Heihi");
			m.setCreatedAt(123123);
			messageList.add(m);
		}

		{
			Message m = new Message();
			m.setSendID(123);
			m.setRecieveID(234);
			m.setText("Heihi1231");
			m.setCreatedAt(123123);
			messageList.add(m);
		}

		mMessageRecycler = (RecyclerView) view.findViewById(R.id.reyclerview_message_list);
		mMessageAdapter = new ChatListAdapter(getActivity(), messageList);
		mMessageRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));


		mMessageRecycler.setAdapter(mMessageAdapter);


		return view;
	}

	public void postExecute(View view, Object groupData) {

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
