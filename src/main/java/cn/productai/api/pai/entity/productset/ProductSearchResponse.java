package cn.productai.api.pai.entity.productset;

import cn.productai.api.pai.entity.dataset.DataSetBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductSearchResponse extends DataSetBaseResponse {

    @JsonProperty("results")
    private Product[] results;
}
