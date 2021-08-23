package com.dwg.taobaounion.presenter;

import com.dwg.taobaounion.view.IHomeCallback;

public interface IHomePresenter {

    /**
     * 获取商品分类
     */
    void getCategories();

    /**
     * 注册UI通知接口
     * @param callback
     */
    void registerCallback(IHomeCallback callback);

    /**
     * 取消注册UI通知接口
     * @param callback
     */
    void unregisterCallback(IHomeCallback callback);
}
