package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.TrainingSet;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.lib.Http;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;

public class TrainingSetImpl extends AbstractService implements TrainingSet {

    private static final Logger logger = LogManager.getLogger(TrainingSetImpl.class);
    private static final String URL = BASE_URL + "/custom_training/_0000194/training_set";

    @Override
    public String create(String name, String description) {
        try {
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"description\":\"" + description + "\""
                    + "}";
            return Http.request(HttpMethod.POST, URL, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("TrainingSet create error", paie);
            return null;
        }
    }

    @Override
    public String listAll() {
        try {
            return Http.request(HttpMethod.GET, URL, getHeaders());
        } catch (PAIException paie) {
            logger.error("TrainingSet listAll request error", paie);
            return null;
        }
    }

    @Override
    public String getById(String trainingSetId) {
        try {
            String url = URL + "/" + trainingSetId;
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("TrainingSet getById request error", paie);
            return null;
        }
    }

    @Override
    public String update(String trainingSetId, String name, String description) {
        try {
            String url = URL + "/" + trainingSetId;
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"description\":\"" + description + "\""
                    + "}";
            return Http.request(HttpMethod.PUT, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("TrainingSet trainingSetId request error", paie);
            return null;
        }
    }

    @Override
    public String delete(String trainingSetId) {
        try {
            String url = URL + "/" + trainingSetId;
            return Http.request(HttpMethod.DELETE, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("TrainingSet delete request error", paie);
            return null;
        }
    }

    @Override
    public String bulkAddTrainingData(String fileContent) {
        try {
            String url = URL + "/file";
            String json = "{"
                    + "\"csv_file\":\"" + fileContent + "\""
                    + "}";
            return Http.request(HttpMethod.POST, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("TrainingSet bulkAddTrainingData request error", paie);
            return null;
        }
    }

    @Override
    public String bulkDeleteTrainingData(String fileContent) {
        try {
            String url = URL + "/file";
            String json = "{"
                    + "\"csv_file\":\"" + fileContent + "\""
                    + "}";
            return Http.request(HttpMethod.DELETE, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("TrainingSet bulkDeleteTrainingData request error", paie);
            return null;
        }
    }

    @Override
    public String clearTrainingSet(String trainingSetId, String name) {
        try {
            String token = Base64.getEncoder().encodeToString(("selfserve_admin" + name).getBytes());
            String json = "{"
                    + "\"training_set_id\":\"" + trainingSetId + "\","
                    + "\"name\":\"" + name + "\","
                    + "\"token\":\"" + token + "\""
                    + "}";
            return Http.request(HttpMethod.DELETE, URL, getHeaders(), json);
        } catch (PAIException paie) {
            logger.error("TrainingSet delete request error", paie);
            return null;
        }
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
