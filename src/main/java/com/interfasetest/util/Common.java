package com.interfasetest.util;

/**
 * Created by han on 2017/3/3.
 */
public class Common {

    /**
     * 判断字符串是不是数字
     * @param str
     * @return
     */
    public static boolean isNum(String str){
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }


}
