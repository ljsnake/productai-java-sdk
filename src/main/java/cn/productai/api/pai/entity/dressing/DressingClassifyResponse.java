package cn.productai.api.pai.entity.dressing;

import cn.productai.api.core.base.BaseResponse;
import cn.productai.api.pai.response.DressingClassifyResult;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class DressingClassifyResponse extends BaseResponse {

    @JsonProperty("results")
    private DressingClassifyResult[] results;

    public DressingClassifyResult[] getResults() {
        return results;
    }

    public void setResults(DressingClassifyResult[] results) {
        this.results = results;
    }
}
