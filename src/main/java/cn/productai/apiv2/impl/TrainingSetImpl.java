package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.TrainingSet;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.lib.Http;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingSetImpl extends AbstractService implements TrainingSet {

    private static final Logger logger = Logger.getLogger(TrainingSetImpl.class.getName());
    private static final String URL = BASE_URL + "/custom_training/_0000194";

    @Override
    public String create(String name, String description) throws PAIException {
        try {
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"description\":\"" + description + "\""
                    + "}";
            String url = URL + "/training_set";
            return Http.request(HttpMethod.POST, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet create error", paie);
            throw paie;
        }
    }

    @Override
    public String listAll() throws PAIException {
        try {
            String url = URL + "/training_sets";
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet listAll request error", paie);
            throw paie;
        }
    }

    @Override
    public String getById(String trainingSetId) throws PAIException {
        try {
            String url = URL + "/training_sets/" + trainingSetId;
            return Http.request(HttpMethod.GET, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet getById request error", paie);
            throw paie;
        }
    }

    @Override
    public String update(String trainingSetId, String name, String description) throws PAIException {
        try {
            String url = URL + "/training_sets/" + trainingSetId;
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"description\":\"" + description + "\""
                    + "}";
            return Http.request(HttpMethod.PUT, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet trainingSetId request error", paie);
            throw paie;
        }
    }

    @Override
    public String delete(String trainingSetId) throws PAIException {
        try {
            String url = URL + "/training_sets/" + trainingSetId;
            return Http.request(HttpMethod.DELETE, url, getHeaders());
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet delete request error", paie);
            throw paie;
        }
    }

    @Override
    public String bulkAddTrainingData(String trainingSetId, File file) throws PAIException {
        try {
            String url = URL + "/training_sets/" + trainingSetId + "/images";
            return Http.request(HttpMethod.POST, url, getHeaders(), "csv_file", file);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet bulkAddTrainingData request error", paie);
            throw paie;
        }
    }

    @Override
    public String bulkDeleteTrainingData(String trainingSetId, File file) throws PAIException {
        try {
            String url = URL + "/training_sets/" + trainingSetId + "/images";
            return Http.request(HttpMethod.DELETE, url, getHeaders(), "csv_file", file);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "TrainingSet bulkDeleteTrainingData request error", paie);
            throw paie;
        }
    }

    @Override
    public String clearTrainingSet(String trainingSetId, String name) throws PAIException {
        try {
            String token = new BASE64Encoder().encode(("selfserve_admin:" + name).getBytes());
            String json = "{"
                    + "\"training_set_id\":\"" + trainingSetId + "\","
                    + "\"name\":\"" + name + "\","
                    + "\"token\":\"" + token + "\""
                    + "}";
            String url = URL + "/training_sets/" + trainingSetId + "/clear";
            return Http.request(HttpMethod.DELETE, url, getHeaders(), json);
        } catch (PAIException paie) {
            logger.log(Level.SEVERE, "Clear trainingSet request error", paie);
            throw paie;
        }
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
