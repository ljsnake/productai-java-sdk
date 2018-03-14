package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.base.BaseRequest;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.api.core.enums.ServiceTypeId;

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
        return String.format("https://%s/product_sets/%s", this.getHost(), ServiceTypeId.PRODUCT_SET);
    }
}
