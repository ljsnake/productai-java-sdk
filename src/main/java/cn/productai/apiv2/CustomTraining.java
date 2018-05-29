package cn.productai.apiv2;

import cn.productai.api.core.IProfile;

import java.io.File;

public interface CustomTraining {

    void setProfile(IProfile profile);

    String listAllService();

    String getServiceById(String serviceId);

    String updateServiceName(String serviceId, String name);

    String deleteServiceById(String serviceId);

    String predict(String serviceId, String imageUrl, File image);
}
