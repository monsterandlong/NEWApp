package com.bf.com.myapplication.manger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bf.com.myapplication.entity.IntentEntiy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class InIntent {
    private static List<IntentEntiy> data;
    private static ByteArrayOutputStream bos;

    public static List<IntentEntiy> getIntent(String web,int length){
        data = new ArrayList<>();

        try {
            URL url=new URL(web);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("Aaccept-Charset","utf-8");
            int code = connection.getResponseCode();
            if (code==200){
                BufferedReader output=new BufferedReader(new InputStreamReader
                        (connection.getInputStream(),"UTF-8"));
                String s = output.readLine();
                JSONObject object= new JSONObject(s);
                JSONArray array=object.getJSONArray("list");
                for(int i=0;i<length;i++){
                    JSONObject sub=array.getJSONObject(i);
                    String title= sub.getString("title");
                    String time= sub.getString("time");
                    String docurl= sub.getString("docurl");
                    String img= sub.getString("imgurl");
                    Bitmap bitmap=getImg(img);
                    IntentEntiy listBean=new IntentEntiy(title,time,docurl,bitmap,img);
                    data.add(listBean);
                }
            }else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    public static Bitmap getImg(String web){
        try {
            URL url=new URL(web);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("Aaccept-Charset","utf-8");
            int code = connection.getResponseCode();
            if (code==200){
                InputStream input=connection.getInputStream();
                byte[] buffer=new byte[1024];
                int len=0;
                bos = new ByteArrayOutputStream();
                while ((len=input.read(buffer))!=-1){
                    bos.write(buffer,0,len);
                }

            }else {
            }
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] data=bos.toByteArray();
        Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
        return bitmap;
    }
}
