package com.sina.weibo.data;

import com.sina.weibo.model.WeiboData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DataReader
 * @Author caozj
 * @Date 2020/3/29 0:29
 * @Version 1.0
 */
public class DataReader {
    private static String path="D:/weiboData/";
    public static List<WeiboData> loadData(String fileName){
        List<WeiboData> dataList=new ArrayList<>();
        String filepath=path+fileName;
        File file=new File(filepath);
        if (!file.exists()) return null;
        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new FileReader(file));
            String record=null;
            while ((record=reader.readLine())!=null){
                String[] strings = record.split("\t");
                WeiboData data=new WeiboData();
                data.setId(strings[0]);
                data.setName(strings[1]);
                data.setUrl(strings[2]);
                data.setPubTime(strings[3]);
                data.setFetchTime(strings[4]);
                data.setKeyWord(strings[5]);
                data.setContent(strings[6]);
                data.setSharedNo(Integer.parseInt(strings[7]));
                data.setComNo(Integer.parseInt(strings[8]));
                data.setLikeNo(Integer.parseInt(strings[9]));
                dataList.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return dataList;

    }
}
