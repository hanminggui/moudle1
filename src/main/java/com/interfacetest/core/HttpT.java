package com.interfacetest.core;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
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
    public Request get(){
        //get请求的参数
        String param = "";
        //计算get请求的响应时间
        Long startTime,endTime;
        //设置请求超时时长
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
        log.info("------------------------开始请求------------------------");
        log.info("接口类型：get");
        log.info("接口URL为：" + url);
        /**
         * create by song
         * 设置get请求参数 并和url进行拼接为完整的请求地址
         */
        for (Map.Entry<Object,Object> entry : params.entrySet()) {
            if(!param.equals("")){
                param = param + "&";
            }
            param += entry.getKey().toString()+"="+entry.getValue().toString();
            //打请求印参数信息
            log.info("参数：\"" +entry.getKey() + "\":\"" + entry.getValue() + "\"");
        }
        url = url + "?" + param;
log.error(url);
        //创建httpGet对象
        HttpGet httpGet = new HttpGet(url);
        //设置请求配置
        httpGet.setConfig(requestConfig);
        //返回报告对象
        CloseableHttpResponse response = null;
        //请求开始时间
        startTime = new Date().getTime();
        try {
            response = closeableHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            endTime = new Date().getTime();
            if (entity != null) {
                log.info("接口响应返回值为：" + EntityUtils.toString(entity,encode));
            }
            log.info("接口响应时间为：" + (endTime - startTime) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(response!=null || !response.equals("")){
                    response.close();
                }
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
            HttpEntity entity = response.getEntity();
            endTime = new Date().getTime();
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
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        for (Map.Entry<Object,Object> entry : params.entrySet()) {
            log.info("参数：\"" +entry.getKey() + "\":\"" + entry.getValue() + "\"");
            param.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }
        HttpEntity entity = new UrlEncodedFormEntity(param,UTF_8);
        return entity;
    }

}
