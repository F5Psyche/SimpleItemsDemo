package com.zhanghf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhanghf.constant.CommonDMO;
import com.zhanghf.enums.HTTPCodeEnum;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhanghf.constant.InterfaceInfoDMO.REQUEST_TIMEOUT_CONFIG;

/**
 * http接口调用类
 *
 * @author zhanghf
 * @version 1.0
 * @date 9:07 2020/3/12
 */
@Slf4j
public class HttpConnectionUtils {

    /**
     * @param uuid 唯一识别码
     * @param uri  请求地址
     * @param json 请求参数
     * @return 返回参数
     */
    public static String httpConnectionPost(String uuid, String uri, JSONObject json) {
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setConnectTimeout(300000);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty(CommonDMO.HEADER_NAME, CommonDMO.HEADER_VALUE);
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            String data = JSON.toJSONString(json == null ? "{}" : json);
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            outputStream.write(bytes);
            InputStream inputStream = connection.getInputStream();
            String responseContent = CommonUtils.inputStreamToString(uuid, inputStream);
            int status = connection.getResponseCode();
            String message = connection.getResponseMessage();
            connection.disconnect();
            log.info("uuid={}, status={}, message={}, responseContent={}", uuid, status, message, responseContent);
            return responseContent;
        } catch (IOException e) {
            log.error("uuid={}, errMsg={}", uuid, CommonUtils.getStackTraceString(e));
            return null;
        }
    }


    public static ResultVo<String> managePost(String url, Object object, String interfaceCode) throws Exception {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("interfaceData", object);
        stringObjectMap.put("interfaceCode", interfaceCode);
        System.out.println(new ObjectMapper().writeValueAsString(stringObjectMap));
        URL u = new URL(url);
        HttpURLConnection con = null;
        // 构建符合经办端的请求参数
        StringBuilder sb = new StringBuilder();
        sb.append("method=execGm&jsonData=").append(new ObjectMapper().writeValueAsString(stringObjectMap));
        //发送请求
        try {
            con = (HttpURLConnection) u.openConnection();
            //POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream outputStream = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, "GBK");
            System.out.println(sb);
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        // 读取返回内容
        StringBuilder buffer = new StringBuilder();
        try {
            //一定要有返回值，否则无法把请求发送给server端。
            if (con != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "GBK"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                    buffer.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject resultJson = JSON.parseObject(buffer.toString());
        System.out.println(resultJson);
        ResultVo resultVo = new ResultVo<String>();
        resultVo.setResult(resultJson.get("result"));
        resultVo.setCode(resultJson.getJSONObject("data").getString("resultData"));
        resultVo.setSuccess(resultJson.getJSONObject("data").getBoolean("resultCode"));
        resultVo.setResultDes(resultJson.getJSONObject("data").getString("resultMsg"));
        return resultVo;
    }


    /**
     * @param uuid      唯一识别码
     * @param httpUrl   请求地址
     * @param condition 请求参数
     * @return 返回参数
     */
    public static Object httpClientPost(String uuid, String httpUrl, JSONObject condition) {
        HttpPost post = new HttpPost(httpUrl);
        List<NameValuePair> list = new ArrayList<>();
        try {
            for (String key : condition.keySet()) {
                Object object = condition.get(key);
                String simpleName = object.getClass().getSimpleName();
                switch (simpleName) {
                    case "String":
                        list.add(new BasicNameValuePair(key, condition.getString(key)));
                        break;
                    case "JSONArray":
                        JSONArray array = condition.getJSONArray(key);
                        for (Object obj : array) {
                            list.add(new BasicNameValuePair(key, obj.toString()));
                        }
                        break;
                    default:
                        log.error("uuid={}, simpleName={}, object={}", uuid, simpleName, object);
                        list.add(new BasicNameValuePair(key, object.toString()));
                        break;
                }
            }
            log.info("uuid={}, list={}", uuid, list);
            post.setEntity(new UrlEncodedFormEntity(list, CommonDMO.CHARSET_NAME));
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, e.getMessage());
            return e.toString();
        }
        return httpPostUsing(uuid, post);
    }

    /**
     * @param uuid    唯一识别码
     * @param httpUrl 请求地址
     * @param object  请求参数
     * @param header  请求头
     * @return 返回参数
     */
    public static Object httpClientPost(String uuid, String httpUrl, Object object, JSONObject header) {
        HttpPost post = new HttpPost(httpUrl);
        post.setHeader(CommonDMO.HEADER_NAME, CommonDMO.HEADER_VALUE);
        if (!CollectionUtils.isEmpty(header)) {
            header.keySet().forEach(key -> {
                post.setHeader(key, header.getString(key));
            });
        }
        byte[] bytes = object.toString().getBytes(StandardCharsets.UTF_8);
        post.setEntity(new ByteArrayEntity(bytes));
        return httpPostUsing(uuid, post);
    }


    /**
     * @param uuid 唯一识别码
     * @param post httpPost
     * @return 返回参数
     */
    private static Object httpPostUsing(String uuid, HttpPost post) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        String responseContent;
        int status;
        try {
            httpClient = HttpClients.createDefault();
            post.setConfig(REQUEST_TIMEOUT_CONFIG);
            response = httpClient.execute(post);
            status = response.getStatusLine().getStatusCode();
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, CommonDMO.CHARSET_NAME);
        } catch (Exception e) {
            log.error("uuid={}, errMsg={}", uuid, e.toString());
            return e.toString();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                log.error("uuid={}, errMsg={}", uuid, e.toString());
            }
        }
        log.info("uuid={}, status={}, responseContent={}", uuid, status, responseContent);
        if (status != HTTPCodeEnum.OK.getCode()) {
            return "httpStatus<" + status + ">请求错误";
        }
        return JSONObject.parse(responseContent);
    }
}
