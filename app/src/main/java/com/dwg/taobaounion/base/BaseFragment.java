package com.dwg.taobaounion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dwg.taobaounion.R;
import com.dwg.taobaounion.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder mBind;
    private FrameLayout mBaseContainer;

    private Status currentStatus = Status.GONE;
    private View mSuccessView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    @OnClick(R.id.network_error_tips)
    public void retry() {
        LogUtils.d(this, "on retry");
        onRetryClick();
    }

    /**
     * 如果子类在网络错误后需要点击，就覆盖该方法即可
     */
    protected void onRetryClick(){
    }

    public enum Status {
        GONE, SUCCESS, ERROR, EMPTY, LOADING
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater, container);
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatusView(inflater, container);

        mBind = ButterKnife.bind(this, rootView);

        initView(rootView);
        initPresenter();
        loadData();

        return rootView;
    }

    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_layout, container, false);
    }

    /**
     * 加载各种状态的view
     *
     * @param inflater
     * @param container
     */
    private void loadStatusView(LayoutInflater inflater, ViewGroup container) {
        // 加载成功的view
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);
        // 加载正在加载中的view
        mLoadingView = loadLoadingView(inflater, container);
        mBaseContainer.addView(mLoadingView);
        // 加载网络错误的view
        mErrorView = loadErrorView(inflater, container);
        mBaseContainer.addView(mErrorView);
        // 加载内容为空的view
        mEmptyView = loadEmptyView(inflater, container);
        mBaseContainer.addView(mEmptyView);

        setupStatus(Status.GONE);
    }

    /**
     * 子类通过这个方法来切换状态页面
     * @param status
     */
    public void setupStatus(Status status) {
        currentStatus = status;
        mSuccessView.setVisibility(currentStatus == Status.SUCCESS ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(currentStatus == Status.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(currentStatus == Status.ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(currentStatus == Status.EMPTY ? View.VISIBLE : View.GONE);
    }

    protected void initView(View rootView) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) {
            mBind.unbind();
        }
        release();
    }

    protected void release() {
        // 释放资源
    }

    protected void initPresenter() {
        // 创建Presenter
    }

    protected void loadData() {
        // 加载数据
    }

    private View loadSuccessView(LayoutInflater inflater, ViewGroup container) {
        int resId = getRootViewResId();
        return inflater.inflate(resId, container, false);
    }

    private View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    private View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    private View loadEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }


    protected abstract int getRootViewResId();
}
