package cn.productai.api.examples;

import cn.productai.api.core.*;
import cn.productai.api.core.enums.*;
import cn.productai.api.examples.batch_tasks.TasksExample;
import cn.productai.api.examples.classify.*;
import cn.productai.api.examples.color.ColorAnalysisByFileExample;
import cn.productai.api.examples.color.ColorAnalysisByUrlExample;
import cn.productai.api.examples.dataset.*;
import cn.productai.api.examples.detect.*;
import cn.productai.api.examples.dressing.DressingClassifyByFileExample;
import cn.productai.api.examples.dressing.DressingClassifyByUrlExample;
import cn.productai.api.examples.filter.*;
import cn.productai.api.examples.search.*;
import cn.productai.api.examples.service.*;

public class Main {

    public static void main(String[] args) {

        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("****");
        profile.setSecretKey("****");
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

        /**
         * DataSet management api example
         */
        IExample dataSet_management_api_example = new DataSetManagementApiExample();
        dataSet_management_api_example.run(client);

        /**
         * Search service management api example
         */
        ServiceApiExample service_management_api_example = new ServiceApiExample();
        service_management_api_example.setDataSetId("s5xwihok");
        service_management_api_example.run(client);

        /**
         * Batch api examples
         */
        TasksExample task_example = new TasksExample();
        task_example.run(client);

        /**
         * Color api example
         */
        ColorAnalysisByFileExample color_file_example = new ColorAnalysisByFileExample();
        color_file_example.run(client);

        ColorAnalysisByUrlExample color_url_example = new ColorAnalysisByUrlExample();
        color_url_example.run(client);

        /**
         * Dressing api example
         */
        DressingClassifyByFileExample dressing_file_example = new DressingClassifyByFileExample();
        dressing_file_example.run(client);

        DressingClassifyByUrlExample dressing_url_example = new DressingClassifyByUrlExample();
        dressing_url_example.run(client);

        /**
         * Filter
         */
        IExample filter_by_file_example = new SmartFilterExample();
        filter_by_file_example.run(client);

        IExample filter_by_image_url_example = new SmartFilterByUrlExample();
        filter_by_image_url_example.run(client);


        System.out.println("Done");
    }
}
