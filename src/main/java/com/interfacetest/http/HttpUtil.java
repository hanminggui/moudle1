package com.interfacetest.http;

import com.interfacetest.http.bean.Request;
import org.apache.http.HttpConnection;
import org.apache.http.client.methods.*;

import java.net.URI;

/**
 * Created by han on 2017/4/12.
 */
public class HttpUtil {

    public void send(Request request){
        HttpRequestBase httpRequest = null;
        switch (request.getRequestMethod()){
            case GET:
                httpRequest = new HttpGet();
                break;
            case POST:
                httpRequest = new HttpPost();
                break;
            case PUT:
                httpRequest = new HttpPut();
                break;
            case DELETE:
                httpRequest = new HttpDelete();
                break;
            case OPTIONS:
                httpRequest = new HttpOptions();
                break;
        }

        httpRequest.setURI(URI.create(request.getUrl()));



    }
}
