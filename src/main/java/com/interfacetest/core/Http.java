package com.interfacetest.core;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Map;

/**
 * Created by han on 2017/3/3.
 * 提供GET和POST请求发送功能、设置header功能
 */
public class Http {
    private String url;
    private String param;
    private String header;

    public Http(){

    }
    public Http(String url){
        this.url=url;
    }

    public Http(String url, String param) {
    }

    public Http(String url, String param, String headers) {
    }

    public Request doPost(){

        return null;
    }

    static CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
    public Request doGet(){
        HttpGet httpGet = new HttpGet("http://www.cnblogs.com/loveyakamoz/archive/2011/07/21/2113252.html");
        HttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }



    public static String post(String s, Map<String, String> params) {
        return null;
    }
}
