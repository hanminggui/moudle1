package com.interfacetest.http;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * Created by han on 2017/3/31.
 */
public class JsonPost extends Http {

    @Override
    public HttpRequestBase build() {
        HttpPost post = new HttpPost();
        StringEntity entity = new StringEntity(param, UTF_8);
        post.setEntity(entity);
        return post;
    }

    public JsonPost(){
        super();
        setType(RequestType.POST);
    }

}
