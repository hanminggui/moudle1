package com.interfacetest.core;

import com.interfacetest.util.Common;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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


    /**
     * 无参构造方法
     */
    public Http(){
    }

    /**
     * 构造时设置url
     *
     * @param url
     *  请求的url
     */
    public Http(String url){
        this();
        setUrl(url);
    }

    /**
     * 构造时设置url和参数
     *
     * @param url
     *  请求的url
     *
     * @param param
     *  请求的参数
     */
    public Http(String url, String param) {
        this(url);
        this.setParams(param);
    }

    /**
     * 构造时设置url和参数、请求头
     *
     * @param url
     *  请求的url
     *
     * @param param
     *  请求的参数
     *
     * @param headers
     *  请求头
     */
    public Http(String url, String param, String headers) {
        this(url, param);
        this.setHeaders(headers);
    }


    /**
     * 设置url
     *
     * @param url
     *  请求的url
     *
     * @return
     *  this
     */
    public Http setUrl(String url){
        this.url = url;
        return this;
    }

    /**
     * 取出url
     *
     * @return
     *  url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 取出编码设置
     *
     * @return
     *  encode
     */
    public String getEncode() {
        return encode;
    }

    /**
     * 设置编码
     *
     * @param encode
     *  encode
     */
    public void setEncode(String encode) {
        this.encode = encode;
    }


    /**
     * 批量设置参数(覆盖)
     *
     * @param params
     *  key=value&key1=value1&key2=value2 格式的参数
     *
     * @return
     *  this
     */
    public Http setParams(String params){
        return setParams(Common.split(params, SPLIT_ENTRY, SPLIT_KEY_VALUE));
    }

    /**
     * 批量设置参数(覆盖)
     *
     * @param params
     *  Map<Object, Object> 类型的参数
     *
     * @return
     *  this
     */
    public Http setParams(Map<Object, Object> params){
        this.param = mapToParam(params);
        return this;
    }

    /**
     * 增加一个参数(增量)
     *
     * @param key
     *  参数名
     *
     * @param value
     *  参数值
     *
     * @return
     *  this
     */
    public Http addParam(String key, String value){
        this.param.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * 增加一个参数(增量)
     *
     * @param keyValue
     *  key=value 格式的参数
     *
     * @return
     *  this
     */
    public Http addParam(String keyValue){
        String[] keyAndValue = keyValue.split(this.SPLIT_KEY_VALUE);
        return addParam(keyAndValue[0], keyAndValue[1]);
    }

    /**
     * 取出参数
     *
     * @return
     *  List<NameValuePair>参数集合
     */
    public List<NameValuePair> getParam() {
        return param;
    }


    /**
     * 设置请求头(覆盖)
     *
     * @param headers
     *  key=value&key1=value1&key2=value2 格式的请求头
     *
     * @return
     *  this
     */
    public Http setHeaders(String headers){
        return setHeaders(Common.split(headers, SPLIT_ENTRY, SPLIT_KEY_VALUE));
    }

    /**
     * 设置请求头(覆盖)
     *
     * @param headers
     *  Map<Object, Object> 类型的请求头
     *
     * @return
     * this
     */
    public Http setHeaders(Map<Object, Object> headers){
        this.headers = headers;
        return this;
    }

    /**
     * 增加请求头(增量)
     *
     * @param header
     *  key=value 格式的请求头
     *
     * @return
     *  this
     */
    public Http addHeader(String header){
        String[] keyAndValue = header.split(this.SPLIT_KEY_VALUE);
        return addHeader(keyAndValue[0], keyAndValue[1]);
    }

    /**
     * 增加请求头(增量)
     *
     * @param key
     *  header的key
     *
     * @param value
     *  header的value
     *
     * @return
     *  this
     */
    public Http addHeader(String key, String value){
        this.headers.put(key, value);
        return this;
    }

    /**
     * 取出headers
     *
     * @return
     *  Map<Object, Object> headers
     */
    public Map<Object, Object> getHeaders() {
        return headers;
    }


    /**
     * Map<Object, Object>转成List<NameValuePair>
     *
     * @param map
     *  Map<Object, Object> map
     *
     * @return
     *  List<NameValuePair>
     */
    private List<NameValuePair> mapToParam(Map<Object, Object> map){
        List<NameValuePair> param = new ArrayList<>();
        for (Map.Entry<Object,Object> entry : map.entrySet()) {
//            log.info("参数：\"" +entry.getKey() + "\":\"" + entry.getValue() + "\"");
            param.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }
        return param;
    }


    /**
     * 发送get请求
     *
     * @return
     *  Request对象
     *  包含：
     *      请求类型
     *      url
     *      返回结果
     *      状态码
     *      响应时间
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

        try {
            req.setResult(EntityUtils.toString(response.getEntity(), encode));
        } catch (IOException e) {
            e.printStackTrace();
        }

        req.setCode(response.getStatusLine().getStatusCode());
        req.setRunTime(runTime);

        log.info(req);

        return req;
    }

    /**
     * 发送get请求时设置url
     *
     * @param url
     *  url
     *
     * @return
     *  和无参get()一样
     */
    public Request get(String url){
        setUrl(url);
        return get();
    }

    /**
     * 发送get请求时设置url、请求头
     *
     * @param url
     *  url
     *
     * @param headers
     *  请求头
     *
     * @return
     *  和无参get()一样
     */
    public Request get(String url, String headers){
        setHeaders(headers);
        return get(url);
    }



    /**
     * 发送post请求
     *
     * @return
     *  Request对象
     *  包含：
     *      请求类型
     *      url
     *      参数
     *      返回结果
     *      状态码
     *      响应时间
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
        req.setParam(entity.toString()); //现在保存的参数还有问题 需要修改

        try {
            req.setResult(EntityUtils.toString(response.getEntity(), encode));
        } catch (IOException e) {
            e.printStackTrace();
        }

        req.setCode(response.getStatusLine().getStatusCode());
        req.setRunTime(runTime);

        log.info(req);

        return req;
    }

    /**
     * 发送post请求时设置url
     *
     * @param url
     *  url
     *
     * @return
     *  和无参post()一样
     */
    public Request post(String url){
        setUrl(url);
        return post();
    }

    /**
     * 发送post请求时设置url、参数
     *
     * @param url
     *  url
     *
     * @param param
     *  参数
     *
     * @return
     *  和无参post()一样
     */
    public Request post(String url, String param){
        setParams(param);
        return post(url);
    }

    /**
     * 发送post请求时设置url、参数、请求头
     *
     * @param url
     *  url
     *
     * @param param
     *  参数
     *
     * @param header
     *  请求头
     *
     * @return
     *  和无参post()一样
     */
    public Request post(String url, String param, String header){
        setHeaders(header);
        return post(url, param);
    }


}
