package com.beng.http.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MainHttpPost {

    public static void main(String[] args) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:9999/api/addUser");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> list = new ArrayList<>();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("userName", "zhangsan");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("password", "123456");
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("serviceName", "sms");
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("groupName", "custom");
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("serviceId", "1");
        BasicNameValuePair basicNameValuePair6 = new BasicNameValuePair("userIp", "127.0.0.1");
        list.add(basicNameValuePair);
        list.add(basicNameValuePair2);
        list.add(basicNameValuePair3);
        list.add(basicNameValuePair4);
        list.add(basicNameValuePair5);
        list.add(basicNameValuePair6);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);

    }
}
