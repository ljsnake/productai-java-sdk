package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.attribute.ParaSignAttribute;
import cn.productai.api.core.base.ManagementAPIBaseRequest;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.api.core.enums.ServiceTypeId;

public class GetProductsByProductSetRequest extends ManagementAPIBaseRequest<GetProductsByProductSetResponse> {

    private String productSetId;

    @ParaSignAttribute(Name = "ids")
    private String[] productIds;

    public GetProductsByProductSetRequest() {
        super();
    }

    public GetProductsByProductSetRequest(String productSetId, String[] productIds) {
        super();
        this.setProductSetId(productSetId);
        this.setProductIds(productIds);
        this.setRequestMethod(HttpMethod.GET);
    }

    @Override
    public Class<GetProductsByProductSetResponse> getResponseClass() {
        return GetProductsByProductSetResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/%s/%s/products",
                this.getHost(), ServiceTypeId.PRODUCT_SET, this.getProductSetId());
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
