package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.base.ManagementAPIBaseRequest;
import cn.productai.api.core.enums.HttpMethod;

public class DeleteProductsByProductSetRequest extends ManagementAPIBaseRequest<AddProductResponse> {

    private String productSetId;

    public DeleteProductsByProductSetRequest() {
        super();
        this.setRequestMethod(HttpMethod.PUT);
    }

    /**
     * @param productSetId 商品集ID
     */
    public DeleteProductsByProductSetRequest(String productSetId) {
        this();
        this.setProductSetId(productSetId);
    }

    @Override
    public Class<AddProductResponse> getResponseClass() {
        return AddProductResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/_0000178/%s/products", this.getHost(), this.getProductSetId());
    }

    public String getProductSetId() {
        return productSetId;
    }

    public void setProductSetId(String productSetId) {
        this.productSetId = productSetId;
    }
}
