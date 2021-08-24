package com.dwg.taobaounion.ui.fragment;


import android.view.View;

import com.dwg.taobaounion.R;
import com.dwg.taobaounion.base.BaseFragment;

public class RedPacketFragment extends BaseFragment {


    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_red_packet;
    }

    @Override
    protected void initView(View rootView) {
        setupStatus(Status.SUCCESS);
    }
}
