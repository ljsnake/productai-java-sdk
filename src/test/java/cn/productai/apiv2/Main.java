package cn.productai.apiv2;

import cn.productai.api.core.DefaultProfile;
import cn.productai.api.core.IProfile;
import cn.productai.apiv2.impl.CustomTrainingImpl;
import cn.productai.apiv2.impl.TrainingSetImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("249f5ce644aae36b6eb138e87af9e2fd");

        trainingSetExamples(profile);

//        customTrainingExamples(profile);
    }

    static void customTrainingExamples(IProfile profile) {
        CustomTraining customTraining = new CustomTrainingImpl();
        customTraining.setProfile(profile);

        String result;

        result = customTraining.createService("gd2a2sf3",
                "Foo Service", "Foo Description", "classifier");
        System.out.println(">>> createService result: " + result);

        result = customTraining.listAllService();
        System.out.println(">>> listAllService result: " + result);

        result = customTraining.getServiceById("xdku0jqm");
        System.out.println(">>> getServiceById result: " + result);

        result = customTraining.updateServiceName("xdku0jqm", "Bar name");
        System.out.println(">>> updateServiceName result: " + result);

        result = customTraining.deleteServiceById("xdku0jqm");
        System.out.println(">>> deleteServiceById result: " + result);
    }

    static void trainingSetExamples(IProfile profile) {
        TrainingSet trainingSet = new TrainingSetImpl();
        trainingSet.setProfile(profile);

        String result;

//        result = trainingSet.create("foo name", "bar of description");
//        System.out.println(">>> Create result: " + result);

        result = trainingSet.listAll();
        System.out.println(">>> List all result: " + result);

        result = trainingSet.getById("gd2a2sf3");
        System.out.println(">>> Get by id result: " + result);

        result = trainingSet.update("gd2a2sf3", "new name", "new desc");
        System.out.println(">>> Update by id result: " + result);

        result = trainingSet.delete("gd2a2sf3");
        System.out.println(">>> Delete by id result: " + result);

//        try {
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//            File file = new File(classloader.getResource("raw_data.csv").getFile());
//            result = trainingSet.bulkAddTrainingData("gd2a2sf3", file);
//            System.out.println(">>> bulkAddTrainingData result: " + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//            File file = new File(classloader.getResource("raw_data_delete.csv").getFile());
//            result = trainingSet.bulkDeleteTrainingData("gd2a2sf3", file);
//            System.out.println(">>> bulkDeleteTrainingData result: " + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        result = trainingSet.clearTrainingSet("antvpwdp", "foo name");
        System.out.println(">>> clearTrainingSet by id result: " + result);
    }
}
