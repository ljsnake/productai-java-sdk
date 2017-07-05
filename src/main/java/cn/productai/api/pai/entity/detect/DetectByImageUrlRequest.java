package cn.productai.api.pai.entity.detect;

import cn.productai.api.core.entity.AIService;
import cn.productai.api.core.enums.DetectType;
import cn.productai.api.core.enums.ServiceType;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.api.pai.base.CallApiByImageUrlBaseRequest;

import java.util.HashMap;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class DetectByImageUrlRequest extends CallApiByImageUrlBaseRequest<DetectResponse> {

    @Override
    public Class<DetectResponse> getResponseClass() {
        return DetectResponse.class;
    }

    private static HashMap<Integer, AIService> _detectServiceDicts = EnumHelper.toServiceHashMap(DetectType.class);
    private static HashMap<Integer, String> _serviceTypeDicts = EnumHelper.toHashMap(ServiceType.class);

    public DetectByImageUrlRequest(DetectType detectType) {
        super(_detectServiceDicts.get(detectType.ordinal()).getServiceType(),
                _detectServiceDicts.get(detectType.ordinal()).getServiceId());
    }

    public DetectByImageUrlRequest(DetectType detectType, String url, String loc) {
        super(_detectServiceDicts.get(detectType.ordinal()).getServiceType(),
                _detectServiceDicts.get(detectType.ordinal()).getServiceId(), url, loc);
    }

    public DetectByImageUrlRequest(String serviceType, String serviceId, String url, String loc) {
        super(serviceType, serviceId, url, loc);
    }

    public DetectByImageUrlRequest(ServiceType serviceType, String serviceId, String url, String loc) {
        super(_serviceTypeDicts.get(serviceType == null ? ServiceType.Classify.ordinal() : serviceType.ordinal()), serviceId, url, loc);
    }
}
