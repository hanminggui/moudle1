package com.interfasetest.util;

import java.math.BigInteger;

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



}
