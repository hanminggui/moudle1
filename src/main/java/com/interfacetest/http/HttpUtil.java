package com.interfacetest.http;

import com.interfacetest.http.bean.Request;
import com.interfacetest.http.enums.ContentType;
import org.apache.http.client.methods.*;

import java.net.URI;

/**
 * Created by han on 2017/4/12.
 */
public class HttpUtil {

    //拆分参数默认字符
    protected static String SPLIT_ENTRY = "&";
    protected static String SPLIT_KEY_VALUE = "=";

    public void send(Request req){
        HttpRequestBase httpRequest = null;
        switch (req.getRequestMethod()){
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

        httpRequest.setURI(URI.create(req.getUrl()));

        //set header
        httpRequest.setHeader("Content-Type", ContentType.getContentType(req.getContentType()));
        if(req.getHeader() != null){
            String headers2[] = req.getHeader().split(SPLIT_ENTRY);
            for(int i=0; i<headers2.length; i++){
                String header3[] = headers2[i].split(SPLIT_KEY_VALUE);
                httpRequest.setHeader(header3[0], header3[1]);
            }

        }


        //set param



        //send



        //get result code




        //get result header




        //get result body




        //


    }


    public void doPost(Request req){

    }

    public void doGet(Request req){

    }

    public void doPut(Request req){

    }

    public void doDelete(Request req){

    }

    public void doOptions(Request req){

    }

}
