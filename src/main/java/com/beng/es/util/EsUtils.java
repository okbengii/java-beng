package com.beng.es.util;

import java.net.URLEncoder;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.beng.http.util.HttpUtils;

public class EsUtils {

    private static final String UTF_8 = "utf-8";
    private static String esServerUrl;

    /**
     * 在使用 spring 结合时，可以使用 @Vaule 的形式从配置文件中读取 es 地址
     * 
     * @param esServerUrl
     */
    public void setEsServerUrl(String esServerUrl) {
        this.esServerUrl = esServerUrl;
    }

    public static String query(String sql) {
        try {
            String url = esServerUrl + "/_sql?sql=" + URLEncoder.encode(sql, UTF_8);
            HttpGet get = new HttpGet(url);
            return HttpUtils.getInstance().sendGet(get);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用post的方法将sql请求发送出去
     * 
     * @param sql
     * @return
     */
    public static String queryPost(String sql) {
        try {
            String url = esServerUrl + "/_sql";
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(sql, ContentType.APPLICATION_JSON));
            return HttpUtils.getInstance().sendPost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new EsUtils().setEsServerUrl("http://localhost:9200");
        String sql = "select * from table";
        System.out.println(queryPost(sql));
    }
}