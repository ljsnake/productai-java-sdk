package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.exceptions.ProfileException;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractService {

    static final String BASE_URL = "https://api.productai.cn";
    static final String X_CA_ACCESS_KEY_ID = "x-ca-accesskeyid";
    IProfile profile;

    Map<String, String> getHeaders() throws PAIException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_CA_ACCESS_KEY_ID, getAccessKeyId());
        return headers;
    }

    String getAccessKeyId() throws PAIException {
        if (this.profile == null || this.profile.getAccessKeyId() == null) {
            throw new ProfileException();
        }

        return this.profile.getAccessKeyId();
    }
}
