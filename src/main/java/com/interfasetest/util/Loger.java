package com.interfasetest.util;

import java.util.Date;

/**
 * Created by han on 2017/3/3.
 */
public class Loger {

    public static void main(String[] args){

        Date befor = new Date();
        for(int i=0; i<1000000; i++){
            System.out.println("aaaaaaa" + i);
        }
        Date after = new Date();


        System.out.println(after.getTime()-befor.getTime());
    }

}
