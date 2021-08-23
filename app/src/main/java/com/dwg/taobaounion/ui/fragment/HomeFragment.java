package com.dwg.taobaounion.ui.fragment;


import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.dwg.taobaounion.R;
import com.dwg.taobaounion.base.BaseFragment;
import com.dwg.taobaounion.model.domain.Categories;
import com.dwg.taobaounion.presenter.IHomePresenter;
import com.dwg.taobaounion.presenter.impl.HomePresenterImpl;
import com.dwg.taobaounion.ui.adapter.HomePagerAdapter;
import com.dwg.taobaounion.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    private IHomePresenter mHomePresenter;
    private HomePagerAdapter mHomePagerAdapter;

    @BindView(R.id.home_indicator)
    protected TabLayout homeIndicator;

    @BindView(R.id.home_pager)
    protected ViewPager homePager;


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        homeIndicator.setupWithViewPager(homePager);
        // 给ViewPager设置适配器
        mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        homePager.setAdapter(mHomePagerAdapter);
    }

    @Override
    protected void initPresenter() {
        // 创建 Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        // 加载数据
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        // 加载的数据从这里回来
        if (categories != null) {
            mHomePagerAdapter.setCategories(categories);
        }
    }

    @Override
    protected void release() {
        // 取消回调注册
        if (mHomePresenter != null) {
            mHomePresenter.unregisterCallback(this);
        }
    }
}
