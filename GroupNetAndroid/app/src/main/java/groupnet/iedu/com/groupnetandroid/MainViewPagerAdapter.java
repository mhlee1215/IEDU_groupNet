package groupnet.iedu.com.groupnetandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import groupnet.iedu.com.groupnetandroid.HomeFragment;

/**
 *
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<MainFragment> fragments = new ArrayList<>();
	private MainFragment currentFragment;

	public MainViewPagerAdapter(FragmentManager fm) {
		super(fm);

		fragments.clear();
		fragments.add(HomeFragment.newInstance(0));
		fragments.add(AddFragment.newInstance(1));
		fragments.add(SearchFragment.newInstance(2));
		fragments.add(ChatFragment.newInstance(3));
		fragments.add(GroupFragment.newInstance(4));
	}

	public void resetItem(int i){
		if(i==1){
			fragments.add(AddFragment.newInstance(1));
		}

		this.notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int position) {
		return (Fragment)fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			currentFragment = ((MainFragment) object);
		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public MainFragment getCurrentFragment() {
		return currentFragment;
	}
}