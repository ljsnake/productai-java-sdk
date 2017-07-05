package cn.productai.api.pai.entity.classify;

import cn.productai.api.core.entity.AIService;
import cn.productai.api.core.enums.ClassifyType;
import cn.productai.api.core.enums.ServiceType;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.api.pai.base.CallApiByImageFileBaseRequest;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class ClassifyByImageFileRequest extends CallApiByImageFileBaseRequest<ClassifyResponse> {

    @Override
    public Class<ClassifyResponse> getResponseClass() {
        return ClassifyResponse.class;
    }

    private static HashMap<Integer, AIService> _classifyServiceDicts = EnumHelper.toServiceHashMap(ClassifyType.class);
    private static HashMap<Integer, String> _serviceTypeDicts = EnumHelper.toHashMap(ServiceType.class);

    public ClassifyByImageFileRequest(ClassifyType classifyType) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(), _classifyServiceDicts.get(classifyType.ordinal()).getServiceId());
    }

    public ClassifyByImageFileRequest(ClassifyType classifyType, File imageFile) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(), _classifyServiceDicts.get(classifyType.ordinal()).getServiceId(), imageFile);
    }

    public ClassifyByImageFileRequest(String serviceType, String serviceId) {
        super(serviceType, serviceId);
    }

    public ClassifyByImageFileRequest(String serviceType, String serviceId, File imageFile) {
        super(serviceType, serviceId, imageFile);
    }

    /**
     *
     * @param serviceType should be ServiceType.Classify
     * @param serviceId your service id
     */
    public ClassifyByImageFileRequest(ServiceType serviceType, String serviceId) {
        super(_serviceTypeDicts.get(serviceType == null ? ServiceType.Classify.ordinal() : serviceType.ordinal()), serviceId);
    }

    /**
     *
     * @param serviceType should be ServiceType.Classify
     * @param serviceId your service id
     * @param imageFile the image file
     */
    public ClassifyByImageFileRequest(ServiceType serviceType, String serviceId, File imageFile) {
        super(_serviceTypeDicts.get(serviceType == null ? ServiceType.Classify.ordinal() : serviceType.ordinal()), serviceId, imageFile);
    }
}
