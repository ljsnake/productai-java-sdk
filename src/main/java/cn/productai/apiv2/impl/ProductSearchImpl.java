package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.ProductSearch;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.exceptions.ProfileException;
import cn.productai.apiv2.lib.Http;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ProductSearchImpl implements ProductSearch {

    private static final Logger logger = LogManager.getLogger(ProductSearchImpl.class);
    private static final String BASE_URL = "https://api.productai.cn/product_sets/_0000178/services";
    private static final String X_CA_ACCESS_KEY_ID = "x-ca-accesskeyid";
    private IProfile profile;

    @Override
    public String createService(String name, String scenario, String productSetId) {
        try {
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"scenario\":\"" + scenario + "\","
                    + "\"product_set_id\":\"" + productSetId + "\""
                    + "}";

            return Http.request(HttpMethod.POST, BASE_URL, getHeaders(), json);
        } catch (PAIException pai) {
            logger.error("createService request error", pai);
            return null;
        }
    }

    @Override
    public String listAllService() {
        try {
            return Http.request(HttpMethod.GET, BASE_URL, getHeaders());
        } catch (PAIException pai) {
            logger.error("listAllService request error", pai);
            return null;
        }
    }

    @Override
    public String getServiceById(String productSetId) {
        try {
            String url = BASE_URL + "/" + productSetId;
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException pai) {
            logger.error("getServiceById request error", pai);
            return null;
        }
    }

    @Override
    public String updateServiceName(String productSetId, String name) {
        try {
            String url = BASE_URL + "/" + productSetId;
            String json = "{\"name\":\"" + name + "\"}";
            return Http.request(HttpMethod.PATCH, url, getHeaders(), json);
        } catch (PAIException pai) {
            logger.error("updateServiceName request error", pai);
            return null;
        }
    }

    @Override
    public String deleteServiceById(String productSetId) {
        try {
            String url = BASE_URL + "/" + productSetId;
            return Http.request(HttpMethod.DELETE, url, getHeaders());
        } catch (PAIException pai) {
            logger.error("deleteServiceById request error", pai);
            return null;
        }
    }

    private String getAccessKeyId() throws PAIException {
        if (this.profile == null || this.profile.getAccessKeyId() == null) {
            throw new ProfileException();
        }

        return this.profile.getAccessKeyId();
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }

    private Map<String, String> getHeaders() throws PAIException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_CA_ACCESS_KEY_ID, getAccessKeyId());
        return headers;
    }
}
