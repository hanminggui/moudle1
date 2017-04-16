package com.interfacetest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by han on 2017/3/3.
 */
public class JsonHelper {

    public static String getValue(String json, String keys){
        return getValue(json, keys.split("\\."));
    }

    /**
     *
     * 取出Json中指定的值
     * 支持所有情况的格式
     * @param json  目标json
     * @param keys  要取出值的key的完整路径 例如：
     *              json={"a":[{"b":1},{"c":"c"}],"d":{"e":"e"}}
     *              keys=d.e return e
     *              keys=a[0].b  return 1        可以写全路径key加数组下标(从0开始)
     *              keys=a.b  return 1          如果路径中包含数组，没有写数组下标默认取第0个数组里的key
     *              keys=a[1].c  return c
     *              keys=a.c  return null       路径中包含数组时第0个数组里没有key=c所以返回null
     * @return
     */
    public static String getValue(String json, String keys[]) {
        if (null == keys || keys.length == 0) {
            return null;
        }

        for(int i=0; i<keys.length; i++){
            if(hasIndex(keys[i])){
                if(isIndex(keys[i])){
                    json = JSON.parseArray(json).getString(getIndex(keys[i]));
                }else {
                    String keyName = keys[i].substring(0, keys[i].indexOf("["));
                    int keyIndex = getIndex(keys[i]);
                    json = JSON.parseObject(json).getJSONArray(keyName).getString(keyIndex);
                }
            }else {
                try{
                    json = JSON.parseObject(json).getString(keys[i]);
                }catch (ClassCastException e){
                    json = JSON.parseObject(JSON.parseArray(json).getString(0)).getString(keys[i]);
                }
            }
        }

        return json+ "";
    }

    private static boolean hasIndex(String str){
        return str.contains("[") && str.contains("]");
    }

    private static boolean isIndex(String str){
        return str.startsWith("[") && str.endsWith("]");
    }

    private static int getIndex(String index){
        return Integer.parseInt(index.substring(index.indexOf("[")+1, index.indexOf("]")));
    }

}
