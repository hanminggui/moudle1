package com.interfacetest.core;

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

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    /**
     * 请求的url
     */
    private String url;

    /**
     * 请求方式
     */
    private RequestType requestType;

    /**
     * 返回的状态码
     */
    private int code;

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
