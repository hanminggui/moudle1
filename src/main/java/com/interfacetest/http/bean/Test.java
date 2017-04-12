package com.interfacetest.http.bean;

/**
 * Created by han on 2017/4/12.
 *
 * 测试对象
 */
public class Test {

    /**
     * id
     */
    private String id;

    /**
     * 请求
     */
    private Request request;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
