package com.bf.com.myapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.adapter.XListAdapter;
import com.bf.com.myapplication.base.BaseAcyivity;
import com.bf.com.myapplication.entity.IntentEntiy;
import com.bf.com.myapplication.manger.InIntent;
import com.bf.com.myapplication.view.XListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class MainActivity extends BaseAcyivity implements XListView.IXListViewListener,AdapterView.OnItemClickListener{



    @BindView(R.id.listview)
    XListView listview;
    private boolean b;
    public List<IntentEntiy> data;
    private XListAdapter adapter;
    public int i=1;
    public int a=10;


    @Override
    public void initdata() {
        ShareSDK.initSDK(this);
        setTiltle("资讯");
        listview.setPullLoadEnable(true);
        listview.setPullRefreshEnable(true);
        new Thread() {
            @Override
            public void run() {
                data = InIntent.getIntent("http://wangyi.butterfly.mopaasapp.com/news/api?type=war&page="+i+"&limit=10", 10);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new XListAdapter(data);
                        listview.setAdapter(adapter);
                    }
                });
            }
        }.start();

    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    public boolean showLeftFragment() {
        return true;
    }

    @Override
    public void setListener() {
        listview.setXListViewListener(this);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b = !b;
                data = InIntent.getIntent("http://wangyi.butterfly.mopaasapp.com/news/api?type=war&page=1&limit=10", 10);
                adapter.notifyDataSetInvalidated();
                onLoad();

            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Thread(){
            @Override
            public void run() {
                i++;
                a=a+10;
                List<IntentEntiy> data1 = InIntent.getIntent("http://wangyi.butterfly.mopaasapp.com/news/api?type=war&page="+i+"&limit=10", a);
                Log.e("data1","data1集合="+data1);
                data.addAll(data1);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        Log.e("data1","daol");
                        onLoad();
                    }
                });

            }
        }.start();
    }

    public void onLoad() {
        listview.stopRefresh(true);
        listview.stopLoadMore();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-ddHH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        listview.setRefreshTime(str);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.img_loginFrag_login:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
                openActivity(LoginActivity.class,false);
                break;
            case R.id.rela_left_new:
                openActivity(MainActivity.class,false);
                break;
            case R.id.rela_left_favorite:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
                openActivity(FavoriteActivity.class,false);
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
            case R.id.img_loginFrag_QQ:
                Toast.makeText(this, "1111", Toast.LENGTH_SHORT).show();
                Platform qqPlatform = ShareSDK.getPlatform(QQ.NAME);
                qqPlatform.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                qqPlatform.authorize();
                break;
            case R.id.img_loginFrag_XL:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                weibo.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(MainActivity.this,GetMainActivity.class);
        IntentEntiy datas=data.get(position);
        String url=datas.getDocurl();
        intent.putExtra("url", url);
        intent.putExtra("title",datas.getTitle());
        intent.putExtra("ioce",datas.getIoce());
        intent.putExtra("time",datas.getTime());
        startActivity(intent);
    }

}
