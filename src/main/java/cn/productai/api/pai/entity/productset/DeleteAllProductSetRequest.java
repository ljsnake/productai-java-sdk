package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.base.BaseRequest;
import cn.productai.api.core.enums.HttpMethod;

public class DeleteAllProductSetRequest extends BaseRequest<DeleteAllProductSetResponse> {

    public DeleteAllProductSetRequest() {
        super();
        this.setRequestMethod(HttpMethod.DELETE);
    }

    @Override
    public Class<DeleteAllProductSetResponse> getResponseClass() {
        return DeleteAllProductSetResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/_0000178", this.getHost());
    }
}
