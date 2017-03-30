package com.interfacetest.util;

import com.interfacetest.encryption.Base64;
import com.interfacetest.encryption.Coder;
import com.interfacetest.encryption.RSAEncrypt;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // encryptMD5()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, Coder.encryptMD5(str.getBytes())).toString(16);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密出现错误");
        }
    }

    /**
     *
     * @param str
     *          待加密的字符串
     * @return
     *          加密后的字符串
     */
    public static String encryptByPublicKey(String str){
        try {
            byte[] cipherData = RSAEncrypt.encrypt(
                    RSAEncrypt.loadPublicKeyByStr(
                            RSAEncrypt.loadPublicKeyByFile(
                                    Common.class.getClassLoader().getResource("keys").
                                            getPath())),str.getBytes());
            return Base64.encode(cipherData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("RSA公钥加密出现错误");
        }
    }

    /**
     * 拆分字符串
     * @param str
     * @param s1
     * @param s2
     * @return
     *  map
     */
    public static Map<Object, Object> split(String str, String s1, String s2){
        String strs[] = str.split(s1);
        Map<Object, Object> mapStrs = new HashMap<>();
        for(int i=0; i<strs.length; i++){
            String[] strsI = strs[i].split(s2);
            mapStrs.put(strsI[0], strsI[1]);
        }

        return mapStrs;
    }



}
