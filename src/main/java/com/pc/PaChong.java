package com.pc;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by han on 2017/3/12.
 */
public class PaChong implements Runnable{

    public static List<String> urls = new ArrayList<String>();
    public static List<String> oldUrls = new ArrayList<String>();
    public static String YUMING = "http://www.chinaacc.com";
    private String name;
    private static final int THREANNUM = 10;

    public PaChong(String name){
        this.setName(name);
    }

    public static void main(String[] args){
        urls.add(YUMING);
        List<Thread> Threads = new ArrayList<Thread>();
        int ThreadNum = 0;

        while(urls.size() != 0){

            if(Threads.size() < THREANNUM){
                System.out.println("线程数不足，设定线程数" + THREANNUM + "    当前线程数" + Threads.size() + "将启动" + (THREANNUM-Threads.size()) + "个线程");
                for(int i=0; i < THREANNUM-Threads.size(); i++){
                    Thread thread = new Thread(new PaChong("Thread"+ ThreadNum++));
                    Threads.add(thread);
                    thread.start();
                    System.out.println(thread.getName() + "已启动。现在状态:"+ thread.getState());
                }
            }

            System.out.println("******************************************************");
            System.out.println("已抓取标题数:" + oldUrls.size());
            System.out.println("待抓取队列数:" + urls.size());
            System.out.println("线程数:" + Threads.size());

            for (Thread thread : Threads){
                if(!thread.isAlive()){
                    thread.start();
                    System.out.println(thread.getName() + "意外死亡,已重新启动。现在状态:"+ thread.getState());
                }
            }

            System.out.println("******************************************************");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        String str = toGet(YUMING, null, "utf-8");
        addLinks(str);
        oldUrls.add(YUMING);
        urls.remove(YUMING);
    }

    public void run(){
        while(urls.size()>0){
            String url = urls.get(0);
            String result = toGet(url,null,null);

            addLinks(result);
            oldUrls.add(url);
            urls.remove(url);
        }
    }

    /**
     * 添加链接到待执行队列
     * @param html
     */
    public static synchronized void addLinks(String html){
        String regEx = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        // 查找字符串中是否有匹配正则表达式的字符/字符串

        while(matcher.find()){
            String link = matcher.group().replace("\n", "").replace("\r", "").replace(" ", "").replace("href=", "").replace("\"","").replace(";","").replace("javascript:","").replace("void(0)","").replace("footurl","");

            //如果 链接为空、链接是邮件链接、不是本域名下的链接 不保存
            if(null == link || "".equals(link) || link.contains("mailto:") || !link.contains(YUMING)){
                continue;
            }

            if(!link.contains("http")){//如果链接之后后面的路径  加上域名拼成完整链接
                link = YUMING + link;
            }

            if(isNewLink(link)){
                urls.add(link);
            }

        }
    }

    /**
     * 检查url是否执行过或在待执行队列
     * @param link
     * @return
     */
    public static boolean isNewLink(String link){

        for(String url : urls){
            if(link == null || url == null || url.equals(link)){
                return false;
            }
        }

        for(String url : oldUrls){
            if(link == null || url == null || url.equals(link)){
                return false;
            }
        }

        return true;
    }

    /**
     * 发送get请求
     * @param url
     * @param headers
     * @param encode
     * @return
     */
    public static String toGet(String url, Map<String,String> headers, String encode){
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        String content = null;

        if(null == encode || "".equals(encode)){
            encode = "utf-8";
        }

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

}
