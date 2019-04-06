package cloud.util;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 自定义Http链接
 */
public class HttpClientUtils {
    private static PoolingHttpClientConnectionManager httpClientConnectionManager = null;
    private static RequestConfig requestConfig = null;
    static {
        httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(200);
        httpClientConnectionManager.setDefaultMaxPerRoute(200);

        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(3*1000)
                .setConnectionRequestTimeout(6*1000)
                .setSocketTimeout(6*1000)
                .build();
    }

    /**
     * Post
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, Object> param){
        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        CloseableHttpResponse closeableHttpResponse = null;
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        Set<Map.Entry<String, Object>> set = param.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()){
            nameValuePairs.add(new BasicNameValuePair(iterator.next().getKey(),iterator.next().getValue().toString()));
        }
        HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs, Charsets.UTF_8);
        httpPost.setEntity(httpEntity);
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpPost);
            StatusLine statusLine = closeableHttpResponse.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity responseEntity = closeableHttpResponse.getEntity();
                InputStream inputStream = responseEntity.getContent();
                InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String response = null;
                while((response = br.readLine()) != null){
                    response.concat(response);
                }
                return  response;
            }
        } catch (IOException e) {
            new Throwable();
        }finally {
            if(null != closeableHttpResponse){
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * Get
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, Object> param){
        String response = "";
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if(null != param){
            Set<Map.Entry<String, Object>> entries = param.entrySet();
            for(Map.Entry<String, Object> entry : entries){
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
        }
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(nameValuePairs);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                response = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(null != httpResponse){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}
