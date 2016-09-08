package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UrlConnection;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MyUrlConnect {
    private String urlString; // http://www.baidu.com
    private String requestMethod;
    private String requestParameter;
    private int connectTimeOut;
    private int readTimeOut;

    private HttpURLConnection connection;
    private URL url;

    private DataOutputStream requsetOutStream;
    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private String resposeResult="请求中----";

    private boolean isok;

    Context context;
    public MyUrlConnect() {
        super();
        this.connectTimeOut = 10000;
        this.readTimeOut = 10000;
    }

    public MyUrlConnect(String urlString, String requestMethod, String requestParameter) {
        this.urlString = urlString;
        this.requestMethod = requestMethod;
        this.requestParameter = requestParameter;
        this.connectTimeOut = 10000;
        this.readTimeOut = 10000;
        this.context=context;
    }

    public void setUrl(String urlString) {
        this.urlString = urlString;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public String getResposeResult() {
        return this.resposeResult;
    }

    public boolean urlConnection(){
        connection=null;
        isok=false;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                try {
                    url=new URL(urlString);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod(requestMethod);
                    connection.setConnectTimeout(connectTimeOut);
                    connection.setReadTimeout(readTimeOut);
                    if(requestMethod.equals("POST")){
                        connection.setDoOutput(true);
                        requsetOutStream=new DataOutputStream(connection.getOutputStream());
                        requsetOutStream.writeBytes(requestParameter);
                        requsetOutStream.flush();
                        requsetOutStream.close();
                        Log.d("--------->","EXCUTED");
                    }
                    bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                    stringBuilder=new StringBuilder();
                    String readLine =null;
                    while ((readLine=bufferedReader.readLine()) != null) {
                        stringBuilder.append(readLine);
                    }
                    resposeResult=stringBuilder.toString();
                    isok=true;

                    //   bufferedReader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }

//            }
//
//        }).start();
        return isok;
    }

}
