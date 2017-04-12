package com.interfacetest.http;

import com.interfacetest.http.bean.Request;
import com.interfacetest.http.bean.RequestMethod;

/**
 * Created by han on 2017/3/31.
 */
public class JsonPost extends Http {

    public JsonPost(){
        super();
        setType(RequestMethod.POST);
    }

    public Request send(){

        return null;
    }

}
