# readme

## 1.项目管理工具 maven

pom.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>dataMining</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>hw1_sina</artifactId>
    <version>1.0.0</version>

    <dependencies>
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.9.2</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
<!--        json util-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.51</version>
        </dependency>
    </dependencies>

</project>
```

## 2.Cookie配置

配置文件位置

`src/main/resources/weibo.properties`

配置文件格式

`name value` 

注意空格

```properties
SUBP 0033WrSXqPxfM72
ULV 15843736
```

cookie配置之后程序自动读取

## 3.程序入口

`src/main/java/com/sina/weibo/MainApp.java`

```java
 public static void main(String[] args) throws ParseException {
        String start="2020-1-1";
        String end="2020-3-25";


        List<String> keywords=new ArrayList<>();
        keywords.add("新冠肺炎");
        keywords.add("疫情");
        keywords.add("抗疫");
     //由于搜索关键词不多，所以这里手动添加，没有配置文件

        List<WeiboData> data=new ArrayList<>();

        for (String keyword : keywords) {
            List<WeiboData> dataOfKeyWord = spiderDuration(keyword, DateUtil.parseDate(start), DateUtil.parseDate(end));
            data.addAll(dataOfKeyWord);
            dataOfKeyWord.clear();
            try {
                //程序休眠
                Thread.sleep(INTERVAL_KEYWORD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

     //保存文件
        DataSaver.saveData(data,"data.csv");

    }

```

## 4.数据清理

使用了一个过滤器

`src/main/java/com/sina/weibo/filter/WeiboDataFilter.java`

```java
public class WeiboDataFilter {
    private int minTextLength;//最短文本长度
    private int maxTextLength;//最长文本长度
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
}
```

最后又检查了一下文本，把不包含任何关键词的记录全部过滤

位置`src/test/java/com/sina/weibo/data/DataSaverTest.java`

```java
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
```

