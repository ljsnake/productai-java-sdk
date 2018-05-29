package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.CustomTraining;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.lib.Http;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CustomTrainingImpl extends AbstractService implements CustomTraining {

    private static final Logger logger = LogManager.getLogger(CustomTrainingImpl.class);
    private static final String URL = BASE_URL + "/custom_training/_0000194/";

    @Override
    public String createService(String trainingSetId, String name, String description,
                                String scenario) {
        try {
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"description\":\"" + description + "\","
                    + "\"scenario\":\"" + scenario + "\""
                    + "}";
            String url = URL + trainingSetId + "/service";
            return Http.request(HttpMethod.POST, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("TrainingSet delete request error", paie);
            return null;
        }
    }

    @Override
    public String listAllService() {
        try {
            String url = URL + "service/list";
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("CustomTraining listAllService request error", paie);
            return null;
        }
    }

    @Override
    public String getServiceById(String serviceId) {
        try {
            String url = URL + serviceId + "/service/view";
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("CustomTraining getServiceById request error", paie);
            return null;
        }
    }

    @Override
    public String updateServiceName(String serviceId, String name) {
        try {
            String url = URL + serviceId + "/service";
            String json = "{\"name\":\"" + name + "\"}";
            return Http.request(HttpMethod.PUT, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("CustomTraining updateServiceName request error", paie);
            return null;
        }
    }

    @Override
    public String deleteServiceById(String serviceId) {
        try {
            String url = URL + serviceId + "/service";
            return Http.request(HttpMethod.DELETE, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("CustomTraining deleteServiceById request error", paie);
            return null;
        }
    }

    @Override
    public String predict(String serviceId, String imageUrl, String image) {
        try {
            if (imageUrl == null && image == null) {
                throw new PAIException("imageUrl and image can be null at the same time.");
            }

            String url = URL + "/" + serviceId;
            String json;
            if (image != null) {
                json = "{\"image\":\"" + image + "\"}";
            } else {
                json = "{\"image_url\":\"" + imageUrl + "\"}";
            }

            return Http.request(HttpMethod.POST, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("CustomTraining predict request error", paie);
            return null;
        }
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
