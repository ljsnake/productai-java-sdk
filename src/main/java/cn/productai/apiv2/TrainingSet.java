package cn.productai.apiv2;

import cn.productai.api.core.IProfile;

import java.io.File;

public interface TrainingSet {

    void setProfile(IProfile profile);

    String create(String name, String description);

    String listAll();

    String getById(String trainingSetId);

    String update(String trainingSetId, String name, String description);

    String delete(String trainingSetId);

    String bulkAddTrainingData(File file);

    String bulkDeleteTrainingData(File file);

    String clearTrainingSet(String trainingSetId, String name);
}
