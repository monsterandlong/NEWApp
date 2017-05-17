package com.bf.com.myapplication.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.base.BaseAcyivity;
import com.bf.com.myapplication.manger.DButile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetMainActivity extends BaseAcyivity implements View.OnClickListener{

    @BindView(R.id.web_getmain)
    WebView webGetmain;
    @BindView(R.id.img_getmain_left)
    ImageView imgGetmainLeft;
    @BindView(R.id.img_getmain_right)
    ImageView imgGetmainRight;
    private String title;
    private String time;
    private String ioce;
    private String url;

    @Override
    public void initdata() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        time = intent.getStringExtra("time");
        ioce = intent.getStringExtra("ioce");
        webGetmain.loadUrl(url);
        webGetmain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webGetmain.loadUrl(request.getUrl().toString());
                return true;
            }
        });

    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_get_main;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    public boolean showLeftFragment() {
        return true;
    }

    @Override
    public void setListener() {
        imgGetmainRight.setOnClickListener(GetMainActivity.this);
        imgGetmainLeft.setOnClickListener(GetMainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_getmain_right:
                registerForContextMenu(imgGetmainRight);
                PopupMenu popupMenu=new PopupMenu(GetMainActivity.this,v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_getmain,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        DButile.addDB(title,ioce,time,url);
                        Toast.makeText(GetMainActivity.this, "点击弹窗", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                popupMenu.show();
                break;
            case R.id.img_getmain_left:
                openActivity(MainActivity.class,true);
                break;
            case R.id.img_loginFrag_login:
//                openActivity(LoginActivity.class,false);
                break;
            case R.id.rela_left_new:
                openActivity(MainActivity.class,false);
                break;
            case R.id.rela_left_favorite:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
//                openActivity(FavoriteActivity.class,false);
                break;
            case R.id.rela_left_local:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rela_left_commend:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rela_left_photo:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
