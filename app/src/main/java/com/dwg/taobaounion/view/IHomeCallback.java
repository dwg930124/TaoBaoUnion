package com.dwg.taobaounion.view;

import com.dwg.taobaounion.model.domain.Categories;

public interface IHomeCallback {

    /**
     * 获取商品分类成功
     */
    void onCategoriesLoaded(Categories categories);
}
