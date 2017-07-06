package cn.productai.api.pai.entity.dressing;

import cn.productai.api.core.base.BaseResponse;
import cn.productai.api.pai.response.DressingClassifyResult;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Zhong Wang on 2017/7/4.
 *
 */
public class DressingClassifyResponse extends BaseResponse {

    @JsonProperty("results")
    private DressingClassifyResult[] results;

    @JsonProperty("labels")
    private String[] labels;

    public DressingClassifyResult[] getResults() {
        return results;
    }

    public void setResults(DressingClassifyResult[] results) {
        this.results = results;
    }
}
