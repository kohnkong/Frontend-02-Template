package com.geekbang.JVM.utils;

import okhttp3.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    /**
     * get
     *
     * @author kongqf
     * @date 2018/8/30 14:42
     */
    public static String get(String url, Map<String, String> queries) {
        logger.debug("post url is {},parajson is {}", new Object[]{url, queries});
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = getInstance();
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                responseBody = response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        logger.debug("url is {},response is {}", request.url(), responseBody);
        return responseBody;
    }

    /**
     * post
     *
     * @author kongqf
     * @date 2018/8/31 17:32
     */
    public static String post(String url, String jsonParams) {
        logger.debug("post url is {},parajson is {}", new Object[]{url, jsonParams});
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return getResponse(request);
    }

    /**
     * get header
     *
     * @author kongqf
     * @date 2018/8/30 14:49
     */
    public static String getForHeader(String url, Map<String, String> queries) {
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                String kvParam = entry.getKey() + "=" + entry.getValue();
                if (firstFlag) {
                    sb.append("?").append(kvParam);
                    firstFlag = false;
                } else {
                    sb.append("&").append(kvParam);
                }
            }
        }
        Request request = new Request.Builder()
                .addHeader("key", "value")
                .url(sb.toString())
                .build();
        return getResponse(request);
    }

    /**
     * Post 请求发送xml数据
     *
     * @author kongqf
     * @date 2018/8/30 14:47
     */
    public static String postXmlParams(String url, Map<String, String[]> queries, String xml) {
        logger.debug("post url is {},parajson is {}", new Object[]{url, xml});
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            for (Map.Entry<String, String[]> entry : queries.entrySet()) {
                String key = entry.getKey();
                String[] values = entry.getValue();
                String kvParam = key + "=" + (values.length == 0 ? "" : String.join(",", values));
                if (firstFlag) {
                    sb.append("?").append(kvParam);
                    firstFlag = false;
                } else {
                    sb.append("&").append(kvParam);
                }
            }
        }
        Request request = new Request.Builder()
                .url(sb.toString())
                .post(requestBody)
                .build();

        return getResponse(request);
    }

    public static String postXmlParams(String url, String xml, String credential) {
        logger.debug("post url is {},parajson is {}", new Object[]{url, xml});
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", credential)
                .post(requestBody)
                .build();

        return getResponse(request);
    }

    public static String getResponse(Request request) {
        String responseBody = "";
        Response response = null;
        try {
            OkHttpClient okHttpClient = getInstance();
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                responseBody = response.body().string();
            }
            //logger.debug("getResponse is {}", JSON.toJSONString(response));
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        logger.debug("url is {},response is {}", request.url(), responseBody);
        return responseBody;
    }

    public static OkHttpClient getInstance() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }
}

