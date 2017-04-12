package com.interfacetest.http.bean;

import com.alibaba.fastjson.JSON;

/**
 * Created by han on 2017/3/3.
 *
 * 请求对象
 */
public class Request {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String inHeader) {
        this.header = inHeader;
    }

    public String getResultHeader() {
        return resultHeader;
    }

    public void setResultHeader(String outHeader) {
        resultHeader = outHeader;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
//        return "Request{" +
//                "url='" + url + '\'' +
//                ", requestType=" + requestType +
//                ", code=" + code +
//                ", result='" + result + '\'' +
//                ", testResult=" + testResult +
//                ", testResultContent='" + testResultContent + '\'' +
//                ", taskId='" + taskId + '\'' +
//                ", runTime=" + runTime +
//                '}';
    }


    /**
     * id
     */
    private String id;

    /**
     * 接口id
     */
    private String interfaceId;

    /**
     * 请求方式
     */
    private RequestMethod requestMethod;

    /**
     * 请求的url
     */
    private String url;

    /**
     * 请求数据类型
     */
    private ContentType contentType;

    /**
     * 请求的参数
     */
    private String param;

    /**
     * 请求头
     */
    private String header;



    /**
     * 返回的头
     */
    private String resultHeader;

    /**
     * 返回的状态码
     */
    private int status;

    /**
     * 返回的结果
     */
    private String result;

    /**
     * 接口运行时间
     */
    private long runTime;
}
