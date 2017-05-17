package com.bf.com.myapplication.manger;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bf.com.myapplication.base.App;
import com.bf.com.myapplication.entity.DBfavorite;
import com.bf.com.myapplication.entity.IntentEntiy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class DButile {
    private static final String DB_PATH = "data/data/com.bf.com.myapplication/databases";
    private static final String DB_NAME = "newcom.db";


    static {
        copyDb();
    }

    private static int i = 0;

    private static void copyDb() {
        File path = new File(DB_PATH);
        if (!path.exists()) path.mkdirs();
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = App.appContent.getAssets().open("newcom.db");
            fos = new FileOutputStream(DB_PATH + "/" + DB_NAME);
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (is != null) is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //获取数据库
    public static SQLiteDatabase getDB(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + "/" + DB_NAME, null);
        return db;
    }
    //添加
    public  static void addDB(String title,String ioce,String time,String url){
        i++;
        Log.e("size","已经jia"+ i);
        getDB().execSQL("insert into url_list(id,title, ioce,time,url) values(?,?,?,?,?)",
                new Object[]{null,title,ioce,time,url});
    }
    //删除
    public static void deleteDB(String title){
        getDB().execSQL("delete from url_list where title=?", new Object[]{title});
    }
    //更新
    public void update(IntentEntiy intentEntiy){
        getDB().execSQL("update url_list set title=?,ioce=?,time=?,url=?, where id=?",
                new Object[]{intentEntiy.getTitle(), intentEntiy.getBitmap(), intentEntiy.getTime(),intentEntiy.getDocurl(),intentEntiy.getId()});
    }
    //查询
    public static List<DBfavorite> findDB(){
        List<DBfavorite> data=new ArrayList<>();
        Cursor cursor = getDB().rawQuery("select * from url_list",null);
        while (cursor.moveToNext()){
            i++;
            int UtilId = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String ioce = cursor.getString(cursor.getColumnIndex("ioce"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            DBfavorite list=new DBfavorite(title,ioce,time,url);
            data.add(list);

        }
        cursor.close();
        return data;
    }
}
