package cn.productai.apiv2;

import cn.productai.api.core.IProfile;

public interface CustomTraining {

    void setProfile(IProfile profile);

    String listAllService();

    String getServiceById(String serviceId);

    String updateServiceName(String serviceId, String name);

    String deleteServiceById(String serviceId);

    String predict(String serviceId, String imageUrl, String image);

    String createService(String trainingSetId, String name, String description, String scenario);
}
