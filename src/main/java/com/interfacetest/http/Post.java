package com.interfacetest.http;

import com.interfacetest.util.Common;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * Created by han on 2017/3/31.
 */
public class Post extends Http {

    public Post(){
        super();
        setType(RequestType.POST);
    }

    private List<NameValuePair> param = new ArrayList<>();

    @Override
    public HttpRequestBase build() {
        HttpPost post = new HttpPost();
        HttpEntity entity = new UrlEncodedFormEntity(param,UTF_8);
        post.setEntity(entity);
        return post;
    }

    /**
     * 批量设置参数(覆盖)
     *
     * @param params
     *  key=value&key1=value1&key2=value2 格式的参数
     *
     * @return
     *  this
     */
    public Http setParam(String params){
        return setParam(Common.split(params, SPLIT_ENTRY, SPLIT_KEY_VALUE));
    }

    /**
     * 批量设置参数(覆盖)
     *
     * @param params
     *  Map<Object, Object> 类型的参数
     *
     * @return
     *  this
     */
    public Http setParam(Map<Object, Object> params){
        this.param = mapToParam(params);
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
        this.param.add(new BasicNameValuePair(key, value));
        return this;
    }

    /**
     * 增加一个参数(增量)
     *
     * @param keyValue
     *  key=value 格式的参数
     *
     * @return
     *  this
     */
    public Http addParam(String keyValue){
        String[] keyAndValue = keyValue.split(this.SPLIT_KEY_VALUE);
        return addParam(keyAndValue[0], keyAndValue[1]);
    }

    /**
     * 取出参数
     *
     * @return
     *  List<NameValuePair>参数集合
     */
    public List<NameValuePair> getParam() {
        return param;
    }



    /**
     * Map<Object, Object>转成List<NameValuePair>
     *
     * @param map
     *  Map<Object, Object> map
     *
     * @return
     *  List<NameValuePair>
     */
    private List<NameValuePair> mapToParam(Map<Object, Object> map){
        List<NameValuePair> param = new ArrayList<>();
        for (Map.Entry<Object,Object> entry : map.entrySet()) {
            param.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }
        return param;
    }

}
