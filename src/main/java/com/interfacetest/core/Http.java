package com.interfacetest.core;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * Created by han on 2017/3/3.
 * 提供GET和POST请求发送功能、设置header功能
 */
public class Http {
    private String url;
    private String param;
    private String header;
    private String encode = "utf-8";


    public Http(){
    }
    public Http(String url){
        this.url=url;
    }

    public Http(String url, String param) {
        this.url = url;
        this.param = param;
    }

    public Http(String url, String param, String headers) {
        this.url = url;
        this.param = param;
        this.header = headers;
    }

    public Http setUrl(String url){
        this.url = url;
        return this;
    }

    public Http setParam(String param){
        this.param = param;
        return this;
    }

    public Http setHeader(String header){
        this.header = header;
        return this;
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
        response.getEntity();


        return null;
    }


    public void post(){
        /**
         * set header and param
         */

        HttpPost post = new HttpPost(this.url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("1", "2"));

        HttpEntity param = new UrlEncodedFormEntity(params,UTF_8);
        post.setEntity(param);

    }

    public void post(String url){
        setUrl(url);
        post();
    }

    public void post(String url, String param){
        setParam(param);
        post(url);
    }

    public void post(String url, String param, String header){
        setHeader(header);
        post(url, param);
    }

    public void addHeader(){

    }


    public String post(String s, Map<String, String> params) {
        return null;
    }


}
