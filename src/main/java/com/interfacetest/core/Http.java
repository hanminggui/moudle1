package com.interfacetest.core;

import com.interfasetest.util.Common;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * Created by han on 2017/3/3.
 * 提供GET和POST请求发送功能、设置header功能
 */
public class Http {

    private static final String SPLIT_ENTRY = "&";
    private static final String SPLIT_KEY_VALUE = "=";
    private String url;
    private Logger log = Logger.getLogger(this.getClass());
    private String encode = "utf-8";
    private List<NameValuePair> param = new ArrayList<>();
    private Map<Object, Object> headers = new HashMap<>();
    private CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();



    public Http(){
    }

    public Http(String url){
        setUrl(url);
    }

    public Http(String url, String param) {
        this(url);
        this.setParams(param);
    }

    public Http(String url, String param, String headers) {
        this(url, param);
        this.setHeader(headers);
    }


    /**
     *
     * @param url
     * @return
     */
    public Http setUrl(String url){
        this.url = url;
        return this;
    }

    public String getUrl() {
        return url;
    }

    /**
     *
     * @return
     */
    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }


    /**
     *
     * @param params
     * @return
     */
    public Http setParams(String params){
        return setParams(Common.split(params, SPLIT_ENTRY, SPLIT_KEY_VALUE));
    }

    public Http setParams(Map<Object, Object> params){
        this.param = mapToParam(params);
        return this;
    }

    public Http addParam(String key, String value){
        this.param.add(new BasicNameValuePair(key, value));
        return this;
    }

    public Http addParam(String keyValue){
        String[] keyAndValue = keyValue.split(this.SPLIT_KEY_VALUE);
        return addParam(keyAndValue[0], keyAndValue[1]);
    }

    public List<NameValuePair> getParam() {
        return param;
    }


    /**
     *
     * @param headers
     * @return
     */
    public Http setHeaders(String headers){
        return setHeaders(Common.split(headers, SPLIT_ENTRY, SPLIT_KEY_VALUE));
    }

    public Http setHeaders(Map<Object, Object> headers){
        this.headers = headers;
        return this;
    }

    public Http setHeader(String key, String value){
        this.headers.clear();
        this.headers.put(key, value);
        return this;
    }

    public Http setHeader(String header){
        String[] keyAndValue = header.split(this.SPLIT_KEY_VALUE);
        return setHeader(keyAndValue[0], keyAndValue[1]);
    }

    public Http addHeader(String header){
        String[] keyAndValue = header.split(this.SPLIT_KEY_VALUE);
        return addHeader(keyAndValue[0], keyAndValue[1]);
    }

    public Http addHeader(String key, String value){
        this.headers.put(key, value);
        return this;
    }

    public Map<Object, Object> getHeaders() {
        return headers;
    }


    /**
     * map转成http请求类型的List
     * @param map
     * @return
     */
    private List<NameValuePair> mapToParam(Map<Object, Object> map){
        List<NameValuePair> param = new ArrayList<>();
        for (Map.Entry<Object,Object> entry : map.entrySet()) {
            log.info("参数：\"" +entry.getKey() + "\":\"" + entry.getValue() + "\"");
            param.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }
        return param;
    }


    /**
     * doGet
     * @return
     */
    public Request get(){

        HttpGet get = new HttpGet();
        get.setURI(URI.create(this.url));
        for(Map.Entry<Object, Object> entry : headers.entrySet()){
            get.addHeader(entry.getKey().toString(), entry.getKey().toString());
        }

        HttpResponse response = null;

        Long beginTime = new Date().getTime();
        try {
            response = closeableHttpClient.execute(get);

        } catch (IOException e) {
            e.printStackTrace();
        }

        long runTime = new Date().getTime() - beginTime;

        Request req = new Request();
        req.setRequestType(RequestType.POST);
        req.setUrl(this.url);
        req.setResult(response.getEntity().toString());
        req.setCode(response.getStatusLine().getStatusCode());
        req.setRunTime(runTime);

        return req;
    }

    public Request get(String url){
        setUrl(url);
        return get();
    }

    public Request get(String url, String headers){
        setHeaders(headers);
        return get(url);
    }

    

    /**
     * doPost
     * @return
     */
    public Request post(){
        HttpPost post = new HttpPost();
        post.setURI(URI.create(this.url));
        HttpEntity entity = new UrlEncodedFormEntity(this.param,UTF_8);
        post.setEntity(entity);
        for(Map.Entry<Object, Object> entry : headers.entrySet()){
            post.addHeader(entry.getKey().toString(), entry.getKey().toString());
        }

        HttpResponse response = null;

        Long beginTime = new Date().getTime();
        try {
            response = closeableHttpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long runTime = new Date().getTime() - beginTime;

        Request req = new Request();
        req.setRequestType(RequestType.POST);
        req.setUrl(this.url);
        req.setResult(response.getEntity().toString());
        req.setCode(response.getStatusLine().getStatusCode());
        req.setRunTime(runTime);

        return req;
    }

    public Request post(String url){
        setUrl(url);
        return post();
    }

    public Request post(String url, String param){
        setParams(param);
        return post(url);
    }

    public Request post(String url, String param, String header){
        setHeader(header);
        return post(url, param);
    }


}
