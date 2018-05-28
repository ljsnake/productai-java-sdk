package cn.productai.apiv2.lib;

import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.exceptions.HttpException;
import cn.productai.apiv2.exceptions.PAIException;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class Http {

    private static final Logger logger = LogManager.getLogger(Http.class);
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static String get(String url, Map<String, String> headers) throws IOException {
        Request.Builder request = new Request.Builder()
                .url(url);
        headers.forEach(request::addHeader);
        Response response = client.newCall(request.build()).execute();
        return response.body().string();
    }

    public static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String request(HttpMethod httpMethod,
                                 String url,
                                 Map<String, String> headers,
                                 String json) throws PAIException {
        // Set URL
        Request.Builder request = new Request.Builder()
                .url(url);

        // Set headers
        if (headers != null) {
            headers.forEach(request::addHeader);
        }

        // Set body
        RequestBody body = null;
        if (json != null) {
            body = RequestBody.create(JSON, json);
        }

        if (httpMethod.equals(HttpMethod.POST)) {
            request.post(body);
        }

        if (httpMethod.equals(HttpMethod.PUT)) {
            request.put(body);
        }

        if (httpMethod.equals(HttpMethod.PATCH)) {
            request.patch(body);
        }

        if (httpMethod.equals(HttpMethod.DELETE)) {
            request.delete();
        }

        try {
            Response reponse = client.newCall(request.build()).execute();
            return reponse.body().string();
        } catch (Exception e) {
            logger.error("Http Error", e.getMessage());
            throw new HttpException();
        }
    }

    public static String request(HttpMethod httpMethod,
                                 String url,
                                 Map<String, String> headers) throws PAIException {
        return request(httpMethod, url, headers, null);
    }
}
