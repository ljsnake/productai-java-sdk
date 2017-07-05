package cn.productai.api.pai.entity.detect;

import cn.productai.api.core.entity.AIService;
import cn.productai.api.core.enums.DetectType;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.api.pai.base.CallApiByImageFileBaseRequest;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class DetectByImageFileRequest extends CallApiByImageFileBaseRequest<DetectResponse> {

    @Override
    public Class<DetectResponse> getResponseClass() {
        return DetectResponse.class;
    }

    private static HashMap<Integer, AIService> _detectServiceDicts = EnumHelper.toServiceHashMap(DetectType.class);

    public DetectByImageFileRequest(DetectType detectType) {
        super(_detectServiceDicts.get(detectType.ordinal()).getServiceType(),
                _detectServiceDicts.get(detectType.ordinal()).getServiceId());
    }

    public DetectByImageFileRequest(DetectType detectType, File imageFile, String loc) {
        super(_detectServiceDicts.get(detectType.ordinal()).getServiceType(),
                _detectServiceDicts.get(detectType.ordinal()).getServiceId(), imageFile, loc);
    }

    public DetectByImageFileRequest(String serviceType, String serviceId, File imageFile, String loc) {
        super(serviceType, serviceId, imageFile, loc);
    }
}
