package com.sina.weibo.util;

import static org.junit.Assert.*;

public class CookieUtilTest {

    @org.junit.Test
    public void getCookiesByName() {
        CookieUtil.getCookiesByName("weibo");
    }

    @org.junit.Test
    public void StringSub() {
        String str="转发 520";
        String s2 = "评论";

        if (str.length()==2) {
            System.out.println(0);
        }else {
            int index=str.indexOf(" ");
            String count=str.substring(index+1);
            System.out.println(Integer.parseInt(count));
        }
//        System.out.println(str.length());

    }


}