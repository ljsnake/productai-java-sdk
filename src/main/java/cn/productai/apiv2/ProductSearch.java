package cn.productai.apiv2;

import cn.productai.api.core.IProfile;
import cn.productai.apiv2.exceptions.PAIException;

import java.util.Map;

public interface ProductSearch {

    String query(String serviceId, String imageUrl, String loc, Integer count, String tags,
                 String keywords, String minPrice, String maxPrice, Map<String, String> params) throws PAIException;

    String createService(String name, String scenario, String productSetId) throws PAIException;

    String listAllService() throws PAIException;

    String getServiceById(String productSetId) throws PAIException;

    String updateServiceName(String productSetId, String name) throws PAIException;

    String deleteServiceById(String productSetId) throws PAIException;

    void setProfile(IProfile profile);
}
