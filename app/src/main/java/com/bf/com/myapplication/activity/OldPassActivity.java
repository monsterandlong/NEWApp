package com.bf.com.myapplication.activity;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.base.BaseAcyivity;

public class OldPassActivity extends BaseAcyivity {

    @Override
    public void initdata() {

    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_old_pass;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    public boolean showLeftFragment() {
        return false;
    }


}
