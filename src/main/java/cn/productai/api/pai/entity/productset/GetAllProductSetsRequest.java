package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.base.BaseRequest;
import cn.productai.api.core.enums.HttpMethod;

public class GetAllProductSetsRequest extends BaseRequest<GetAllProductSetsResponse> {

    public GetAllProductSetsRequest() {
        super();
        this.setRequestMethod(HttpMethod.GET);
    }

    @Override
    public Class<GetAllProductSetsResponse> getResponseClass() {
        return GetAllProductSetsResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/_0000178", this.getHost());
    }
}
