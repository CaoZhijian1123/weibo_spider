package com.sina.weibo.model;

/**
 * @ClassName model
 * @Author caozj
 * @Date 2020/3/20 18:51
 * @Version 1.0
 */
public class WeiboData {
    private String id;
    private String name;
    private String url;
    private String pubTime;
    private String fetchTime;
    private String keyWord;
    private String content;
    private int sharedNo;
    private int comNo;
    private int likeNo;

    public String toCSV(){
        StringBuilder builder=new StringBuilder();
        builder.append(id).append("\t");
        builder.append(name).append("\t");
        builder.append(url).append("\t");
        builder.append(pubTime).append("\t");
        builder.append(fetchTime).append("\t");
        builder.append(keyWord).append("\t");
        builder.append(content).append("\t");
        builder.append(sharedNo).append("\t");
        builder.append(comNo).append("\t");
        builder.append(likeNo);
        return builder.toString();
    }

    @Override
    public String toString() {
        return "WeiBo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", pubTime='" + pubTime + '\'' +
                ", fetchTime='" + fetchTime + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", content='" + content + '\'' +
                ", sharedNo=" + sharedNo +
                ", comNo=" + comNo +
                ", likeNo=" + likeNo +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(String fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSharedNo() {
        return sharedNo;
    }

    public void setSharedNo(int sharedNo) {
        this.sharedNo = sharedNo;
    }

    public int getComNo() {
        return comNo;
    }

    public void setComNo(int comNo) {
        this.comNo = comNo;
    }

    public int getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(int likeNo) {
        this.likeNo = likeNo;
    }


}
