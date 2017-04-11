package com.interfacetest.http;

import com.interfacetest.util.Common;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by han on 2017/3/31.
 */
public abstract class Http {

    //拆分参数默认字符
    protected static String SPLIT_ENTRY = "&";
    protected static String SPLIT_KEY_VALUE = "=";

    /**
     * 编码默认为utf-8
     */
    protected String encode = "utf-8";

    protected String host;
    protected String path;
    protected String url;
    protected Object param;
    protected RequestType type;
    protected Map<Object, Object> headers = new HashMap<>();

    protected CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

    protected Logger log = Logger.getLogger(this.getClass());


    public abstract Request send();

    /**
     * 无参构造方法
     */
    public Http(){
        Properties pro = Common.getConfig("http.properties");
        SPLIT_ENTRY = pro.getProperty("http.split.entry") != null ? pro.getProperty("http.split.entry") : SPLIT_ENTRY;
        SPLIT_KEY_VALUE = pro.getProperty("http.split.key.value") != null ? pro.getProperty("http.split.key.value") : SPLIT_KEY_VALUE;
        encode = pro.getProperty("http.encode") != null ? pro.getProperty("http.encode") : encode;
        setHost(pro.getProperty(pro.getProperty("http.host")));
    }


    /**
     * 取出域名
     *
     * @return
     *  配置的域名
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置域名
     *
     * @param host
     * 指定域名
     */
    public Http setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * 取出请求路径
     *
     * @return
     * 请求路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置请求路径
     *
     * @param path
     * 路径
     */
    public Http setPath(String path) {
        this.path = path;
        this.setUrl(getHost() + getPath());
        return this;
    }

    /**
     * 设置url
     *
     * @param url
     *  请求的url
     *
     * @return
     *  this
     */
    public Http setUrl(String url){
        this.url = url;
        return this;
    }

    /**
     * 取出url
     *
     * @return
     *  url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 取出编码设置
     *
     * @return
     *  encode
     */
    public String getEncode() {
        return encode;
    }

    /**
     * 设置编码
     *
     * @param encode
     *  encode
     */
    public Http setEncode(String encode) {
        this.encode = encode;
        return this;
    }

    public static String getSplitEntry() {
        return SPLIT_ENTRY;
    }

    public Http setSplitEntry(String splitEntry) {
        SPLIT_ENTRY = splitEntry;
        return this;
    }

    public static String getSplitKeyValue() {
        return SPLIT_KEY_VALUE;
    }

    public Http setSplitKeyValue(String splitKeyValue) {
        SPLIT_KEY_VALUE = splitKeyValue;
        return this;
    }

    public Object getParam() {
        return param;
    }

    public Http setParam(Object param) {
        this.param = param;
        return this;
    }

    /**
     * 增加一个参数(增量)
     *
     * @param key
     *  参数名
     *
     * @param value
     *  参数值
     *
     * @return
     *  this
     */
    public Http addParam(String key, String value){
        return this;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    /**
     * 设置请求头(覆盖)
     *
     * @param headers
     *  key=value&key1=value1&key2=value2 格式的请求头
     *
     * @return
     *  this
     */
    public Http setHeaders(String headers){
        return setHeaders(Common.split(headers, SPLIT_ENTRY, SPLIT_KEY_VALUE));
    }

    /**
     * 设置请求头(覆盖)
     *
     * @param headers
     *  Map<Object, Object> 类型的请求头
     *
     * @return
     * this
     */
    public Http setHeaders(Map<Object, Object> headers){
        this.headers = headers;
        return this;
    }

    /**
     * 增加请求头(增量)
     *
     * @param header
     *  key=value 格式的请求头
     *
     * @return
     *  this
     */
    public Http addHeader(String header){
        String[] keyAndValue = header.split(this.SPLIT_KEY_VALUE);
        return addHeader(keyAndValue[0], keyAndValue[1]);
    }

    /**
     * 增加请求头(增量)
     *
     * @param key
     *  header的key
     *
     * @param value
     *  header的value
     *
     * @return
     *  this
     */
    public Http addHeader(String key, String value){
        this.headers.put(key, value);
        return this;
    }

    /**
     * 取出headers
     *
     * @return
     *  Map<Object, Object> headers
     */
    public Map<Object, Object> getHeaders() {
        return headers;
    }


    /**
     * header数组 转 Map<Object, Object>
     *
     * @param headers
     *  待转换的headers
     *
     * @return
     * Map<Object, Object> headers
     */
    protected Map<Object, Object> arrayToMap(Header[] headers){
        Map<Object, Object> mapHeaders = new HashMap<>();
        for (int i=0; i<headers.length; i++){
            mapHeaders.put(headers[i].getName(), headers[i].getValue());
        }
        return mapHeaders;
    }

    /**
     * 填充通用字段
     * @param req
     * @return
     */
    protected Request buildRequest(Request req, HttpResponse response){
        if (getUrl() != null) req.setUrl(getUrl().toString());
        if (getParam() != null) req.setParam(getParam().toString());
        if(getHeaders() != null) req.setHeader(getHeaders().toString());
        if(getType() != null) req.setType(getType());
        if(null != response){

            req.setResultHeader(arrayToMap(response.getAllHeaders()).toString());
            req.setStatus(response.getStatusLine().getStatusCode());
            try {
                req.setResult(EntityUtils.toString(response.getEntity(), encode));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return req;
    }


}
