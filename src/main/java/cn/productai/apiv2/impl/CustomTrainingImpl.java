package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.apiv2.CustomTraining;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class CustomTrainingImpl extends AbstractService implements CustomTraining {

    private static final Logger logger = LogManager.getLogger(CustomTrainingImpl.class);
    private static final String URL = BASE_URL + "/custom_training/_0000194";

    @Override
    public void setProfile(IProfile profile) {

    }

    @Override
    public String listAllService() {
        return null;
    }

    @Override
    public String getServiceById(String serviceId) {
        return null;
    }

    @Override
    public String updateServiceName(String serviceId, String name) {
        return null;
    }

    @Override
    public String deleteServiceById(String serviceId) {
        return null;
    }

    @Override
    public String predict(String serviceId, String imageUrl, File image) {
        return null;
    }
}
