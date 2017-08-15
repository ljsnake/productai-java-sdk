package cn.productai.api.examples;

import cn.productai.api.core.*;
import cn.productai.api.core.enums.LanguageType;

public class Main {

    public static void main(String[] args) {

        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("3c289113a9b86b63f46551c895c2a617");
        profile.setSecretKey("*********");
        profile.setVersion("1");
        profile.setGlobalLanguage(LanguageType.Chinese);

        IWebClient client = new DefaultProductAIClient(profile);

        /**
         * Classify
         */
        IExample classify_by_file_example = new ClassifyByFileExample();
        classify_by_file_example.run(client);

        IExample classify_by_url_example = new ClassifyByUrlExample();
        classify_by_url_example.run(client);

        /**
         * Image search
         */
        IExample search_by_file_example = new ImageSearchExample();
        search_by_file_example.run(client);

        IExample search_by_url_example = new ImageSearchByUrlExample();
        search_by_url_example.run(client);

        /**
         * Detect
         */
        IExample detect_by_file_example = new DetectExample();
        detect_by_file_example.run(client);

        IExample detect_by_url_example = new DetectByUrlExample();
        detect_by_url_example.run(client);

        /**
         * Filter
         */
        IExample filter_by_file_example = new SmartFilterExample();
        filter_by_file_example.run(client);

        IExample filter_by_image_url_example = new SmartFilterByUrlExample();
        filter_by_image_url_example.run(client);

        /**
         * Data set
         */
        IExample dataSet_example = new DataSetManagementExample();
        dataSet_example.run(client);

        /**
         * Add single image to data set
         */
        IExample single_add_image_to_dataSet_example = new DataSetSingleModifyExample();
        single_add_image_to_dataSet_example.run(client);

        /**
         * Delete images from data set
         */
        IExample delete_images_from_dataSet_example = new DataSetDeleteExample();
        delete_images_from_dataSet_example.run(client);

        System.out.println("Done");
    }
}
