package cn.productai.apiv2.lib;

import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.exceptions.PAIException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Http {

    private static final Logger logger = Logger.getLogger(Http.class.getName());
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static String request(HttpMethod httpMethod,
                                 String url,
                                 Map<String, String> headers,
                                 String json,
                                 String fileFieldName, File file) throws PAIException {
        // Set URL
        Request.Builder request = new Request.Builder()
                .url(url);

        // Set headers
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // Set body
        RequestBody body = null;
        if (json != null) {
            body = RequestBody.create(JSON, json);
        }

        if (fileFieldName != null && file != null) {
            body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(
                            fileFieldName,
                            file.getName(),
                            RequestBody.create(MediaType.parse("text/csv"), file))
                    .build();
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
            if (body != null) {
                request.delete(body);
            } else {
                request.delete();
            }
        }

        try {
            Response response = client.newCall(request.build()).execute();
            String resBody = null;
            if (response.body() != null) {
                resBody = response.body().string();
            }
            if (response.code() >= 400) {
                logger.log(Level.SEVERE, "Request error, response code is "
                        + response.code() + ", response body: " + resBody);

                ObjectMapper om = new ObjectMapper();
                JsonNode jsonNode = om.readTree(resBody);
                Integer errorCode = jsonNode.path("error_code").asInt();
                String message = jsonNode.path("message").asText();
                throw new PAIException(errorCode, message);
            }

            return resBody;
        } catch (PAIException paie) {
            throw paie;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Http error", e.getMessage());
            throw new PAIException(e);
        }
    }


    public static String request(HttpMethod httpMethod,
                                 String url,
                                 Map<String, String> headers, String json) throws PAIException {
        return request(httpMethod, url, headers, json, null, null);
    }

    public static String request(HttpMethod httpMethod,
                                 String url,
                                 Map<String, String> headers) throws PAIException {
        return request(httpMethod, url, headers, null, null, null);
    }

    public static String request(HttpMethod httpMethod,
                                 String url,
                                 Map<String, String> headers, String fileFieldName, File file) throws PAIException {
        return request(httpMethod, url, headers, null, fileFieldName, file);
    }
}
