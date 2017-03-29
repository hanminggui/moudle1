package com.interfacetest.core;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.Map;

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

    public HttpT() {}

    public HttpT(String url) {
        this.url = url;
        req.setUrl(url);
    }

    public String getURL() {
        return url;
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



        response.getEntity();

        return null;
    }


}
