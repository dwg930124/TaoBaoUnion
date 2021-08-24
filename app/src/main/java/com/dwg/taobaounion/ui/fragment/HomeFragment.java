package com.dwg.taobaounion.ui.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_home_layout, container, false);
    }

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
    protected void onRetryClick() {
        super.onRetryClick();
        // 网络错误，点击重试，重新加载分类内容
        if (mHomePresenter != null) {
            mHomePresenter.getCategories();
        }
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        // 加载的数据从这里回来
        if (categories != null) {
            mHomePagerAdapter.setCategories(categories);
        }
    }

    @Override
    public void onLoading() {
        setupStatus(Status.LOADING);
    }

    @Override
    public void onSuccess() {
        setupStatus(Status.SUCCESS);
    }

    @Override
    public void onError() {
        setupStatus(Status.ERROR);
    }

    @Override
    public void onEmpty() {
        setupStatus(Status.EMPTY);
    }

    @Override
    protected void release() {
        // 取消回调注册
        if (mHomePresenter != null) {
            mHomePresenter.unregisterCallback(this);
        }
    }
}
