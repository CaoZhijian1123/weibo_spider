package com.sina.weibo.util;

import com.sina.weibo.filter.WeiboDataFilter;
import com.sina.weibo.model.WeiboData;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HtmlUtil
 * @Author caozj
 * @Date 2020/3/20 17:50
 * @Version 1.0
 */
public class HtmlUtil {
    private static String NO_RESULT_CLASS="card-no-result";
    private static String WEIBO_CLASS="card";

    /**
     *
     * @param document
     * @return true for have result, false for not
     */
    public static boolean hasResult(Document document){
        Elements result = document.getElementsByClass(NO_RESULT_CLASS);
      //  System.out.println(result.html());
        return result.size()==0;
    }

    /**
     * 解析文档
     * @param document
     * @return 返回包含微博数据的list
     */
    public static List<WeiboData> parseDocument(Document document,String keyword){

        List<WeiboData> list=new ArrayList<>();
        Elements elements = document.getElementsByClass(WEIBO_CLASS);

        for (Element element : elements) {
            WeiboData weiboData = parseData(element, keyword);

            WeiboDataFilter filter=new WeiboDataFilter();
            boolean b = filter.filter(weiboData);
            //数据过滤
            if (!b){
                weiboData=null;
            }

            if (weiboData!=null){
                list.add(weiboData);
            }

        }


        return list;
    }

    /**
     * 从html文档中解析出一条微博数据
     * @param element
     * @return
     */
    private static WeiboData parseData(Element element,String keyword){




        //获取用户信息
        Elements dataElements = element.children();
        if (dataElements.size()<1) return null;
        Element feedElement = dataElements.get(0);
        Elements infos = feedElement.getElementsByClass("info");
        if (infos.size()==0) return null;
        Element info = infos.get(0);


        Elements nameElements = info.getElementsByClass("name");
        if (nameElements.size()==0) return null;

        //用户名和id
        Element nameElement = nameElements.get(0);
        String href = nameElement.attr("href");
        int eIndex = href.indexOf("?");
        String userId=href.substring(12,eIndex);
        String name = nameElement.text();
        //内容
        Elements txtElements = feedElement.getElementsByClass("txt");
        if (txtElements.size()==0) return null;
        Element txtElement = txtElements.get(0);
        String text = txtElement.text();
        if (text==null) return null;
        int length = text.length();
        //来源
        Elements fromElements = feedElement.getElementsByClass("from");
        if (fromElements.size()==0) {
            return null;
        }
        Element fromElement = fromElements.get(0);
        Element sourceElement = fromElement.getElementsByTag("a").get(0);
//        System.out.println(sourceElement.html());
        //url
        String link = sourceElement.attr("href");
        link="https:"+link;
        //published time
        String pubTime = sourceElement.text();

//
//        System.out.println("userID="+userId);
//        System.out.println("userName="+name);
//        System.out.println(text);
//        System.out.println(link);
//        System.out.println("pubTime = " + pubTime);


        //获取转发评论等信息
        Element actElement = dataElements.get(1);
        Elements listElements = actElement.getElementsByTag("li");
        if (listElements.size()==0) return null;

        String share = listElements.get(1).text().trim();
        String comment = listElements.get(2).text().trim();
        String like = listElements.get(3).text().trim();



//        System.out.println("share = " + share);
//        System.out.println("comment = " + comment);
//        System.out.println("like = " + like);

        WeiboData data=new WeiboData();
        data.setId(userId);
        data.setName(name);
        data.setPubTime(pubTime);
        data.setContent(text);
        data.setUrl(link);
        data.setKeyWord(keyword);
        data.setComNo(getCount(Act.COMMENT,comment));
        data.setLikeNo(getCount(Act.LIKE,like));
        data.setSharedNo(getCount(Act.SHARE,share));
        data.setFetchTime(DateUtil.getFormatDate(new Date()));

        //
//        System.out.println(data.toCSV());
        return data;
    }


    private static int getCount(Act act,String source){
        switch (act){
            case SHARE:
            case COMMENT:
                if (source.length()==2) {
//                    System.out.println(0);
                    return 0;
                }else {
                    int index=source.indexOf(" ");
                    String count=source.substring(index+1);
                    return Integer.parseInt(count);
//                    System.out.println(Integer.parseInt(count));
                }
            case LIKE:
                if (source==null||"".equals(source)){
                    return 0;
                }else {
                    try{
                        return Integer.parseInt(source.trim());
                    }catch (Exception e){
                        return 0;
                    }
                }

            default:
                return -1;
        }
    }


}
