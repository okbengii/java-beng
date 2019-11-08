package com.beng.http.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class MainHttpPost2 {

    public static void main(String[] args) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        Map<String, String> params = new HashMap<>();
        params.put("userName", "zhangsan");
        params.put("password", "123456");
        params.put("serviceName", "sms");
        params.put("groupName", "custom");
        params.put("serviceId", "1");
        params.put("userIp", "127.0.0.1");
        String postUrl = postUrlEncodedWithParams("http://localhost:9999/api/addUser", params);
        HttpPost httpPost = new HttpPost(postUrl);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
    }

    public static String postUrlEncodedWithParams(String url, Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder param = new StringBuilder(url + "?");
        // 将要拼接的参数urlencode
        for (Entry<String, String> entry : params.entrySet()) {
            param.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&");
        }
        return param.toString();
    }
}
