package com.beng.http.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils implements Closeable {
    public static final String UTF_8 = "UTF-8";
    private final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/33.0";
    private CloseableHttpClient client;
    private static final HttpUtils INSTANCE = new HttpUtils();

    private HttpUtils() {
        this.init();
    }

    public static HttpUtils getInstance() {
        return INSTANCE;
    }

    public void init() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(3000); // MaxPerRoute 500
        connectionManager.setMaxTotal(10000);// maxTotal 300
        HttpClientBuilder builder = HttpClients.custom();
        builder.setConnectionManager(connectionManager);
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder.setSocketTimeout(30 * 1000);// 2min
        requestConfigBuilder.setConnectTimeout(10 * 1000);// 30s
        requestConfigBuilder.setConnectionRequestTimeout(30000);// 30s
        builder.setDefaultRequestConfig(requestConfigBuilder.build());
        builder.setUserAgent(DEFAULT_USER_AGENT);
        client = builder.build();
    }

    @Override
    public void close() throws IOException {
        if (client != null) {
            client.close();
        }
    }

    public String sendPost(HttpPost post) {
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity());
            response.close();
            return result;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }

    public String executeGetWithResult(String url) {
        return sendGet(new HttpGet(url));
    }

    public String sendGet(HttpGet get) {
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            String result = EntityUtils.toString(response.getEntity());
            response.close();
            return result;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }

    public String put(HttpPut put) {
        CloseableHttpResponse response = null;
        try {
            response = client.execute(put);
            String result = EntityUtils.toString(response.getEntity());
            response.close();
            return result;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }

    public String sendDelete(HttpDelete del) {
        CloseableHttpResponse response = null;
        try {
            response = client.execute(del);
            String result = EntityUtils.toString(response.getEntity());
            response.close();
            return result;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }

    public CloseableHttpClient getHttpClient() {
        return client;
    }

    public HttpPost post(String url) {
        return post(url, null);
    }

    public HttpPost post(String url, Map<String, Object> params) {
        return post(url, params, null);
    }

    public HttpPost post(String url, Map<String, Object> params, HttpHost proxy) {
        HttpPost result = new HttpPost(url);
        if (params != null && !params.isEmpty()) {
            result.setEntity(buildParams(params));
        }
        return result;
    }

    public UrlEncodedFormEntity buildParams(Map<String, ? extends Object> params) {
        return buildParams(params, UTF_8);
    }

    @SuppressWarnings("rawtypes")
    public UrlEncodedFormEntity buildParams(Map<String, ? extends Object> params, String encoding) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        List<NameValuePair> parameters = new ArrayList<>();
        for (Entry<String, ? extends Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                if (value instanceof List) {
                    for (Object o : (List) value) {
                        if (o != null) {
                            parameters.add(new BasicNameValuePair(entry.getKey(), o.toString()));
                        }
                    }
                } else {
                    parameters.add(new BasicNameValuePair(entry.getKey(), value.toString()));
                }
            } else {
                parameters.add(new BasicNameValuePair(entry.getKey(), null));
            }
        }
        return new UrlEncodedFormEntity(parameters, Charset.forName(encoding));
    }

    public String buildParamString(Map<String, ? extends Object> params) {
        return buildParamString(params, UTF_8);
    }

    public String buildParamString(Map<String, ? extends Object> params, String encoding) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (Entry<String, ? extends Object> entry : params.entrySet()) {
                Object value = entry.getValue();
                value = value == null ? "" : value.toString();
                sb.append("&").append(URLEncoder.encode(entry.getKey(), encoding)).append("=")
                        .append(URLEncoder.encode((String) value, encoding));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return sb.toString();
    }
}
