package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.apiv2.TrainingSet;
import cn.productai.apiv2.exceptions.PAIException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class TrainingSetImpl extends AbstractService implements TrainingSet {

    private static final Logger logger = LogManager.getLogger(TrainingSetImpl.class);
    private static final String URL = BASE_URL + "/custom_training/_0000194";

    @Override
    public String create(String name, String description) {
        try {
            String json = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"scenario\":\"" + scenario + "\","
                    + "\"product_set_id\":\"" + productSetId + "\""
                    + "}";
        } catch (PAIException paie) {
            logger.error("");
        }
    }

    @Override
    public String listAll() {
        return null;
    }

    @Override
    public String getById(String trainingSetId) {
        return null;
    }

    @Override
    public String update(String trainingSetId, String name, String description) {
        return null;
    }

    @Override
    public String delete(String trainingSetId) {
        return null;
    }

    @Override
    public String bulkAddTrainingData(File file) {
        return null;
    }

    @Override
    public String bulkDeleteTrainingData(File file) {
        return null;
    }

    @Override
    public String clearTrainingSet(String trainingSetId, String name) {
        return null;
    }

    @Override
    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
