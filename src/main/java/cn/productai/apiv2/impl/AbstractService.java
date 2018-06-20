package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.exceptions.ProfileException;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractService {

    static final String BASE_URL = "https://api.productai.cn";
    static final String X_CA_ACCESS_KEY_ID = "x-ca-accesskeyid";
    static final String X_CA_VERSION = "x-ca-version";
    static final String X_CA_VERSION_VALUE = "1";
    static final String LANGUAGE = "Accept-Language";
    static HashMap<Integer, String> _languageDicts = EnumHelper.toHashMap(LanguageType.class);
    IProfile profile;

    Map<String, String> getHeaders() throws PAIException {
        Map<String, String> headers = new HashMap<>();
        headers.put(X_CA_ACCESS_KEY_ID, getAccessKeyId());
        headers.put(X_CA_VERSION, X_CA_VERSION_VALUE);

        if (profile.getGlobalLanguage() == null) {
            profile.setGlobalLanguage(LanguageType.English);
        }
        headers.put(LANGUAGE, _languageDicts.get(profile.getGlobalLanguage().ordinal()));
        return headers;
    }

    String getAccessKeyId() throws PAIException {
        if (this.profile == null || this.profile.getAccessKeyId() == null) {
            throw new ProfileException("Profile is required.");
        }

        return this.profile.getAccessKeyId();
    }
}
