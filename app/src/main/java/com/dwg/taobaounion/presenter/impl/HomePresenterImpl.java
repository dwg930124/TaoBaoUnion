package com.dwg.taobaounion.presenter.impl;

import com.dwg.taobaounion.model.Api;
import com.dwg.taobaounion.model.domain.Categories;
import com.dwg.taobaounion.presenter.IHomePresenter;
import com.dwg.taobaounion.utils.LogUtils;
import com.dwg.taobaounion.utils.RetrofitManager;
import com.dwg.taobaounion.view.IHomeCallback;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenterImpl implements IHomePresenter {

    private IHomeCallback mCallback;

    @Override
    public void getCategories() {
        // 加载商品分类数据
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> task = api.getCategories();
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                // 数据结果
                int code = response.code();
                LogUtils.d(HomePresenterImpl.this, "code --> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    // 请求成功
                    Categories categories = response.body();

                    /*LogUtils.d(HomePresenterImpl.this, categories.toString());
                    List<Categories.DataBean> data = categories.getData();
                    for (Categories.DataBean datum : data) {
                        LogUtils.d(HomePresenterImpl.this, datum.toString());
                    }*/

                    if (mCallback != null) {
                        mCallback.onCategoriesLoaded(categories);
                    }
                }else {
                    // 请求失败
                    LogUtils.d(HomePresenterImpl.this, "请求失败！");
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                // 加载失败
                LogUtils.d(HomePresenterImpl.this, "请求失败 --> " + t);
            }
        });
    }

    @Override
    public void registerCallback(IHomeCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unregisterCallback(IHomeCallback callback) {
        mCallback = null;
    }
}
