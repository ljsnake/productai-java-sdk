package cn.productai.api.pai.entity.classify;

import cn.productai.api.core.entity.AIService;
import cn.productai.api.core.enums.ClassifyType;
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

    public ClassifyByImageUrlRequest(ClassifyType classifyType) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(),
                _classifyServiceDicts.get(classifyType.ordinal()).getServiceId());
    }

    public ClassifyByImageUrlRequest(ClassifyType classifyType, String imageUrl) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(),
                _classifyServiceDicts.get(classifyType.ordinal()).getServiceId(), imageUrl);
    }

    public ClassifyByImageUrlRequest(String serviceTye, String serviceId) {
        super(serviceTye, serviceId);
    }

    public ClassifyByImageUrlRequest(String serviceTye, String serviceId, String imageUrl) {
        super(serviceTye, serviceId, imageUrl);
    }
}
