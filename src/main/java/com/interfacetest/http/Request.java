package com.interfacetest.core;

import com.alibaba.fastjson.JSON;

/**
 * Created by han on 2017/3/3.
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

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
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

    public boolean isTestResult() {
        return testResult;
    }

    public void setTestResult(boolean testResult) {
        this.testResult = testResult;
    }

    public String getTestResultContent() {
        return testResultContent;
    }

    public void setTestResultContent(String testResultContent) {
        this.testResultContent = testResultContent;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
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
     * 请求的url
     */
    private String url;

    /**
     * 请求的参数
     */
    private String param;

    private String header;

    private String resultHeader;

    private String entity;


    /**
     * 请求方式
     */
    private RequestType type;

    /**
     * 返回的状态码
     */
    private int status;

    /**
     * 返回的结果
     */
    private String result;

    /**
     * 测试结果
     */
    private boolean testResult;

    /**
     * 测试结果说明
     */
    private String testResultContent;

    /**
     * 本次测试唯一标识符
     */
    private String taskId;

    /**
     * 接口运行时间
     */
    private long runTime;
}
