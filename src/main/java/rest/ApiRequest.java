package rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiRequest {
    public static String getRest(String url) {
        String result = null;
        try {
            result = EntityUtils.toString(httpResponse(url).getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getStatus(String url) {
        return httpResponse(url).getStatusLine().getStatusCode();
    }

    private static HttpResponse httpResponse(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            response = httpClient.execute(new HttpGet(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
