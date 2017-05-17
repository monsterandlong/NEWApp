package com.bf.com.myapplication.activity;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.base.BaseAcyivity;

public class MeActivity extends BaseAcyivity {

    @Override
    public void initdata() {

    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_me;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    public boolean showLeftFragment() {
        return true;
    }
}
