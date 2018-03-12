package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.base.BaseResponse;
import cn.productai.api.pai.entity.dataset.Dataset;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProductSetResponse extends BaseResponse {

    @JsonProperty("results")
    private Dataset[] Datasets;

    public Dataset[] getDatasets() {
        return Datasets;
    }

    public void setDatasets(Dataset[] datasets) {
        Datasets = datasets;
    }
}
