package com.sina.weibo.data;

import com.sina.weibo.model.WeiboData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DataSaverTest {
//这里对保存的数据又做了一些处理，把不包含关键词的记录都删除了
    @Test
    public void saveJsonFile() {
        List<WeiboData> data = DataReader.loadData("data.csv");
        List<WeiboData> dataToRemove=new ArrayList<>();

        System.out.println(data.size());

        for (WeiboData weiboData : data) {
            String content = weiboData.getContent();
            if (!content.contains("新冠肺炎") && !content.contains("抗疫") &&!content.contains("疫情")){
                dataToRemove.add(weiboData);
            }

        }
        data.removeAll(dataToRemove);
        System.out.println("after filter:--"+data.size());
        DataSaver.saveJsonFile(data,"data2.json");

    }
}