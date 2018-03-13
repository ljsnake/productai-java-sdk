package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.attribute.ParaSignAttribute;
import cn.productai.api.core.base.ManagementAPIBaseRequest;
import cn.productai.api.core.enums.HttpMethod;

public class DeleteProductsByProductSetRequest extends ManagementAPIBaseRequest<DeleteProductsByProductSetResponse> {

    private String productSetId;

    @ParaSignAttribute(Name = "ids")
    private String[] productIds;

    public DeleteProductsByProductSetRequest() {
        super();
        this.setRequestMethod(HttpMethod.DELETE);
    }

    /**
     * @param productSetId 商品集ID
     */
    public DeleteProductsByProductSetRequest(String productSetId, String[] productIds) {
        this();
        this.setProductSetId(productSetId);
        this.setProductIds(productIds);
        this.setProductSetId(productSetId);
    }

    @Override
    public Class<DeleteProductsByProductSetResponse> getResponseClass() {
        return DeleteProductsByProductSetResponse.class;
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

    public String[] getProductIds() {
        return productIds;
    }

    public void setProductIds(String[] productIds) {
        this.productIds = productIds;
    }
}
