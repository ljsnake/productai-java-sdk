package cn.productai.api.pai.entity.classify;

import cn.productai.api.core.entity.AIService;
import cn.productai.api.core.enums.ClassifyType;
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

    public ClassifyByImageFileRequest(ClassifyType classifyType) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(), _classifyServiceDicts.get(classifyType.ordinal()).getServiceId());
    }

    public ClassifyByImageFileRequest(ClassifyType classifyType, File imageFile) {
        super(_classifyServiceDicts.get(classifyType.ordinal()).getServiceType(), _classifyServiceDicts.get(classifyType.ordinal()).getServiceId(), imageFile);
    }

    public ClassifyByImageFileRequest(String serviceTye, String serviceId) {
        super(serviceTye, serviceId);
    }

    public ClassifyByImageFileRequest(String serviceTye, String serviceId, File imageFile) {
        super(serviceTye, serviceId, imageFile);
    }
}
