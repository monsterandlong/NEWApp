package com.bf.com.myapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.adapter.ListFavorite;
import com.bf.com.myapplication.base.BaseAcyivity;
import com.bf.com.myapplication.entity.DBfavorite;
import com.bf.com.myapplication.manger.DButile;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends BaseAcyivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    @BindView(R.id.list_favorite)
    ListView listFavorite;
    private List<DBfavorite> data;
    private DBfavorite datas;
    private ListFavorite adapter;

    @Override
    public void initdata() {
        ButterKnife.bind(this);
        setTiltle("收藏新闻");
        data = DButile.findDB();
        adapter = new ListFavorite(data);
        listFavorite.setAdapter(adapter);
        Log.e("size","size="+ data.size());

    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_favorite;
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
        listFavorite.setOnItemClickListener(this);
        listFavorite.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(FavoriteActivity.this,GetMainActivity.class);
        datas = data.get(position);
        String url= datas.getDocurl();
        intent.putExtra("url", url);
        startActivity(intent);
        Log.e("title","title10="+datas.getTitle());
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        datas=data.get(position);
        int xId=position;
        String title=datas.getTitle();
        showNormalDialog(title,xId);
        return true;
    }
    private void showNormalDialog(final String title, final int xId){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(FavoriteActivity.this);
        normalDialog.setTitle("选择是否删除");
        normalDialog.setMessage("删除本收藏");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DButile.deleteDB(title);
                        data.remove(xId);
                        adapter.notifyDataSetChanged();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.create().show();
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.img_loginFrag_login:
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

        }
    }


}
