package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.attribute.ParaSignAttribute;
import cn.productai.api.core.base.ManagementAPIBaseRequest;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.api.core.exceptions.ClientException;

public class ProductSearchRequest extends ManagementAPIBaseRequest<UpdateProductSetResponse> {

    @ParaSignAttribute(Name = "loc")
    public String loc;

    @ParaSignAttribute(Name = "tags")
    public String tags;

    @ParaSignAttribute(Name = "count")
    public Integer count;

    @ParaSignAttribute(Name = "page")
    public Integer page;

    @ParaSignAttribute(Name = "min_price")
    public Double minPrice;

    @ParaSignAttribute(Name = "max_price")
    public Double maxPrice;

    @ParaSignAttribute(Name = "keywords")
    public String keywords;

    private String productSetId;

    private String serviceId;

    public ProductSearchRequest() {
        super();
        this.setRequestMethod(HttpMethod.POST);
    }

    public ProductSearchRequest(String productSetId,
                                String serviceId,
                                String loc,
                                Integer count,
                                Integer page,
                                Double minPrice,
                                Double maxPrice,
                                String keywords) throws ClientException {
        this();
        this.setProductSetId(productSetId);
        this.setServiceId(serviceId);

        if (loc != null) {
            this.setLoc(loc);
        }
        if (count != null) {
            this.setCount(count);
        }
        if (page != null) {
            this.setPage(page);
        }
        if (minPrice != null) {
            if (minPrice < 0) {
                throw new ClientException("SDK.ProductSearchRequest", "min_price must large or equal than 0");
            }
            this.setMinPrice(minPrice);
        }
        if (maxPrice != null) {
            if (maxPrice < 0) {
                throw new ClientException("SDK.ProductSearchRequest", "max_price must large or equal than 0");
            }
            this.setMaxPrice(maxPrice);
        }
        if (keywords != null) {
            this.setKeywords(keywords);
        }
    }

    @Override
    public Class<UpdateProductSetResponse> getResponseClass() {
        return UpdateProductSetResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/_0000178/%s", this.getHost(), this.getProductSetId());
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getProductSetId() {
        return productSetId;
    }

    public void setProductSetId(String productSetId) {
        this.productSetId = productSetId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
