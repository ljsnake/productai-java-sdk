package cn.productai.apiv2;

import cn.productai.api.core.IProfile;

public interface ProductSearch {

    void setProfile(IProfile profile);

    String createService(String name, String scenario, String productSetId);

    String listAllService();

    String getServiceById(String productSetId);

    String updateServiceName(String productSetId, String name);

    String deleteServiceById(String productSetId);
}
