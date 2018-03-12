package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.base.BaseRequest;
import cn.productai.api.core.enums.HttpMethod;

public class GetProductSetRequest extends BaseRequest<GetProductSetResponse> {

    private String productSetId;

    public GetProductSetRequest() {
        super();
        this.setRequestMethod(HttpMethod.GET);
    }

    @Override
    public Class<GetProductSetResponse> getResponseClass() {
        return GetProductSetResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/_0000178/%s", this.getHost(), this.getProductSetId());
    }

    public String getProductSetId() {
        return productSetId;
    }

    public void setProductSetId(String productSetId) {
        this.productSetId = productSetId;
    }
}
