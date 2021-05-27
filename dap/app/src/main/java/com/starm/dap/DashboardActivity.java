package com.starm.dap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.starm.dap.fragments.CurriculumFragment;
import com.starm.dap.fragments.HomeFragment;
import com.starm.dap.fragments.MessagesFragment;
import com.starm.dap.fragments.SapFragment;
import com.starm.dap.fragments.TraineesFragment;
import com.starm.dap.models.FragmentTag;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationViewEx.OnNavigationItemSelectedListener {
    private static final String TAG = "DashboardActivity";
    /////nox_adb.exe connect 127.0.0.1:6200

    /// fragament

    private HomeFragment mHomeFragment;
    private TraineesFragment mTraineesFragment;
    private CurriculumFragment mCurriculumFragment;
    private SapFragment mSapFragment;
    private MessagesFragment mMessagesFragment;


    //widgets
    private BottomNavigationViewEx mBottomNavigationViewEx;
    //vars
    private ArrayList<String> mFragmentsTags = new ArrayList<>();
    private ArrayList<FragmentTag> mFragments = new ArrayList<>();
    private int mExitCount = 0;
    //constants
    private static final int HOME_FRAGMENT = 0;
    private static final int TRAINEES_FRAGMENT = 1;
    private static final int CURRICULUM_FRAGMENT = 2;
    private static final int SAP_FRAGMENT = 3;
    private static final int MESSAGING_FRAGMENT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        initBottomNavigationView();
        init();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        switch (item.getItemId()) {


            case R.id.home: {
                mFragmentsTags.clear();
                mFragmentsTags = new ArrayList<>();
                init();
                break;
            }

            case R.id.bottom_nav_trainees: {
                Log.d(TAG, "onBottomNavigationItemSelected: trainees.");
                if (mTraineesFragment == null) {
                    mTraineesFragment = new TraineesFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mTraineesFragment, getString(R.string.tag_fragment_trainees));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_trainees));
                    mFragments.add(new FragmentTag(mTraineesFragment, getString(R.string.tag_fragment_trainees)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_trainees));
                    mFragmentsTags.add(getString(R.string.tag_fragment_trainees));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_trainees));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_curriculum: {
                Log.d(TAG, "onNavigationItemSelected: Curriculum.");
                if (mCurriculumFragment == null) {
                    mCurriculumFragment = new CurriculumFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mCurriculumFragment, getString(R.string.tag_fragment_curriculum));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_curriculum));
                    mFragments.add(new FragmentTag(mCurriculumFragment, getString(R.string.tag_fragment_curriculum)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_curriculum));
                    mFragmentsTags.add(getString(R.string.tag_fragment_curriculum));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_curriculum));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_home: {
                Log.d(TAG, "onNavigationItemSelected: HomeFragment.");

                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                    mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_home));
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_home));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_sap: {
                Log.d(TAG, "onNavigationItemSelected: SApFragment.");

                if (mSapFragment == null) {
                    mSapFragment = new SapFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mSapFragment, getString(R.string.tag_fragment_sap));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_sap));
                    mFragments.add(new FragmentTag(mSapFragment, getString(R.string.tag_fragment_sap)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_sap));
                    mFragmentsTags.add(getString(R.string.tag_fragment_sap));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_sap));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_messages: {
                Log.d(TAG, "onNavigationItemSelected: MessagesFragment.");
                if (mMessagesFragment == null) {
                    mMessagesFragment = new MessagesFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mMessagesFragment, getString(R.string.tag_fragment_messages));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_messages));
                    mFragments.add(new FragmentTag(mMessagesFragment, getString(R.string.tag_fragment_messages)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_messages));
                    mFragmentsTags.add(getString(R.string.tag_fragment_messages));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_messages));
                item.setChecked(true);
                break;
            }
        }
        return false;
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        int backStackCount = mFragmentsTags.size();
        if(backStackCount > 1){
            String topFragmentTag = mFragmentsTags.get(backStackCount - 1);

            String newTopFragmentTag = mFragmentsTags.get(backStackCount - 2);
            setFragmentVisibilities(newTopFragmentTag);

            mFragmentsTags.remove(topFragmentTag);

            mExitCount = 0;
        }
        else if( backStackCount == 1){
            String topFragmentTag = mFragmentsTags.get(backStackCount - 1);
            if(topFragmentTag.equals(getString(R.string.tag_fragment_home))){
                //mHomeFragment.scrollToTop();
                mExitCount++;
                Toast.makeText(this, "1 more click to exit", Toast.LENGTH_SHORT).show();
            }
            else{
                mExitCount++;
                Toast.makeText(this, "1 more click to exit", Toast.LENGTH_SHORT).show();
            }
        }

        if(mExitCount >= 2){
            super.onBackPressed();
        }
    }

    private void init(){
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
            transaction.commit();
            mFragmentsTags.add(getString(R.string.tag_fragment_home));
            mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));
        }
        else {
            mFragmentsTags.remove(getString(R.string.tag_fragment_home));
            mFragmentsTags.add(getString(R.string.tag_fragment_home));
        }
        setFragmentVisibilities(getString(R.string.tag_fragment_home));
    }

    private void setNavigationIcon(String tagname) {
        Menu menu = mBottomNavigationViewEx.getMenu();
        MenuItem menuItem = null;
        if (tagname.equals(getString(R.string.tag_fragment_home))) {
            Log.d(TAG, "setNavigationIcon: home fragment is visible");
            menuItem = menu.getItem(HOME_FRAGMENT);
            menuItem.setChecked(true);
        }
        else if (tagname.equals(getString(R.string.tag_fragment_sap))) {
            Log.d(TAG, "setNavigationIcon: sap fragment is visible");
            menuItem = menu.getItem(SAP_FRAGMENT);
            menuItem.setChecked(true);
        }
        else if (tagname.equals(getString(R.string.tag_fragment_messages))) {
            Log.d(TAG, "setNavigationIcon: messages fragment is visible");
            menuItem = menu.getItem(MESSAGING_FRAGMENT);
            menuItem.setChecked(true);
        }
        else if (tagname.equals(getString(R.string.tag_fragment_trainees))) {
            Log.d(TAG, "setNavigationIcon: trainees fragment is visible");
            menuItem = menu.getItem(TRAINEES_FRAGMENT);
            menuItem.setChecked(true);
        }
        else if (tagname.equals(getString(R.string.tag_fragment_curriculum))) {
            Log.d(TAG, "setNavigationIcon: tcurriculum fragment is visible");
            menuItem = menu.getItem(CURRICULUM_FRAGMENT);
            menuItem.setChecked(true);
        }
    }

    private void setFragmentVisibilities(String tagname){
//        if(tagname.equals(getString(R.string.tag_fragment_home)))
//            showBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_saved_connections)))
//            showBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_messages)))
//            showBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_settings)))
//            hideBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_view_profile)))
//            hideBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_chat)))
//            hideBottomNavigation();
//        else if(tagname.equals(getString(R.string.tag_fragment_agreement)))
//            hideBottomNavigation();

        for(int i = 0; i < mFragments.size(); i++){
            if(tagname.equals(mFragments.get(i).getTag())){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.show((mFragments.get(i).getFragment()));
                transaction.commit();
            }
            else{
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide((mFragments.get(i).getFragment()));
                transaction.commit();
            }
        }
        setNavigationIcon(tagname);

       // printBackStack();
    }

    private void initBottomNavigationView() {
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view.");
        mBottomNavigationViewEx.enableAnimation(false);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }

}