package com.sina.weibo.util;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void getFormatDate() {
        System.out.println(DateUtil.getFormatDate(new Date()));
    }

    @Test
    public void nextDay() throws ParseException {
        Date date = DateUtil.nextDay(DateUtil.parseDate("2020-2-29"));
        System.out.println(DateUtil.getFormatDate(date));
    }
}