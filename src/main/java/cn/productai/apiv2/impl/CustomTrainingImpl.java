package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.CustomTraining;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.lib.Http;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomTrainingImpl extends AbstractService implements CustomTraining {

    private static final Logger logger = Logger.getLogger(CustomTrainingImpl.class.getName());
    private static final String URL = BASE_URL + "/custom_training/_0000194";

    @Override
    public String createService(String trainingSetId, String name, String description,
                                String scenario) throws PAIException {
        try {
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"description\":\"" + description + "\","
                    + "\"scenario\":\"" + scenario + "\""
                    + "}";
            String url = URL + "/training_sets/" + trainingSetId + "/services";
            return Http.request(HttpMethod.POST, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet delete request error", paie);
            throw paie;
        }
    }

    @Override
    public String listAllService() throws PAIException {
        try {
            String url = URL + "/services";
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "CustomTraining listAllService request error", paie);
            throw paie;
        }
    }

    @Override
    public String getServiceById(String serviceId) throws PAIException {
        try {
            String url = URL + "/services/" + serviceId;
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "CustomTraining getServiceById request error", paie);
            return null;
        }
    }

    @Override
    public String updateServiceName(String serviceId, String name) throws PAIException {
        try {
            String url = URL + "/services/" + serviceId;
            String json = "{\"name\":\"" + name + "\"}";
            return Http.request(HttpMethod.PUT, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "CustomTraining updateServiceName request error", paie);
            throw paie;
        }
    }

    @Override
    public String deleteServiceById(String serviceId) throws PAIException {
        try {
            String url = URL + "/services/" + serviceId;
            return Http.request(HttpMethod.DELETE, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "CustomTraining deleteServiceById request error", paie);
            throw paie;
        }
    }

    @Override
    public String predict(String serviceId, String imageUrl) throws PAIException {
        try {
            if (imageUrl == null) {
                throw new PAIException("imageUrl is required");
            }

            String url = BASE_URL + "/custom_training/" + serviceId;
            String json = "{\"image_url\":\"" + imageUrl + "\"}";
            return Http.request(HttpMethod.POST, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "CustomTraining predict request error", paie);
            throw paie;
        }
    }

    @Override
    public String predict(String serviceId, File image) throws PAIException {
        try {
            if (image == null) {
                throw new PAIException("image is required");
            }

            String url = BASE_URL + "/custom_training/" + serviceId;
            return Http.request(HttpMethod.POST, url, getHeaders(), "search", image);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "CustomTraining predict request error", paie);
            throw paie;
        }
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
