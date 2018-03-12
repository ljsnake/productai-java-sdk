package cn.productai.api.pai.entity.productset;

import cn.productai.api.pai.entity.dataset.DataSetBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateProductSetResponse extends DataSetBaseResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
