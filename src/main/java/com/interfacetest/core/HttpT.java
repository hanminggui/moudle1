package com.interfacetest.core;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * Created by song on 17/3/28.
 * 提供GET和POST请求发送功能、设置header功能
 */
public class HttpT {
    private String url;
    private String encode = "utf-8";
    private Map<Object,Object> params;
    public Logger log = Logger.getLogger(this.getClass());
    Request req = new Request();
    List<NameValuePair> param = new ArrayList<NameValuePair>();


    public HttpT() {}

    public HttpT(String url) {
        this.url = url;
        req.setUrl(url);
    }

    public HttpT(String url,Map<Object,Object> params) {
        this.url = url;
        req.setUrl(url);
        this.params = params;
    }

    static CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
    public Request get(String url){
        Long startTime,endTime;
        log.info("------------------------开始请求------------------------");
        log.info("接口URL为：" + url);
        startTime = new Date().getTime();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            endTime = new Date().getTime();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                log.info("接口响应返回值为：" + EntityUtils.toString(entity,encode));
            }
            log.info("接口响应时间为：" + (endTime - startTime) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                response.close();
                log.info("------------------------请求结束------------------------");
            } catch(IOException ioe) {
                log.error("关闭请求失败");
                ioe.printStackTrace();
            }
        }

        return null;
    }

    public void post() {
        Long startTime,endTime;
        log.info("------------------------开始请求------------------------");
        log.info("接口类型：post");
        log.info("接口URL为：" + url);
        startTime = new Date().getTime();
        HttpPost post = new HttpPost(url);
        post.setEntity(addParams(params));

        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(post);
            endTime = new Date().getTime();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                log.info("接口响应返回值为：" + EntityUtils.toString(entity,encode));
            }
            log.info("接口响应时间为：" + (endTime - startTime) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                //关闭连接，释放资源
                response.close();
                log.info("------------------------请求结束------------------------");
            } catch(IOException ioqe) {
                log.error("关闭请求失败");
                ioqe.printStackTrace();
            }
        }
    }


    /**
     * create by song
     * 循环添加参数
     */
    public HttpEntity addParams(Map<Object,Object> params) {
        for (Map.Entry<Object,Object> entry : params.entrySet()) {
            log.info("参数：\"" +entry.getKey() + "\":\"" + entry.getValue() + "\"");
            param.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }
        HttpEntity param1 = new UrlEncodedFormEntity(param,UTF_8);
        return param1;
    }

}
