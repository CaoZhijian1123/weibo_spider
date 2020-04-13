package com.sina.weibo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @ClassName cookieUtil
 * @Author caozj
 * @Date 2020/3/20 16:14
 * @Version 1.0
 */
public class CookieUtil {
    public static Map<String,String> getCookiesByName(String filename){
        String path=filename+".properties";
        Properties properties=new Properties();
        Map<String,String> map=new HashMap<>();

        try {
            properties.load(CookieUtil.class.getClassLoader().getResourceAsStream(path));

//            properties.list(System.out);

            Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                map.put((String)entry.getKey(),(String)entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
