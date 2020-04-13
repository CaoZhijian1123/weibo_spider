package com.sina.weibo.filter;

import com.sina.weibo.model.WeiboData;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WeiboDataFilter
 * @Author caozj
 * @Date 2020/3/28 18:10
 * @Version 1.0
 */
public class WeiboDataFilter {
    private int minTextLength;
    private int maxTextLength;
    private List<String> nameNotNeed=new ArrayList<>();


    public WeiboDataFilter(){
        this(5,500);
    }

    public WeiboDataFilter(int minTextLength, int maxTextLength) {
        this.minTextLength = minTextLength;
        this.maxTextLength = maxTextLength;

        nameNotNeed.add("新闻");
        nameNotNeed.add("日报");
        nameNotNeed.add("央视");
//        nameNotNeed.add("人民日报");

    }



    public boolean filter(WeiboData weiboData){
        if (weiboData==null) {
            return false;
        }
        //文本长度检查
        String content = weiboData.getContent();
        if (content==null) {
            return false;
        }
        int length = content.length();
        if (length<minTextLength || length >maxTextLength) {
            return false;
        }


        //用户昵称检查
        if (nameNotNeed!=null){
            String name = weiboData.getName();
            for (String s : nameNotNeed) {
                if (name.contains(s)) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getMinTextLength() {
        return minTextLength;
    }

    public void setMinTextLength(int minTextLength) {
        this.minTextLength = minTextLength;
    }

    public int getMaxTextLength() {
        return maxTextLength;
    }

    public void setMaxTextLength(int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }

    public List<String> getNameNotNeed() {
        return nameNotNeed;
    }

    public void setNameNotNeed(List<String> nameNotNeed) {
        this.nameNotNeed = nameNotNeed;
    }


}
