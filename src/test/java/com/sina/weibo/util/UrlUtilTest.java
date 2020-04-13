package com.sina.weibo.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlUtilTest {

    @Test
    public void getUrl() {


    }

    @Test
    public void testGetUrl() {
        String url = UrlUtil.getUrl("新冠肺炎","2020-2-1", "2020-2-2", 1);
        System.out.println(url);
    }
}