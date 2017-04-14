package com.interfacetest.http;

import com.interfacetest.http.bean.Request;
import com.interfacetest.http.enums.ContentType;
import com.interfacetest.http.enums.RequestMethod;
import com.interfacetest.util.Common;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
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
 * Created by han on 2017/4/12.
 */
public class HttpUtil {

    //拆分参数默认字符
    static String SPLIT_ENTRY = "&";
    static String SPLIT_KEY_VALUE = "=";
    Logger log = Logger.getLogger(this.getClass());
    CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

    public Request send(Request req){

        check(req);
        HttpRequestBase httpRequest = build(req);
        httpRequest = building(httpRequest, req);
        return sendAndLoad(httpRequest, req);
    }


    private void check(Request req){
        if(null == req.getUrl()){
            throw new RuntimeException("URL不能为空 \n" + req);
        }
        if(null == req.getRequestMethod()){
            throw new RuntimeException("请求方式不能为空 \n" + req);
        }
        if(null == req.getContentType() && req.getRequestMethod() != RequestMethod.GET){
            throw new RuntimeException("数据类型不能为空 \n" + req);
        }
    }

    private HttpRequestBase build(Request req){
        HttpRequestBase get = null;
        HttpEntityEnclosingRequestBase noGet = null;
        switch (req.getRequestMethod()){
            case GET:
                get = new HttpGet();
                if(null != req.getParam()){
                    String lxf = "?";
                    if(req.getUrl().contains("?")) {
                        lxf = "&";
                    }
                    req.setUrl(req.getUrl() + lxf + req.getParam());
                }
                break;
            case POST:
                noGet = new HttpPost();
                break;
            case PUT:
                noGet = new HttpPut();
                break;
            case DELETE:
                get = new HttpDelete();
                break;
            case OPTIONS:
                get = new HttpOptions();
                break;
        }

        if(noGet != null){

            if(req.getParam()!= null){

                switch (req.getContentType()){
                    case JSON:
                        noGet.setEntity(new StringEntity(req.getParam(),UTF_8));
                        break;
                    case KEY_VALUE:
                        Map<Object, Object> map = Common.split(req.getParam(), SPLIT_ENTRY, SPLIT_KEY_VALUE);
                        List<NameValuePair> param = new ArrayList<>();
                        for (Map.Entry<Object,Object> entry : map.entrySet()) {
                            param.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
                        }
                        HttpEntity entity = new UrlEncodedFormEntity(param,UTF_8);
                        noGet.setEntity(entity);
                        break;
                }

            }

            noGet.setURI(URI.create(req.getUrl()));
            return noGet;

        }else {

            get.setURI(URI.create(req.getUrl()));
            return get;

        }

    }



    private Request sendAndLoad(HttpRequestBase hrb, Request req){
        HttpResponse response = null;

        Long beginTime = new Date().getTime();
        try {
            response = closeableHttpClient.execute(hrb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long runTime = new Date().getTime() - beginTime;
        if(null != response){
            req.setResultHeader(arrayToMap(response.getAllHeaders()).toString());
            req.setStatus(response.getStatusLine().getStatusCode());
            try {
                req.setResult(EntityUtils.toString(response.getEntity(), UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        req.setRunTime(runTime);
        log.info(req);

        return req;
    }

    private HttpRequestBase building(HttpRequestBase hrb, Request req){

        if(null != req.getContentType()){
            hrb.addHeader("Content-Type", ContentType.getContentType(req.getContentType()));
        }
        if(null != req.getHeader()){
            Map<Object, Object> headers = Common.split(req.getHeader(), SPLIT_ENTRY, SPLIT_KEY_VALUE);
            for(Map.Entry<Object, Object> entry : headers.entrySet()){
                hrb.addHeader(entry.getKey().toString(), entry.getKey().toString());
            }
        }

        return hrb;
    }

    /**
     * header数组 转 Map<Object, Object>
     *
     * @param headers
     *  待转换的headers
     *
     * @return
     * Map<Object, Object> headers
     */
    protected Map<Object, Object> arrayToMap(Header[] headers){
        Map<Object, Object> mapHeaders = new HashMap<>();
        for (int i=0; i<headers.length; i++){
            mapHeaders.put(headers[i].getName(), headers[i].getValue());
        }
        return mapHeaders;
    }

}
