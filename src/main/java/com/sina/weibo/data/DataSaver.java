package com.sina.weibo.data;

import com.alibaba.fastjson.JSON;
import com.sina.weibo.model.WeiboData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName DataSaver
 * @Author caozj
 * @Date 2020/3/20 21:21
 * @Version 1.0
 */
public class DataSaver {

    private static String path="D:/weiboData/";

    public static void saveJsonFile(List<WeiboData> dataList,String fileName){
        String filepath=path+fileName;
        File file=new File(filepath);

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter writer =null;
        try {
            writer=new FileWriter(file,true);
            for (WeiboData weiboData : dataList) {
                writer.write(JSON.toJSONString(weiboData));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void saveData(List<WeiboData> dataList,String fileName){
        String filepath=path+fileName;
        File file=new File(filepath);

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter writer =null;
        try {
            writer=new FileWriter(file,true);
            for (WeiboData weiboData : dataList) {
                writer.write(weiboData.toCSV());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
