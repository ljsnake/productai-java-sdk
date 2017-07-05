package cn.productai.api.core;

import cn.productai.api.core.enums.LanguageType;

/**
 * Created by Thinkpad on 2017/7/3.
 */
public class DefaultProfile implements IProfile {

    private String accessKeyId;
    private String secretKey;
    private String version = "1";
    private LanguageType globalLanguage = null;

    @Override
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    @Override
    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    @Override
    public void setSecretKey(String secretKey) {
        this.secretKey=secretKey;
    }

    @Override
    public String getSecretKey() {
        return this.secretKey;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public void setGlobalLanguage(LanguageType language) {
        this.globalLanguage = language;
    }

    @Override
    public LanguageType getGlobalLanguage() {
        return this.globalLanguage;
    }
}
