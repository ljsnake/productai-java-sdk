package cn.productai.api.pai.entity.dataset;

import cn.productai.api.pai.base.DataSetBatchModifyByFileBaseRequest;

import java.io.File;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class DataSetBatchDeleteRequest extends DataSetBatchModifyByFileBaseRequest<DataSetModifyResponse> {

    @Override
    public Class<DataSetModifyResponse> getResponseClass() {
        return DataSetModifyResponse.class;
    }

    public DataSetBatchDeleteRequest(String imageSetId) {
        super(imageSetId, "urls_to_delete");
    }

    public DataSetBatchDeleteRequest(String imageSetId, File csvFile) {
        super(imageSetId, csvFile, "urls_to_delete");
    }
}
