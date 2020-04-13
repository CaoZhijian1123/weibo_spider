package com.sina.weibo;

import com.sina.weibo.data.DataSaver;
import com.sina.weibo.model.WeiboData;
import com.sina.weibo.util.CookieUtil;
import com.sina.weibo.util.DateUtil;
import com.sina.weibo.util.HtmlUtil;
import com.sina.weibo.util.UrlUtil;
import org.jsoup.Jsoup;
import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MainApp
 * @Author caozj
 * @Date 2020/3/20 16:10
 * @Version 1.0
 */
public class MainApp {
    private static int INTERVAL_PAGE=1000;
    private static int INTERVAL_DAY=3000;
    private static int INTERVAL_KEYWORD=10000;


    public static void main(String[] args) throws ParseException {
        String start="2020-1-1";
        String end="2020-3-25";


        List<String> keywords=new ArrayList<>();
        keywords.add("新冠肺炎");
        keywords.add("疫情");
        keywords.add("抗疫");

        List<WeiboData> data=new ArrayList<>();

        for (String keyword : keywords) {
            List<WeiboData> dataOfKeyWord = spiderDuration(keyword, DateUtil.parseDate(start), DateUtil.parseDate(end));
            data.addAll(dataOfKeyWord);
            dataOfKeyWord.clear();
            try {
                Thread.sleep(INTERVAL_KEYWORD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        DataSaver.saveData(data,"data.csv");

    }

    public static  List<WeiboData> spiderDuration(String keyword,Date begin, Date end){

        List<WeiboData> dataAll=new ArrayList<>();
        while (!begin.equals(end)){
            Date nextDay = DateUtil.nextDay(begin);//每次搜索24h时间区间

            //处理最多50页数据
            for (int j = 1; j <= 50 ; j++) {
                String url = UrlUtil.getUrl(keyword,DateUtil.getFormatDate(begin), DateUtil.getFormatDate(nextDay), j);
                System.out.println("spider...."+url);
                try {
                    Document document = Jsoup.connect(url).cookies(CookieUtil.getCookiesByName("weibo")).get();
                    boolean hasResult = HtmlUtil.hasResult(document);
                    if (!hasResult) {
                        System.out.println("no info found,go to next day");
                        break;
                    }
                    List<WeiboData> dataOnePage = HtmlUtil.parseDocument(document,keyword);

                    dataAll.addAll(dataOnePage);//将当前页的数据加入到list中
                    System.out.println("finished spider in "+DateUtil.getFormatDate(begin)+" in page "+j+ ",  "+dataOnePage.size()+" records were found");
                    Thread.sleep(INTERVAL_PAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            begin=nextDay;

            try {
                Thread.sleep(INTERVAL_DAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //将整个时间段内的数据保存成文件
        DataSaver.saveData(dataAll,keyword+".csv");
        System.out.println("finished on key word "+keyword+" "+dataAll.size()+" records");
        return dataAll;
    }
}
