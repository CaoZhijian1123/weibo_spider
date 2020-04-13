package com.sina.weibo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName UrlUtil
 * @Author caozj
 * @Date 2020/3/20 18:22
 * @Version 1.0
 */
public class UrlUtil {

    /**
     *
     * @param start Format "yyyy-MM-DD" like "2019-12-01"
     * @param end 2019-12-01
     * @param page
     * @return
     */
    public static String getUrl(String keyword,String start,String end,int page){
        return getUrl(keyword,start,0,end,0,page);
    }

    /**
     *
     * @param startDate 2019-12-01
     * @param startHour 0-23
     * @param endDate 2019-12-01
     * @param endHour
     * @param page 1-50
     * @return
     */
    public static String getUrl(String keyword,String startDate,int startHour,String endDate,int endHour,int page){
        startDate=startDate.trim();
        endDate=endDate.trim();

        String path= null;
        try {
            https://s.weibo.com/weibo?q=%E7%96%AB%E6%83%85&typeall=1&suball=1&timescope=custom:2020-03-09-0:2020-03-11&Refer=g
            path = "https://s.weibo.com/weibo?q="+ URLEncoder.encode(keyword,"UTF-8")+"&typeall=1&suball=1&timescope=custom:"+startDate+"-"+startHour+":"+endDate+"-"+endHour+"&Refer=g&page="+page;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return path;
    }

}
