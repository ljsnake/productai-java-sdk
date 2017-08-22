package cn.productai.api.pai.base;

import cn.productai.api.core.attribute.ParaSignAttribute;
import cn.productai.api.core.base.BaseRequest;
import cn.productai.api.core.base.BaseResponse;
import cn.productai.api.core.enums.ServiceType;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.api.core.helper.WebQueryHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class CallApiByImageUrlBaseRequest<T extends BaseResponse> extends BaseRequest<T> {

    private static HashMap<Integer, String> _serviceTypeDicts = EnumHelper.toHashMap(ServiceType.class);

    private String serviceType;
    private String serviceId;

    private ServiceType serviceTypeValue;

    public void setServiceTypeValue(ServiceType serviceTypeValue) {
        this.serviceTypeValue = serviceTypeValue;
        this.serviceType = _serviceTypeDicts.get(serviceTypeValue.ordinal());
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/%s/%s", this.getHost(), this.getServiceType(), this.getServiceId());
    }

    @Override
    public String getQueryString() {
        ArrayList<String> list = new ArrayList<>();
        Field[] ps = this.getClass().getFields();
        for (Field p : ps) {
            ParaSignAttribute ca = p.getAnnotation(ParaSignAttribute.class);
            if (ca != null) {
                try {
                    Object value = p.get(this);
                    if (value != null && !value.toString().isEmpty())
                        list.add(String.format("%s=%s", ca.Name(), ca.IsNeedUrlEncode() ? WebQueryHelper.urlEncode(value.toString()) : value.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.getOptions() != null && this.getOptions().size() > 0) {
            for (String key : this.getOptions().keySet()) {
                try {
                    list.add(String.format("%s=%s", key, WebQueryHelper.urlEncode(this.getOptions().get(key))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return String.join("&", list);
    }

    @Override
    public byte[] getQueryBytes() {
        return null;
    }

    @ParaSignAttribute(Name = "url", IsNeedUrlEncode = true)
    public String url;

    @ParaSignAttribute(Name = "loc")
    public String loc = "0-0-1-1";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public CallApiByImageUrlBaseRequest() {
        super();
    }

    public CallApiByImageUrlBaseRequest(String serviceType, String serviceId) {
        this();
        if (serviceType.isEmpty()) {
            throw new IllegalArgumentException("serviceType can not be null");
        }
        if (serviceId.isEmpty()) {
            throw new IllegalArgumentException("serviceId can not be null");
        }

        this.serviceType = serviceType;
        this.serviceId = serviceId;
    }

    public CallApiByImageUrlBaseRequest(String serviceType, String serviceId, String url) {
        this(serviceType, serviceId);

        this.url = url;
    }

    public CallApiByImageUrlBaseRequest(String serviceType, String serviceId, String url, String loc) {
        this(serviceType, serviceId, url);

        if (loc != null && !loc.isEmpty())
            this.loc = loc;
    }
}
