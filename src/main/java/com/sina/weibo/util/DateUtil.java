package com.sina.weibo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Author caozj
 * @Date 2020/3/20 21:05
 * @Version 1.0
 */
public class DateUtil {
    public static String getFormatDateTime(Date date){

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(date);
        return s;

    }

    public static boolean equal(Date date1, Date date2){
        return date1.equals(date2);
    }

    public static String getFormatDate(Date date){

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(date);
        return s;

    }

    public static Date nextDay(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day+1);

        return  calendar.getTime();
    }

    public static Date parseDate(String string) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(string);
        return date;

    }

}
