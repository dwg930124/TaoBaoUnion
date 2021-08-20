package com.dwg.taobaounion.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dwg.taobaounion.R;
import com.dwg.taobaounion.ui.fragment.HomeFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        HomeFragment homeFragment = new HomeFragment();
    }

}
