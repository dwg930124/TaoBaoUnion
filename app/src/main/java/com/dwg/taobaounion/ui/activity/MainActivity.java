package com.dwg.taobaounion.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dwg.taobaounion.R;
import com.dwg.taobaounion.base.BaseFragment;
import com.dwg.taobaounion.ui.fragment.HomeFragment;
import com.dwg.taobaounion.ui.fragment.RecommendFragment;
import com.dwg.taobaounion.ui.fragment.RedPacketFragment;
import com.dwg.taobaounion.ui.fragment.SearchFragment;
import com.dwg.taobaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_navigation_bar)
    protected BottomNavigationView mNavigationView;


    private HomeFragment mHomeFragment;
    private RecommendFragment mRecommendFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFragmentManager;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBind = ButterKnife.bind(this);

        initListener();
        initFragment();
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mRecommendFragment = new RecommendFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSearchFragment = new SearchFragment();

        switchFragment(mHomeFragment);
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        LogUtils.d(MainActivity.class, item.getTitle().toString());
                        switchFragment(mHomeFragment);
                        break;
                    case R.id.recommend:
                        LogUtils.i(MainActivity.class, item.getTitle().toString());
                        switchFragment(mRecommendFragment);
                        break;
                    case R.id.red_packet:
                        LogUtils.w(MainActivity.class, item.getTitle().toString());
                        switchFragment(mRedPacketFragment);
                        break;
                    case R.id.search:
                        LogUtils.e(MainActivity.class, item.getTitle().toString());
                        switchFragment(mSearchFragment);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void switchFragment(BaseFragment targetFragment) {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.main_page_container, targetFragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }
}
