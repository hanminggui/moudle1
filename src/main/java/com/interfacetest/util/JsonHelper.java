package com.interfacetest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by han on 2017/3/3.
 */
public class JsonHelper {

    /**
     *
     * 取出Json中指定的值
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
    public String getValue(String json, String keys[]){
        if(null == keys || keys.length == 0){
            return null;
        }

        JSONObject jo = JSON.parseObject(json);

        for(int i=0; i<keys.length; i++){

            if(i < keys.length-1){
                if(keys[i].contains("[") && keys[i].contains("]")){
                    String keyName = keys[i].substring(0, keys[i].indexOf("["));
                    int keyIndex = Integer.parseInt(keys[i].substring(keys[i].indexOf("[")+1, keys[i].indexOf("]")));
                    json = jo.getJSONArray(keyName).getString(keyIndex);
                }else {
                    json = jo.getString(keys[i]);
                }
            }
            try{
                jo = JSON.parseObject(json);
            }catch (ClassCastException e){
                json = jo.getJSONArray(keys[i]).getString(0);
                jo = JSON.parseObject(json);
            }

        }

        return jo.get(keys[keys.length-1]) + "";
    }

}
