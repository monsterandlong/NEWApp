package com.bf.com.myapplication.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.base.BaseAcyivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseAcyivity implements View.OnClickListener{
    @BindView(R.id.rela_splash)
    RelativeLayout mRela;
    private Timer timer;
    int time = 3;

    @Override
    public void initdata() {
        ButterKnife.bind(this);
        timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                --time;
                if (time == 0) {
                    openActivity(MainActivity.class, true);
                    timer.cancel();
                }

            }
        },0,1000);
        //0：代表0秒后执行， 1000：代表1秒后执行下一次

    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    public boolean showLeftFragment() {
        return false;
    }

    @Override
    public void setListener() {
        mRela.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        timer.cancel();
        openActivity(MainActivity.class, true);
    }


}
