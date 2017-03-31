package com.interfacetest.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Map;

/**
 * Created by han on 2017/3/31.
 */
public class Get extends Http {

    public Get(){
        super();
        setType(RequestType.GET);
    }

    public Http setParam(Object param) {
        this.param = param;
        if(url.contains("?")){
            setUrl(url + "&" + param);
        }else {
            setUrl(url + "?" + param);
        }
        return this;
    }

    public Request send(){

        HttpGet get = new HttpGet();
        get.setURI(URI.create(url));
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
        req = buildRequest(req, response);
        req.setRunTime(runTime);
        log.info(req);

        return req;
    }

}
