package cn.productai.apiv2;

import cn.productai.api.core.DefaultProfile;
import cn.productai.api.core.IProfile;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.impl.CustomTrainingImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CustomTrainingTest {

    private static IProfile profile = new DefaultProfile();
    private static CustomTraining customTraining = new CustomTrainingImpl();

    @BeforeAll
    static void initAll() {
        profile.setAccessKeyId(System.getenv("X_CA_ACCESS_KEY_ID"));
        customTraining.setProfile(profile);
    }

    @Test
    @DisplayName("List all services")
    void testListAllService() {
        try {
            String result = customTraining.listAllService();
            System.out.println("List services");
            System.out.println(result);
        } catch (PAIException e) {
            System.out.println("Fail");
        }
    }

    @Test
    @DisplayName("Get service by Id")
    void testGetServiceById() {
        try {
            String productSetId = "0ingz90g";
            String result = customTraining.getServiceById(productSetId);
            System.out.println("Get service by Id");
            System.out.println(result);
        } catch (PAIException e) {
            System.out.println("Fail");
        }
    }

    @Test
    @DisplayName("Update service")
    void testUpdateService() {
        try {
            String productSetId = "0ingz90g";
            String result = customTraining.updateServiceName(productSetId, "new name");
            System.out.println("Update service");
            System.out.println(result);
        } catch (PAIException e) {
            System.out.println("Fail");
        }
    }

    @Test
    @DisplayName("Delete service")
    void testDeleteService() {
        try {
            String productSetId = "0ingz90g";
            String result = customTraining.deleteServiceById(productSetId);
            System.out.println("Delete service");
            System.out.println(result);
        } catch (PAIException e) {
            System.out.println("Fail");
        }
    }

    @Test
    @DisplayName("Predict")
    void testPredict() {
        try {
            String serviceId = "0ingz90g";
            String image = "";
            String result = customTraining.predict(serviceId, null, image);
            System.out.println("Delete service");
            System.out.println(result);
        } catch (PAIException e) {
            System.out.println("Fail");
        }
    }
}
