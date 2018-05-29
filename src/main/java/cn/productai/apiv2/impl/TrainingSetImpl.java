package cn.productai.apiv2.impl;

import cn.productai.api.core.IProfile;
import cn.productai.apiv2.TrainingSet;

import java.io.File;

public class TrainingSetImpl extends AbstractService implements TrainingSet {

    @Override
    public void setProfile(IProfile profile) {
    }

    @Override
    public String create(String name, String scenario, String productSetId) {
        return null;
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
}
