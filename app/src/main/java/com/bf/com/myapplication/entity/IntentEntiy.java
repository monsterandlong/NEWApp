package com.bf.com.myapplication.entity;

import android.graphics.Bitmap;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class IntentEntiy {
    public String title;
    public String time;
    public String docurl;
    private String ioce;
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap bitmap;

    public IntentEntiy(String title, String time, String docurl, Bitmap bitmap,String ioce) {
        this.ioce = ioce;
        this.title = title;
        this.time = time;
        this.docurl = docurl;
        this.bitmap = bitmap;
    }
    public IntentEntiy(){}

    public String getIoce() {
        return ioce;
    }

    public void setIoce(String ioce) {
        this.ioce = ioce;
    }

}
