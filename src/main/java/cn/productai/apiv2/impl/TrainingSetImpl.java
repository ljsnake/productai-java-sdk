package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.TrainingSet;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.lib.Http;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Base64;

public class TrainingSetImpl extends AbstractService implements TrainingSet {

    private static final Logger logger = LogManager.getLogger(TrainingSetImpl.class);
    //    private static final String URL = BASE_URL + "/custom_training/_0000194/training_set";
    private static final String URL = BASE_URL + "/custom_training/_0000194/";

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
            String url = URL + "training_set";
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("TrainingSet listAll request error", paie);
            return null;
        }
    }

    @Override
    public String getById(String trainingSetId) {
        try {
            String url = URL + trainingSetId + "/training_set";
            System.out.println("getById URL: " + url);
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("TrainingSet getById request error", paie);
            return null;
        }
    }

    @Override
    public String update(String trainingSetId, String name, String description) {
        try {
            String url = URL + trainingSetId + "/training_set";
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
            String url = URL + trainingSetId + "/training_set";
            return Http.request(HttpMethod.DELETE, url, getHeaders());
        } catch (PAIException paie) {
            logger.error("TrainingSet delete request error", paie);
            return null;
        }
    }

    @Override
    public String bulkAddTrainingData(String trainingSetId, File file) {
        try {
            String url = URL + trainingSetId + "/training_set/file";
            return Http.request(HttpMethod.POST, url, getHeaders(), "csv_file", file);
        } catch (PAIException paie) {
            logger.error("TrainingSet bulkAddTrainingData request error", paie);
            return null;
        }
    }

    @Override
    public String bulkDeleteTrainingData(String trainingSetId, File file) {
        try {
            String url = URL + trainingSetId + "/training_set/file";
            return Http.request(HttpMethod.DELETE, url, getHeaders(), "csv_file", file);
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
            String url = URL + trainingSetId + "/training_set";
            return Http.request(HttpMethod.DELETE, url, getHeaders(), json);
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
