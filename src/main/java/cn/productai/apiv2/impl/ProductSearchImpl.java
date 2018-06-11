package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.ProductSearch;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.lib.Http;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductSearchImpl extends AbstractService implements ProductSearch {

    private static final Logger logger = Logger.getLogger(ProductSearchImpl.class.getName());
    private static final String URL = BASE_URL + "/product_sets/_0000178/services";

    @Override
    public String query(String serviceId, String imageUrl, String loc, Integer count, String tags,
                        String keywords, String minPrice, String maxPrice, Map<String, String> params) throws PAIException {
        if (loc == null) {
            loc = "0-0-1-1";
        }
        if (count == null) {
            count = 20;
        }
        try {
            String json = "{";
            if (tags != null) {
                json += "\"tags\":\"" + tags + "\",";
            }
            if (minPrice != null) {
                json += "\"min_price\":\"" + minPrice + "\",";
            }
            if (maxPrice != null) {
                json += "\"max_price\":\"" + maxPrice + "\",";
            }
            if (keywords != null) {
                json += "\"keywords\":\"" + keywords + "\",";
            }

            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    json += "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\",";
                }
            }
            json += "\"loc\":\"" + loc + "\","
                    + "\"count\":\"" + count + "\","
                    + "\"url\":\"" + imageUrl + "\""
                    + "}";

            String url = BASE_URL + "/product_search/" + serviceId;
            return Http.request(HttpMethod.POST, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "ProductSearch createService request error", paie);
            throw paie;
        }
    }

    @Override
    public String createService(String name, String scenario, String productSetId) throws PAIException {
        try {
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"scenario\":\"" + scenario + "\","
                    + "\"product_set_id\":\"" + productSetId + "\""
                    + "}";

            return Http.request(HttpMethod.POST, URL, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "ProductSearch createService request error", paie);
            throw paie;
        }
    }

    @Override
    public String listAllService() throws PAIException {
        try {
            return Http.request(HttpMethod.GET, URL, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "ProductSearch listAllService request error", paie);
            throw paie;
        }
    }

    @Override
    public String getServiceById(String productSetId) throws PAIException {
        try {
            String url = URL + "/" + productSetId;
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "ProductSearch getServiceById request error", paie);
            throw paie;
        }
    }

    @Override
    public String updateServiceName(String productSetId, String name) throws PAIException {
        try {
            String url = URL + "/" + productSetId;
            String json = "{\"name\":\"" + name + "\"}";
            return Http.request(HttpMethod.PATCH, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "ProductSearch updateServiceName request error", paie);
            throw paie;
        }
    }

    @Override
    public String deleteServiceById(String productSetId) throws PAIException {
        try {
            String url = URL + "/" + productSetId;
            return Http.request(HttpMethod.DELETE, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "ProductSearch deleteServiceById request error", paie);
            throw paie;
        }
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
