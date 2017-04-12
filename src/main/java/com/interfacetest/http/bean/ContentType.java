package com.interfacetest.http.bean;

/**
 * Created by han on 2017/4/12.
 */
public enum ContentType {
    KEY_VALUE,
    JSON,
    HTML,
    XHTML,
    FORM_DATA;

    public String getContentType(ContentType ct){
        switch (ct){
            case FORM_DATA:
                return "multipart/form-data";
            case HTML:
                return "text/html";
            case XHTML:
                return "application/xhtml+xml";
            case JSON:
                return "application/json";
            case KEY_VALUE:
                return "application/x-www-form-urlencoded";
        }
        return null;
    }
}
