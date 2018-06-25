package cn.productai.apiv2.lib;

import cn.productai.api.core.enums.HttpMethod;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.apiv2.exceptions.PAIException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Http {

    private static final Logger logger = Logger.getLogger(Http.class.getName());
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static HashMap<Integer, String> _httpMethodMap = EnumHelper.toHashMap(HttpMethod.class);

    public static String request(HttpMethod httpMethod,
                                 String url,
                                 Map<String, String> headers,
                                 String json,
                                 String fileFieldName, File file) throws PAIException {
        // Set URL
        Request.Builder requestBuilder = new Request.Builder().url(url);

        // Set headers
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // Set Body
        switch (httpMethod) {
            case POST:
            case PUT:
            case PATCH:
            case DELETE:
                String method = _httpMethodMap.get(httpMethod.ordinal());
                RequestBody body = generateRequestBody(json, fileFieldName, file);
                requestBuilder.method(method, body);
                break;
            default:
        }

        try {
            Response response = client.newCall(requestBuilder.build()).execute();
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
                String message = jsonNode.path("message").asText(null);
                Integer responseCode = response.code();
                String requestId = jsonNode.path("request_id").asText(null);
                throw new PAIException(errorCode, message, responseCode, requestId);
            }

            return resBody;
        } catch (PAIException paie) {
            throw paie;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Http error", e.getMessage());
            throw new PAIException(e);
        }
    }

    private static RequestBody generateRequestBody(String json, String fileFieldName, File file) {
        if (fileFieldName != null && file != null) {
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
            bodyBuilder.setType(MultipartBody.FORM)
                    .addFormDataPart(
                            fileFieldName,
                            file.getName(),
                            RequestBody.create(MediaType.parse(getFileContentType(file)), file));

            if (json != null) {
                try {
                    ObjectMapper om = new ObjectMapper();
                    JsonNode jsonNode = om.readTree(json);
                    Iterator<Map.Entry<String, JsonNode>> subEntryIterator = jsonNode.fields();
                    while (subEntryIterator.hasNext()) {
                        Map.Entry<String, JsonNode> subEntry = subEntryIterator.next();
                        bodyBuilder.addFormDataPart(subEntry.getKey(), subEntry.getValue().asText());
                    }
                } catch (Exception e) {
                    logger.info("add json=" + json + " to addFormDataPart got error:" + e.getMessage());
                }
            }

            return bodyBuilder.build();
        }
        if (json != null) {
            return RequestBody.create(JSON, json);
        }
        return null;
    }

    private static String getFileContentType(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            return Files.probeContentType(path); // refer to TestContentType
        } catch (IOException e) {
            logger.info("Files.probeContentType for path=" + path + " got error:" + e.getMessage());
        }
        return "text/csv";
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
