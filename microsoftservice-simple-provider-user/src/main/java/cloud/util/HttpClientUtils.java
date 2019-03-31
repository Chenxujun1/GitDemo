package cloud.util;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

public class HttpClientUtils {
    public String doPost(String url, Map<String, String> param){
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = null;
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        Set<Map.Entry<String, String>> set = param.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()){
            nameValuePairs.add(new BasicNameValuePair(iterator.next().getKey(),iterator.next().getValue()));
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
                //String response = EntityUtils.toString(responseEntity, Charsets.UTF_8);
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
}
