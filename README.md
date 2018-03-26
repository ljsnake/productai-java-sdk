# ProductAI® SDK for Java

[![Build Status](https://travis-ci.org/MalongTech/productai-java-sdk.svg?branch=master)](https://travis-ci.org/MalongTech/productai-java-sdk)
[![GitHub release](https://img.shields.io/github/release/MalongTech/productai-java-sdk.svg)](https://github.com/MalongTech/productai-java-sdk/releases)

## ProductAI:
<br>For more details about ProductAI, view
- [ProductAI Global offcial site](http://www.productai.com)
- [ProductAI China offcial site](http://www.productai.cn)
- [ProductAI Documentation](https://api-doc.productai.cn/doc/pai.html)

# Usage（用法）:

## JDK

```
jdk 1.8.0_131
```

## Example code
```java

package cn.productai.api.examples;

import cn.productai.api.core.*;
import cn.productai.api.core.enums.*;
import cn.productai.api.examples.batch_tasks.TasksExample;
import cn.productai.api.examples.classify.*;
import cn.productai.api.examples.color.*;;
import cn.productai.api.examples.dataset.*;
import cn.productai.api.examples.detect.*;
import cn.productai.api.examples.dressing.*;
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

```

# Examples （示例）
See project examples. (请参考工程中的examples目录，可以使用IDE打开这个目录，运行main.java)

```
"C:\Program Files (x86)\Java\jdk1.8.0_131\bin\java" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.1.4\lib\idea_rt.jar=56193:D:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.1.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\charsets.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\deploy.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-32.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\javaws.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\jce.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\jfr.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\jsse.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\management-agent.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\plugin.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\resources.jar;C:\Program Files (x86)\Java\jdk1.8.0_131\jre\lib\rt.jar;D:\Git\productai-java-sdk\examples\out\production\examples;D:\Git\productai-java-sdk\out\artifacts\productai_java_sdk_jar\productai-java-sdk.jar" cn.productai.api.examples.Main
==>  Demo - 场景分析与标注  <==
See https://api-doc.productai.cn/doc/pai.html#场景分析与标注 for details.

==============================Result==============================
人 - 1.0
近景 - 1.0
滑板 - 0.8486556789900183
家具 - 0.8458443616950199
长椅 - 0.8437045409245522
公园 - 0.8406858317637991
做动作 - 0.8400916723011806
电子产品 - 0.839755451368282
耳机 - 0.8382769451321577
壁架 - 0.8379477081882937
播放器 - 0.8375434315188126
路 - 0.8375027412302735
院子 - 0.8366529415928585
露台 - 0.8366356073604319
随身听 - 0.8362790730915804
楼梯 - 0.836235399852007
人行道 - 0.8352633931724351
道路 - 0.8343461052576037
校园 - 0.8338869043470721
约会 - 0.833687316601359
城市旅游 - 0.8334581722909524
学生 - 0.8329722116383586
步行 - 0.8326962996276108
门廊 - 0.83232436323478
全身像 - 0.8322245165305068
容器 - 0.8321993767876539
书包 - 0.8321735365280051
帽子 - 0.8318941068407346
坐 - 0.8318788454066843
横越 - 0.8318754597080943
并排 - 0.8315926072821874
伴侣 - 0.8315403635969828
墙 - 0.8315047882666013
使用手机 - 0.8310491051812318
城市 - 0.8309303276898228
头发 - 0.8303539299624695
同学 - 0.8301474240734664
女性 - 0.8
平视角 - 0.8
女人 - 0.7
==============================Result==============================
==>  Demo - 场景分析与标注  <==
See https://api-doc.productai.cn/doc/pai.html#场景分析与标注 for details.

==============================Result==============================
远景 - 1.0
无人 - 0.9
高大 - 0.8703244813441267
雪 - 0.8689684261215542
覆盖 - 0.8662902067994493
文化 - 0.8657077932968285
自然 - 0.8656587396872085
山脉 - 0.865068208977097
景观 - 0.863737868610511
山 - 0.8627540152181911
陡峭 - 0.8626938791600232
人 - 0.8618056437855326
冰川 - 0.8617967966404989
山峰 - 0.8614103420589703
植物 - 0.8612866963107042
烘焙师 - 0.8598440360442686
山脊 - 0.8597859314250361
高处 - 0.8595588471522299
会议 - 0.8594949901021014
自然景观 - 0.8594875084537985
针叶林 - 0.8593195360594588
出口 - 0.8587760078556991
荒野 - 0.8587444657744707
雪山 - 0.8584800064690568
高原 - 0.8574969878068343
山谷 - 0.8566848121663391
尖峰 - 0.8566155730961645
山顶 - 0.856355051376396
冰 - 0.856140829176148
冷 - 0.8556875050825358
山坡 - 0.8554744026307055
宏伟 - 0.8547502747119544
缆车 - 0.8546510311360588
太阳 - 0.8537700537180676
地形 - 0.8535911294518608
喇嘛教 - 0.8532137751077663
原始主义 - 0.8530176748162435
岩面 - 0.852662734082293
峭壁 - 0.851512528254249
晴朗 - 0.8514084662510503
==============================Result==============================
==>  Demo - 通用图像搜索  <==
See https://api-doc.productai.cn/doc/pai.html#通用图像搜索 for details.

==============================Result==============================
http://static.esobing.com/images/pai/4.jpg - 0.46116530895233154
http://static.esobing.com/images/pai/5.jpg - 0.45674431324005127
http://static.esobing.com/images/pai/6.jpg - 0.44710057973861694
http://static.esobing.com/images/pai/2.jpg - 0.4296668767929077
http://static.esobing.com/images/pai/3.jpg - 0.42474472522735596
http://static.esobing.com/images/pai/1.jpg - 0.4243323802947998
http://static.esobing.com/images/pai/7.jpg - 0.4092801809310913
==============================Result==============================
==>  Demo - 通用图像搜索  <==
See https://api-doc.productai.cn/doc/pai.html#通用图像搜索 for details.

==============================Result==============================
http://static.esobing.com/images/pai/5.jpg - 0.639316976070404
http://static.esobing.com/images/pai/1.jpg - 0.5797975957393646
http://static.esobing.com/images/pai/2.jpg - 0.4499273896217346
http://static.esobing.com/images/pai/4.jpg - 0.41502732038497925
http://static.esobing.com/images/pai/6.jpg - 0.38998597860336304
http://static.esobing.com/images/pai/3.jpg - 0.3851330876350403
http://static.esobing.com/images/pai/7.jpg - 0.3792150020599365
==============================Result==============================
==>  Demo - 3C电器检测与定位  <==
See https://api-doc.productai.cn/doc/pai.html#3C电器检测与定位 for details.

==============================Result==============================
手机 - 0.9511141180992126
==============================Result==============================
==>  Demo - 宠物检测与定位  <==
See https://api-doc.productai.cn/doc/pai.html#宠物检测与定位 for details.

==============================Result==============================
狗 - 0.980624258518219
==============================Result==============================
==>  Demo - 智能滤镜  <==
See https://api-doc.productai.cn/doc/pai.html#智能滤镜 for details.

==============================Result==============================
http://productai-query.oss-cn-hangzhou.aliyuncs.com/style-transfer/result/comic5/9e773d38b7b2aedd2cf1a4b46929ef2a.jpg?x-oss-process=image/resize,w_850,limit_0 - 0.919928
==============================Result==============================
==>  Demo - 智能滤镜  <==
See https://api-doc.productai.cn/doc/pai.html#智能滤镜 for details.

==============================Result==============================
http://productai-query.oss-cn-hangzhou.aliyuncs.com/style-transfer/result/comic5/9e773d38b7b2aedd2cf1a4b46929ef2a.jpg?x-oss-process=image/resize,w_850,limit_0 - 0.919928
==============================Result==============================
==>  Demo - 向数据集增加多条数据  <==
See https://api-doc.productai.cn/doc/pai.html#向数据集增加多条数据 for details.

==============================Result==============================
LastModifiedTime - 2017-07-11T06:41:16Z
==============================Result==============================
==>  Demo - 向数据集增加单条数据  <==
See https://api-doc.productai.cn/doc/pai.html#向数据集增加单条数据 for details.

==============================Result==============================
LastModifiedTime - 2017-07-11T06:41:16Z
==============================Result==============================
==>  Demo - 从数据集删除多条数据  <==
See https://api-doc.productai.cn/doc/pai.html#从数据集删除多条数据 for details.

==============================Result==============================
LastModifiedTime - 2017-07-11T06:41:16Z
==============================Result==============================
Done

Process finished with exit code 0

```

# Release Notes （更新日志）

## 2018-03-26

- 添加了 ProductSet 相关接口 

## 2018-01-03

 - [Add batch (tasks) apis & example](https://github.com/MalongTech/productai-java-sdk/tree/master/examples/src/cn/productai/api/examples/batch_tasks) (增加批处理API及示例)
 - [Add color apis & examples](https://github.com/MalongTech/productai-java-sdk/tree/master/examples/src/cn/productai/api/examples/color) (增加色彩标注API及示例)
 - [Add dressing examples](https://github.com/MalongTech/productai-java-sdk/tree/master/examples/src/cn/productai/api/examples/dressing) (增加时尚分析示例)
 - Bug fix

## 2017-12-21

 - Add dataset management apis (增加数据集管理API)
 - Add search service management apis （增加搜索服务管理API）
