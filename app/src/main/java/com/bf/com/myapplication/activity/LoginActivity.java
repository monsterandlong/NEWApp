package com.bf.com.myapplication.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.base.BaseAcyivity;
import com.bf.com.myapplication.manger.POST;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseAcyivity implements View.OnClickListener{
    public HashMap<String, Object> allMap = new HashMap<String, Object>();
    @BindView(R.id.tv_login_name)
    EditText edName;
    @BindView(R.id.tv_login_pass)
    EditText edPass;
    @BindView(R.id.bt_login_registered)
    Button btRegistered;
    @BindView(R.id.bt_login_old)
    Button btOld;
    @BindView(R.id.bt_login_login)
    Button btLogin;
    public static final String APPURL = "http://118.244.212.82:9092/newsClient";
    private String web;
    String s="";
    private String name;
    private String pass;

    @Override
    public void initdata() {
        ButterKnife.bind(this);
        setTiltle("登录");
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    public boolean showLeftFragment() {
        return false;
    }

    @Override
    public void setListener() {
        btLogin.setOnClickListener(this);
        btRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login_registered:
                openActivity(RegisteredActivity.class,true);
                break;
            case R.id.bt_login_login:
                name = edName.getText().toString();
                pass = edPass.getText().toString();
                web = APPURL+"/user_login?ver=1&uid="+ name +"&pwd="+ pass +"&device=0";
                new Thread(){
                    @Override
                    public void run() {
                        s= POST.getIntent(web);

                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (s==null){
                                    Toast.makeText(LoginActivity.this, "密码或账号错误", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (s.equals("登录成功")) {
                                    SharedPreferences showLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
                                    boolean isLogin = showLogin.getBoolean("isLogin", true);
                                    showLogin.edit().putBoolean("isLogin", false).commit();
                                    openActivity(MeActivity.class, true);
                                }
                            }
                        });
                    }
                }.start();
                break;
            case R.id.bt_login_old:
                openActivity(OldPassActivity.class,true);
                break;
        }
    }



}
