package com.bf.com.myapplication.manger;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class POST {

    private static String s;
    private static String explain;

    public static String getIntent(String web){

        Log.e("s","wu");
        try {
            URL url=new URL(web);
            Log.e("s","s");
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
                s = output.readLine();
                JSONObject json=new JSONObject(s);
                JSONObject sub=json.getJSONObject("data");
                String message=json.getString("message");
                explain = sub.getString("explain");
                Log.e("sss","explain="+explain);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return explain;
    }

}
