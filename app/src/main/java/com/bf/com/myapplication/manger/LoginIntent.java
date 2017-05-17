package com.bf.com.myapplication.manger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class LoginIntent {
    public static void post(String web, HashMap<String,Object> map){
        try {
            String str = "user="+ URLEncoder.encode(URLEncoder.encode(String.valueOf(map), "UTF-8"),"UTF-8");
            URL url=new URL(web);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            //设置连接超时,2000ms
            connection.setConnectTimeout(2000);
            //设置指定时间内服务器没有返回数据的超时，5000ms
            connection.setReadTimeout(5000);
            //设置允许输出
            connection.setDoOutput(true);
            //设置请求的方式
            connection.setRequestMethod("POST");
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(map);//写入输出流
            out.flush();//立即刷新
            out.close();


//                if(SUCCESS_RESPONSE.equals(successResponse)){
//                    //上传成功
//                }
//
//            } else{
//
//                //上传失败
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void get(String web){
        try {
            URL url=new URL(web);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            //设置连接超时,2000ms
            connection.setConnectTimeout(2000);
            //设置请求的方式
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("Aaccept-Charset","utf-8");
            int code = connection.getResponseCode();
            if(code == 200){
                BufferedReader output=new BufferedReader(new InputStreamReader
                        (connection.getInputStream(),"UTF-8"));
                String serverResponse = output.readLine();
                output.close();
                //对返回的数据serverResponse进行操作

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
