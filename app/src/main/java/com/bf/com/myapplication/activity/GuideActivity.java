package com.bf.com.myapplication.activity;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.adapter.GuideAdapter;
import com.bf.com.myapplication.base.BaseAcyivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseAcyivity {

    @BindView(R.id.pager_guide)
    ViewPager mpager;
    private GuideAdapter adapter;
    private boolean isOpen;

    @Override
    public void init() {
        //去掉状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //如果是第一次启动则启动引导界面，否则直接跳转到闪屏页面
        //记住这个是否第一次启动的状态
        //SharedPreferences代表数据化固有存储，代表将数据存进一个文件，退出后还有
        SharedPreferences sp = getSharedPreferences("appStar", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst ) {
            sp.edit().putBoolean("isFirst", false).commit();

        } else {
            openActivity(SplashActivity.class, true);
        }
    }

    @Override
    public void initdata() {
        ButterKnife.bind(this);
        adapter = new GuideAdapter();
        mpager.setAdapter(adapter);

    }

    @Override
    public void setListener() {
        mpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private Timer timer;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //判断是最后一个图片时
                if (position == adapter.getCount() - 1) {
                    timer = new Timer(false);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (isOpen) {
                                finish();
                            } else {
                                openActivity(SplashActivity.class, true);
                            }


                        }
                    }, 2000);

                } else {
                    //如果在2秒内化打其他图片则不跳转
                    if (timer != null)
                        timer.cancel();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_guide;
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
