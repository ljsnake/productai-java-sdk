package cn.productai.api.core;

import cn.productai.api.core.attribute.IgnoreExtraParasAttribute;
import cn.productai.api.core.attribute.ParaSignAttribute;
import cn.productai.api.core.base.BaseRequest;
import cn.productai.api.core.base.BaseResponse;
import cn.productai.api.core.enums.ResponseType;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.core.exceptions.ServerException;
import cn.productai.api.core.helper.RequestHelper;
import cn.productai.api.core.helper.SignatureHelper;
import cn.productai.api.core.internal.HttpResponse;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultProductAIClient implements IWebClient {

    private String host = "api.productai.cn";
    private Charset charset = Charset.forName("UTF-8");
    private IProfile profile = null;

    public DefaultProductAIClient(){

    }

    public DefaultProductAIClient(IProfile profile) {
        this.profile = profile;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public void setEncoding(Charset charset) {
        this.charset = charset;
    }

    @Override
    public Charset getEncoding() {
        return this.charset;
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }

    @Override
    public IProfile getProfile() {
        return this.profile;
    }

    @Override
    public <T extends BaseResponse> T getResponse(BaseRequest<T> request) throws Exception {
        setHostAndSignature(request);

        HttpResponse response = RequestHelper.getResponse(request);
        return this.parse(request, response);
    }

    private <T extends BaseResponse> void setHostAndSignature(BaseRequest<T> request) throws Exception {
        if (request == null) {
            throw new ClientException("SDK.Request", "Request can not be null!");
        }
        if (this.profile == null) {
            throw new ClientException("SDK.Profile", "Profile can not be null!");
        }
        if (this.profile.getAccessKeyId().isEmpty() || this.profile.getSecretKey().isEmpty()) {
            throw new ClientException("SDK.Profile", "Invalid accessKeyId/accessKey");
        }
        HashMap<String, String> dics = new HashMap<>();

        dics.put("x-ca-accesskeyid", this.profile.getAccessKeyId());
        dics.put("x-ca-version", this.profile.getVersion());
        dics.put("x-ca-timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        dics.put("x-ca-signaturenonce", UUID.randomUUID().toString());
        dics.put("requestmethod", request.getRequestMethodHeader());

        for (String key : dics.keySet()) {
            request.setHeader(key, dics.get(key));
        }

        Field[] properties = request.getClass().getFields();
        for (Field p : properties) {
            ParaSignAttribute ca = p.getAnnotation(ParaSignAttribute.class);
            if (ca != null) {
                Object value = p.get(request);
                if (value != null && !value.toString().isEmpty()) {
                    dics.put(ca.Name(), value.toString());
                }
            }
        }

        // exclude the options
        IgnoreExtraParasAttribute ignoreExtraPara = request.getClass().getAnnotation(IgnoreExtraParasAttribute.class);
        if (ignoreExtraPara == null && request.getOptions() != null && request.getOptions().size() > 0) {
            for (String key : request.getOptions().keySet()) {
                try {
                    dics.put(key, request.getOptions().get(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        request.setHeader("x-ca-signature", SignatureHelper.signature(this.profile.getSecretKey(), dics));
        request.setHost(this.host);

        if (this.profile.getGlobalLanguage() != null) {
            request.setLanguage(this.profile.getGlobalLanguage());
        }
    }

    private <T extends BaseResponse> T parse(BaseRequest<T> request, HttpResponse httpResponse) throws Exception {
        String responseString = httpResponse.getResponseString();
        T t = getInstance(request.getResponseClass());
        if (responseString.isEmpty()) {
            t.setHeaders(httpResponse.getHeaders());
            t.setStatusCode(httpResponse.getStatusCode());
            return t;
        }
        if (t.getResponseType() == ResponseType.Json) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T _t = mapper.readValue(httpResponse.getResponseString(), request.getResponseClass());
            _t.setHeaders(httpResponse.getHeaders());
            _t.setStatusCode(httpResponse.getStatusCode());
            _t.setResponseBase64String(Base64.getEncoder().encodeToString(httpResponse.getResponseBytes()));

            if (httpResponse.getStatusCode() >= 500) {
                throw new ServerException(String.format("%s", _t.getErrorCode()),
                        String.format("%s%s%s",
                                _t.getErrorMsg() == null || _t.getErrorMsg().isEmpty() ? "" : _t.getErrorMsg(),
                                _t.getMessage() == null || _t.getMessage().isEmpty() ? "" : _t.getMessage(),
                                _t.getMsg() == null || _t.getMsg().isEmpty() ? "" : _t.getMsg()));
            } else if (httpResponse.getStatusCode() >= 400) {
                throw new ClientException(String.format("%s", _t.getErrorCode()),
                        String.format("%s%s%s",
                                _t.getErrorMsg() == null || _t.getErrorMsg().isEmpty() ? "" : _t.getErrorMsg(),
                                _t.getMessage() == null || _t.getMessage().isEmpty() ? "" : _t.getMessage(),
                                _t.getMsg() == null || _t.getMsg().isEmpty() ? "" : _t.getMsg()),
                        _t.getRequestId());
            }

            return _t;
        }
        return t;
    }

    private <T extends BaseResponse> T getInstance(Class<T> clasz) throws ClientException {
        try {
            return clasz.newInstance();
        } catch (Exception e) {
            throw new ClientException("SDK.InvalidResponseClass", "Unable to allocate " + clasz.getName() + " class");
        }
    }
}
