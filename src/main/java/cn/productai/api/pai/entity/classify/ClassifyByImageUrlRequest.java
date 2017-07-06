package cn.productai.api.pai.entity.classify;

import cn.productai.api.core.entity.AIService;
import cn.productai.api.core.enums.ClassifyType;
import cn.productai.api.core.enums.ServiceType;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.api.pai.base.CallApiByImageUrlBaseRequest;

import java.util.HashMap;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class ClassifyByImageUrlRequest extends CallApiByImageUrlBaseRequest<ClassifyResponse> {

    @Override
    public Class<ClassifyResponse> getResponseClass() {
        return ClassifyResponse.class;
    }

    private static HashMap<Integer, AIService> _classifyServiceDicts = EnumHelper.toServiceHashMap(ClassifyType.class);
    private static HashMap<Integer, String> _serviceTypeDicts = EnumHelper.toHashMap(ServiceType.class);

    public ClassifyByImageUrlRequest(ClassifyType classifyType) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(),
                _classifyServiceDicts.get(classifyType.ordinal()).getServiceId());
    }

    public ClassifyByImageUrlRequest(ClassifyType classifyType, String imageUrl) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(),
                _classifyServiceDicts.get(classifyType.ordinal()).getServiceId(), imageUrl);
    }

    public ClassifyByImageUrlRequest(String serviceType, String serviceId) {
        super(serviceType, serviceId);
    }

    public ClassifyByImageUrlRequest(String serviceType, String serviceId, String imageUrl) {
        super(serviceType, serviceId, imageUrl);
    }

    /**
     *
     * @param serviceType should be ServiceType.Classify
     * @param serviceId your service id
     */
    public ClassifyByImageUrlRequest(ServiceType serviceType, String serviceId) {
        super(_serviceTypeDicts.get(serviceType == null ? ServiceType.Classify.ordinal() : serviceType.ordinal()), serviceId);
    }

    /**
     *
     * @param serviceType should be ServiceType.Classify
     * @param serviceId your service id
     * @param imageUrl the image file
     */
    public ClassifyByImageUrlRequest(ServiceType serviceType, String serviceId, String imageUrl) {
        super(_serviceTypeDicts.get(serviceType == null ? ServiceType.Classify.ordinal() : serviceType.ordinal()), serviceId, imageUrl);
    }
}
