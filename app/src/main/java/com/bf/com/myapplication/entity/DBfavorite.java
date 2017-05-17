package com.bf.com.myapplication.entity;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class DBfavorite {
    private String title;
    private String ioce;
    private String time;
    private String docurl;
    private Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIoce() {
        return ioce;
    }

    public void setIoce(String ioce) {
        this.ioce = ioce;
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

    public DBfavorite(String title, String ioce, String time, String docurl) {
        this.title = title;
        this.ioce = ioce;
        this.time = time;
        this.docurl = docurl;

    }
}
