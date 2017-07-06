package cn.productai.api.pai.entity.dataset;

import cn.productai.api.pai.base.DataSetSingleModifyByUrlBaseRequest;

import java.util.ArrayList;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class DataSetSingleAddByImageUrlRequest extends DataSetSingleModifyByUrlBaseRequest<DataSetModifyResponse> {

    @Override
    public Class<DataSetModifyResponse> getResponseClass() {
        return DataSetModifyResponse.class;
    }

    public DataSetSingleAddByImageUrlRequest(String imageSetId, ArrayList<String> tags, String meta) {
        super(imageSetId, tags, meta);
    }

    public DataSetSingleAddByImageUrlRequest(String imageSetId, String url, ArrayList<String> tags, String meta) {
        super(imageSetId, url, tags, meta);
    }
}
