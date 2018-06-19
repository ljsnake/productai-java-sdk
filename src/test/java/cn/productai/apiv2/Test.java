package cn.productai.apiv2;

import cn.productai.api.core.DefaultProductAIClient;
import cn.productai.api.core.DefaultProfile;
import cn.productai.api.core.IProfile;
import cn.productai.api.core.IWebClient;
import cn.productai.api.pai.entity.classify.ClassifyByImageUrlRequest;
import cn.productai.api.pai.entity.classify.ClassifyResponse;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.impl.CustomTrainingImpl;
import cn.productai.apiv2.impl.TrainingSetImpl;

import java.io.File;


public class Test {
    public static void main(String[] args) {
        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("xxx");
        profile.setSecretKey("secret");

        try {
            trainingSetExamples(profile);
            customTrainingExamples(profile);
            classifyExamples(profile);
        } catch (PAIException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void classifyExamples(IProfile profile) throws Exception {
        IWebClient client = new DefaultProductAIClient(profile);

        ClassifyByImageUrlRequest request = new ClassifyByImageUrlRequest("classify", "_0000159");
        request.setUrl("https://img.alicdn.com/imgextra/i2/196993935/TB2bTfMqeOSBuNjy0FdXXbDnVXa-196993935.jpg");
        request.setCount(38);

        ClassifyResponse response = client.getResponse(request);
        String json = response.getResponseJsonString();
        System.out.println("json\n" + json);
    }

    static void customTrainingExamples(IProfile profile) throws PAIException {
        CustomTraining customTraining = new CustomTrainingImpl();
        customTraining.setProfile(profile);

        String result;
        String serviceId = "avhiclgf";

        result = customTraining.createService(serviceId,
                "Foo Service", "Foo Description", "classifier");
        System.out.println(">>> createService result: " + result);

        result = customTraining.listAllService();
        System.out.println(">>> listAllService result: " + result);

        result = customTraining.getServiceById(serviceId);
        System.out.println(">>> getServiceById result: " + result);

        result = customTraining.updateServiceName(serviceId, "Bar name");
        System.out.println(">>> updateServiceName result: " + result);

        result = customTraining.predict(serviceId, "https://styleai-shopping.oss-cn-beijing.aliyuncs.com/14d1b1e8a38ae767cc67834799be5ef9ed4595b9.jpg", null);
        System.out.println(">>> predict result: " + result);

        result = customTraining.deleteServiceById(serviceId);
        System.out.println(">>> deleteServiceById result: " + result);
    }

    static void trainingSetExamples(IProfile profile) throws PAIException {
        TrainingSet trainingSet = new TrainingSetImpl();
        trainingSet.setProfile(profile);

        String trainingSetId = "cx42ut7z";

        String result;

        result = trainingSet.create("foo name", "bar of description");
        System.out.println(">>> Create result: " + result);

        result = trainingSet.listAll();
        System.out.println(">>> List all result: " + result);

        result = trainingSet.getById(trainingSetId);
        System.out.println(">>> Get by id result: " + result);

        result = trainingSet.update(trainingSetId, "new name", "new desc");
        System.out.println(">>> Update by id result: " + result);

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File file = new File(classloader.getResource("raw_data.csv").getFile());
            result = trainingSet.bulkAddTrainingData(trainingSetId, file);
            System.out.println(">>> bulkAddTrainingData result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File file = new File(classloader.getResource("raw_data_delete.csv").getFile());
            result = trainingSet.bulkDeleteTrainingData(trainingSetId, file);
            System.out.println(">>> bulkDeleteTrainingData result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = trainingSet.clearTrainingSet(trainingSetId, "Foo Name");
        System.out.println(">>> clearTrainingSet by id result: " + result);

        result = trainingSet.delete(trainingSetId);
        System.out.println(">>> Delete by id result: " + result);
    }
}
