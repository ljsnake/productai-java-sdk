package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.base.BaseRequest;
import cn.productai.api.core.enums.HttpMethod;

public class DeleteProductsSetRequest extends BaseRequest<DeleteProductSetResponse> {

    private String productSetId;

    public DeleteProductsSetRequest() {
        super();
        this.setRequestMethod(HttpMethod.DELETE);
    }

    /**
     * @param productSetId 商品集ID
     */
    public DeleteProductsSetRequest(String productSetId) {
        this();
        this.setProductSetId(productSetId);
    }

    @Override
    public Class<DeleteProductSetResponse> getResponseClass() {
        return DeleteProductSetResponse.class;
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
