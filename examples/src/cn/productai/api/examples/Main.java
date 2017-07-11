package cn.productai.api.examples;

import cn.productai.api.core.*;
import cn.productai.api.core.enums.LanguageType;

public class Main {

    public static void main(String[] args) {

        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("3c289113a9b86b63f46551c895c2a617");
        profile.setSecretKey("****");
        profile.setVersion("1");
        profile.setGlobalLanguage(LanguageType.Chinese);

        IWebClient client = new DefaultProductAIClient(profile);

        /**
         * Classify
         */
        IExample classify_example = new ClassifyByFileExample();
        classify_example.run(client);

        /**
         * Image search
         */
        IExample search_example = new ImageSearchExample();
        search_example.run(client);

        /**
         * Detect
         */
        IExample detect_example = new DetectExample();
        detect_example.run(client);

        /**
         * Filter
         */
        IExample filter_example = new SmartFilterExample();
        filter_example.run(client);

        /**
         * Data set
         */
        IExample dataSet_example = new DataSetManagementExample();
        dataSet_example.run(client);

        System.out.println("Done");
    }
}
