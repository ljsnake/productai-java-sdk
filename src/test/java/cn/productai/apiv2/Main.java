package cn.productai.apiv2;

import cn.productai.api.core.DefaultProfile;
import cn.productai.api.core.IProfile;
import cn.productai.apiv2.impl.TrainingSetImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("249f5ce644aae36b6eb138e87af9e2fd");

        TrainingSet trainingSet = new TrainingSetImpl();
        trainingSet.setProfile(profile);

        String result;

//        result = trainingSet.create("foo name", "bar of description");
//        System.out.println(">>> Create result: " + result);

        result = trainingSet.listAll();
        System.out.println(">>> List all result: " + result);

//        result = trainingSet.getById("gd2a2sf3");
//        System.out.println(">>> Get by id result: " + result);
//
//        result = trainingSet.update("gd2a2sf3", "new name", "new desc");
//        System.out.println(">>> Update by id result: " + result);

//        result = trainingSet.delete("2kt1uc5p");
//        System.out.println(">>> Delete by id result: " + result);

//        try {
//            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//            File file = new File(classloader.getResource("raw_data.csv").getFile());
//            result = trainingSet.bulkAddTrainingData("2kt1uc5p", file);
//            System.out.println(">>> Delete by id result: " + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        result = trainingSet.clearTrainingSet("antvpwdp", "foo name");
        System.out.println(">>> clearTrainingSet by id result: " + result);
    }
}
