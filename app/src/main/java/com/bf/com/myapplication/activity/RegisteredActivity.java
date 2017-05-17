package com.bf.com.myapplication.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.base.App;
import com.bf.com.myapplication.base.BaseAcyivity;
import com.bf.com.myapplication.manger.POST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisteredActivity extends BaseAcyivity implements View.OnClickListener{
    public static final String APPURL = "http://118.244.212.82:9092/newsClient";
    public static final int VERSION_CODE = 1;
    @BindView(R.id.tv_registered_email)
    EditText tvEmail;
    @BindView(R.id.tv_registered_name)
    EditText tvName;
    @BindView(R.id.tv_registered_pass)
    EditText tvPass;
    @BindView(R.id.bt_registered_registered)
    Button btRegistered;
    @BindView(R.id.cb_registered)
    CheckBox cbRegistered;
    private String pass;
    private String email;
    private String name;
    private String web;
    String s;

    @Override
    public void initdata() {
        ButterKnife.bind(this);
        setTiltle("用户注册");

    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    public boolean showLeftFragment() {
        return false;
    }

//    public static String systime() {
//        SimpleDateFormat data = new SimpleDateFormat("MM-ddHH:mm:ss");
//        Date curdate = new Date(System.currentTimeMillis());
//        String systime = data.format(curdate);
//        return systime;
//    }


    //验证邮箱
    public static boolean verifyEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)" +
                "|(([a-zA-Z0-9\\-]+\\.)+))" +
                "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //密码
    public static boolean verifyPassword(String password) {
        Pattern pattern = Pattern
                .compile("^[a-zA-Z0-9]{6,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //版本号
    public static int getVersionCode() {
        try {
            PackageInfo pi = App.appContent.getPackageManager().getPackageInfo(App.appContent.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void setListener() {
        btRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_registered_registered:
                pass = tvPass.getText().toString();
                email = tvEmail.getText().toString();
                name = tvName.getText().toString();
                if (verifyPassword(pass)&&pass!=null){

                }else {
                    Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (verifyEmail(email)&&email!=null){

                }else {
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name!=null){

                }else {
                    Toast.makeText(this, "名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cbRegistered.isChecked()){

                }else {
                    Toast.makeText(this, "请勾选同意服务", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("s","ok");
                web = APPURL+"/user_register?ver=1.0&uid="+name+"&pwd="+pass+"&email="+email;
                Log.e("s","ok2");
                new Thread(){
                    @Override
                    public void run() {
                        s= POST.getIntent(web);
                        RegisteredActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (s==null){
                                    Toast.makeText(RegisteredActivity.this, "密码或账号错误", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (s.equals("注册成功")){
                                    Toast.makeText(RegisteredActivity.this, "OK", Toast.LENGTH_SHORT).show();
//                                    openActivity(MeActivity.class,true);
                                }
                            }
                        });

                    }
                }.start();

                break;
        }
    }



}
