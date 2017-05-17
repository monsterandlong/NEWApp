package com.bf.com.myapplication.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.fragment.LeftlistFragment;
import com.bf.com.myapplication.fragment.LoginFragment;
import com.bf.com.myapplication.lib.SlidingMenu;
import com.bf.com.myapplication.lib.app.SlidingFragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public abstract class BaseAcyivity extends SlidingFragmentActivity {


    private ImageView mIagLeft;
    private TextView tvTitle;
    private Unbinder bind;
    private Fragment fragmentLeft;
    private Fragment fragmentright;
    private ImageView mImgRight;
    private SlidingMenu sm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (showLeftFragment()) setFragment(savedInstanceState);
        init();
        super.setContentView(R.layout.titletop);
        setContentView(getContentView());
        ButterKnife.bind(this);
        setBehindContentView(R.layout.fragment_leftlist);
        setTitletop(isShowTitle());
        bufferknife();
        initdata();
        setListener();
    }

    //设置屏幕
    public void init() {
    }

    public void bufferknife() {
        bind = ButterKnife.bind(this);
    }

    //初始化数据
    public abstract void initdata();

    //找对应布局的
    public abstract int getlayoutId();

    //点击监听
    public void setListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    //判断标题顶部是否显示
    protected abstract boolean isShowTitle();


    public void setTitletop(boolean isShowTitle) {
        RelativeLayout titleTop = (RelativeLayout) findViewById(R.id.tiltle_top);
        if (isShowTitle) {
            tvTitle = (TextView) findViewById(R.id.tv_title);
            mIagLeft = (ImageView) findViewById(R.id.img_title_left);
            mIagLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggle();
                }
            });
            mImgRight = (ImageView) findViewById(R.id.img_title_right);
            mImgRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sm.showSecondaryMenu();
                }
            });

        } else {
            titleTop.setVisibility(View.GONE);
        }
    }

    //设置标题顶部的字
    public void setTiltle(String text) {
        tvTitle.setText(text);
    }

    //将标题顶部和下面拼接起来
    protected View getContentView() {
        LinearLayout mTitle = (LinearLayout) findViewById(R.id.tiltle);
        //解析拼接时，下面的布局的ID
        View subView = LayoutInflater.from(this).inflate(getlayoutId(), null);
        //设置大小
        subView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //将新布局添加到标题布局下
        mTitle.addView(subView);
        return mTitle;
    }

    //跳转页面
    public void openActivity(Class<? extends BaseAcyivity> cla, boolean isFinish) {
        startActivity(new Intent(this, cla));
        if (isFinish) finish();

    }
    public abstract boolean showLeftFragment();

    public void setFragment(Bundle savedInstanceState) {
        fragmentLeft = new LeftlistFragment();
        fragmentright = new LoginFragment();

        sm = getSlidingMenu();
        //滑动的方向
        sm.setMode(SlidingMenu.LEFT_RIGHT);
        sm.setSecondaryMenu(R.layout.fragment_login);
        SharedPreferences showLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
        boolean isLogin = showLogin.getBoolean("isLogin", true);
        if (isLogin) {

        }else {
            TextView tvLogin = (TextView) findViewById(R.id.tv_loginFrag_login);
            tvLogin.setText("long");
        }
        // 设置滑动阴影的宽度
        sm.setShadowWidthRes(R.dimen.shadow_width);
        // 设置滑动菜单阴影的图像资源
        sm.setShadowDrawable(null);
        // 设置滑动菜单视图的宽度
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        sm.setFadeDegree(0.35f);
        // 设置触摸屏幕的模式,这里设置为全屏
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置下方视图的在滚动时的缩放比例
        sm.setBehindScrollScale(0.0f);
    }

    //切换Fragment
    public void switchConent(Fragment fragment) {
        fragmentLeft = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_frame, fragment).commit();
        getSlidingMenu().showContent();
    }

    public void switchConent(Fragment fragment, String title) {
        fragmentright = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_right, fragment).commit();
        getSlidingMenu().showContent();
    }
}
