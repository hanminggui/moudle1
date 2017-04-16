package com.interfacetest.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by han on 2017/3/31.
 */
public class Get extends Http {

    public Get(){
        super();
        setType(RequestType.GET);
    }

    @Override
    public HttpRequestBase build() {
        if(url.contains("?")){
            setUrl(url + "&" + param);
        }else {
            setUrl(url + "?" + param);
        }
        return new HttpGet();
    }

}
