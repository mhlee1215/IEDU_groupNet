package groupnet.iedu.com.groupnetandroid;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import java.lang.reflect.Method;
import java.util.ArrayList;

import groupnet.iedu.com.groupnetandroid.samples.connection.ConnectionSample;
import groupnet.iedu.com.groupnetandroid.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private String TAG = "GroupNet";

    private Context context;
    private MainFragment currentFragment;
    private MainViewPagerAdapter adapter;
    private AHBottomNavigationAdapter navigationAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    //private boolean useMenuResource = true;
    private int[] tabColors;
    private Handler handler = new Handler();

    // UI
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", 0); // 0 - for private mode
        int userId = pref.getInt("USER_ID", -1);
        Log.e("GroupNet", "MAIN_ACTIVITY_USERID:"+userId);
        //System.out.println("MAIN_ACTIVITY_USERID:"+userId);

        context = this;

        initUI();
    }

    public void setTab(int i){
        bottomNavigation.setCurrentItem(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar_options, menu);

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//

        if(menu.getClass().getSimpleName().equals("MenuBuilder")){
            try{
                Method m = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            }
            catch(NoSuchMethodException e){
                Log.e(TAG, "onMenuOpened", e);
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.action_setting:

                return true;
            case R.id.action_about:
                // location found

//                Intent settingActivity = new Intent(context, SettingActivity.class);
//                context.startActivity(settingActivity);
                return true;
            case R.id.action_logout:

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Log out")
                        .setMessage("Is that okay to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("USER_ID", -1);
                                editor.commit();

                                Intent loginActivity = new Intent(context, LoginPageActivity.class);
                                context.startActivity(loginActivity);
                                //Stop the activity
                                //YourClass.this.finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//
//    @Override
//public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu items for use in the action bar
//    MenuInflater inflater = getMenuInflater();
//    inflater.inflate(R.menu.main_action_bar, menu);
//
//    // To show icons in the actionbar's overflow menu:
//    // http://stackoverflow.com/questions/18374183/how-to-show-icons-in-overflow-menu-in-actionbar
//    //if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
//    if(menu.getClass().getSimpleName().equals("MenuBuilder")){
//        try{
//            Method m = menu.getClass().getDeclaredMethod(
//                    "setOptionalIconsVisible", Boolean.TYPE);
//            m.setAccessible(true);
//            m.invoke(menu, true);
//        }
//        catch(NoSuchMethodException e){
//            Log.e(TAG, "onMenuOpened", e);
//        }
//        catch(Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//    //}
//
//    return super.onCreateOptionsMenu(menu);
//}

    private void initUI() {

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomNavigation.getCurrentItem() == 1){
                    System.out.println("add?");
                }
            }
        });


        //bottomNavigation.setAccentColor(android.R.color.holo_blue_bright);

        //bottomNavigation.setColoredModeColors(R.color.blue, R.color.black);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_home_black, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_add_black_24dp, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_search_black_24dp, R.color.color_tab_3);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_chat_black_24dp, R.color.color_tab_4);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.drawable.ic_group_black_24dp, R.color.color_tab_5);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);

        bottomNavigation.addItems(bottomNavigationItems);


        bottomNavigation.setColored(false);

        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
        bottomNavigation.setTranslucentNavigationEnabled(true);




        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    currentFragment.refresh();
                    return true;
                }

                if (currentFragment != null) {
                    currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);
                currentFragment = adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();

                if (position == 1) {
                    bottomNavigation.setNotification("", 1);

                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setAlpha(0f);
                    floatingActionButton.setScaleX(0f);
                    floatingActionButton.setScaleY(0f);
                    floatingActionButton.animate()
                            .alpha(1)
                            .scaleX(1)
                            .scaleY(1)
                            .setDuration(300)
                            .setInterpolator(new OvershootInterpolator())
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    floatingActionButton.animate()
                                            .setInterpolator(new LinearOutSlowInInterpolator())
                                            .start();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .start();

                } else {
                    if (floatingActionButton.getVisibility() == View.VISIBLE) {
                        floatingActionButton.animate()
                                .alpha(0)
                                .scaleX(0)
                                .scaleY(0)
                                .setDuration(300)
                                .setInterpolator(new LinearOutSlowInInterpolator())
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .start();
                    }
                }

                return true;
            }
        });

		/*
		bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
			@Override public void onPositionChange(int y) {
				Log.d("DemoActivity", "BottomNavigation Position: " + y);
			}
		});
		*/

        viewPager.setOffscreenPageLimit(4);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();





//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Setting custom colors for notification
//                AHNotification notification = new AHNotification.Builder()
//                        .setText(":)")
//                        .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_back))
//                        .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_text))
//                        .build();
//                bottomNavigation.setNotification(notification, 1);
//                Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
//                        Snackbar.LENGTH_SHORT).show();
//
//            }
//        }, 3000);

        //bottomNavigation.setDefaultBackgroundResource(R.drawable.bottom_navigation_background);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("GroupNet", "restart!");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Log.e("GroupNet", "onResumeFragments!");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("GroupNet", "onPostResume!");
    }
}
