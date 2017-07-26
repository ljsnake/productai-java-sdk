# ProductAI® SDK for java

[![Build Status](https://travis-ci.org/wzh880801/productai-java-sdk.svg?branch=master)](https://travis-ci.org/wzh880801/productai-java-sdk)
[![GitHub release](https://img.shields.io/github/release/MalongTech/productai-java-sdk.svg)](https://github.com/MalongTech/productai-java-sdk/releases)

## ProductAI:
<br>For more details about ProductAI, view
- [ProductAI Global offcial site](http://www.productai.com)
- [ProductAI China offcial site](http://www.productai.cn)
- [ProductAI Documentation](https://api-doc.productai.cn/doc/pai.html)

# Usage（用法）:

## JDK

```
jdk 1.6.0_45
```

## Example code
```java

package cn.productai.api.examples;

import cn.productai.api.core.DefaultProductAIClient;
import cn.productai.api.core.DefaultProfile;
import cn.productai.api.core.IProfile;
import cn.productai.api.core.IWebClient;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.pai.entity.search.ImageSearchByImageUrlRequest;
import cn.productai.api.pai.entity.search.ImageSearchResponse;
import cn.productai.api.pai.response.SearchResult;

/**
 * Created by Zhong Wang on 2017/7/4.
 *
 */
public class UsageIntroduction {
    public void fullFlowExample() {

        /**
         * step 1 - setup your account profile
         * get your accessKeyId & secretKey at https://console.productai.cn/main#/21/service_category_id=1
         */

        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("<your access key id>");
        profile.setSecretKey("<your secret key>");
        profile.setVersion("1");
        profile.setGlobalLanguage(LanguageType.Chinese);

        /**
         * step 2 - initialize your ProductAI client
         *
         */

        IWebClient client = new DefaultProductAIClient(profile);

        /**
         * step 3 - build your request
         * take image search as example
         */

        ImageSearchByImageUrlRequest request = new ImageSearchByImageUrlRequest("<your service id>");
        request.setUrl("http://productai.cn/img/f10.jpg");

        // this value will be override because you had set the IProfile.GlobalLanguage = LanguageType.Chinese
        request.setLanguage(LanguageType.English);

        /**
         * step 4 - send out the request、receive the response、catch the exceptions
         */

        try {
            ImageSearchResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            for (SearchResult r : response.getResults()) {
                // access the response directly
                System.out.println(String.format("%s - %s", r.getUrl(), r.getScore()));
            }

            System.out.println("==============================Result==============================");
        } catch (cn.productai.api.core.exceptions.ServerException e) {
            System.out.println(String.format("ServerException occurred. ErrorCode: %s \r\n ErrorMessage: %s",
                    e.getErrorCode(),
                    e.getErrorMessage()));
            e.printStackTrace();

        } catch (ClientException e) {
            System.out.println(String.format("ClientException occurred. ErrorCode: %s \r\n ErrorMessage: %s \r\n RequestId: %s",
                    e.getErrorCode(),
                    e.getErrorMessage(),
                    e.getRequestId()));
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getTypeName(), e.getMessage()));
            e.printStackTrace();
        }

        /**
         *  Congrats! Now you can build your smart AI service using ProductAI.
          */
    }
}

```

# Examples （示例）
See project examples. (请参考工程中的examples目录)

Example results:
```
"C:\Program Files (x86)\Java\jdk1.6.0_45\bin\java" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.1.4\lib\idea_rt.jar=63905:D:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.1.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\charsets.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\deploy.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\ext\dnsns.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\ext\localedata.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\ext\sunjce_provider.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\ext\sunmscapi.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\ext\sunpkcs11.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\javaws.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\jce.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\jsse.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\management-agent.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\plugin.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\resources.jar;C:\Program Files (x86)\Java\jdk1.6.0_45\jre\lib\rt.jar;D:\Git\productai-java-sdk\examples\out\production\examples;C:\java\artifacts\productai_java_sdk_jar\productai-java-sdk.jar" cn.productai.api.examples.Main
==>  Demo - 场景分析与标注  <==
See https://api-doc.productai.cn/doc/pai.html#场景分析与标注 for details.

==============================Result==============================
人 - 1.0
近景 - 1.0
滑板 - 0.8486556921266302
家具 - 0.8458443741671878
长椅 - 0.8437045535421541
公园 - 0.8406858369331082
做动作 - 0.8400916844550352
电子产品 - 0.8397554656317946
耳机 - 0.838276961623767
壁架 - 0.8379477182711964
播放器 - 0.837543445954293
路 - 0.837502761906926
院子 - 0.8366529540108817
露台 - 0.8366356239662476
随身听 - 0.8362790845682658
楼梯 - 0.8362354189223588
人行道 - 0.8352634141014739
道路 - 0.8343461161575755
校园 - 0.8338869260027721
约会 - 0.8336873255832901
城市旅游 - 0.8334581898824319
学生 - 0.8329722340945331
步行 - 0.8326963210678979
门廊 - 0.8323243774780811
全身像 - 0.8322245387980179
容器 - 0.8321993959051452
书包 - 0.8321735538047955
帽子 - 0.8318941157471803
坐 - 0.8318788525682377
横越 - 0.8318754789163938
并排 - 0.831592612013235
伴侣 - 0.8315403647939116
墙 - 0.831504812719313
使用手机 - 0.8310491227746196
城市 - 0.8309303430879227
头发 - 0.8303539422342755
同学 - 0.830147444749635
女性 - 0.8
平视角 - 0.8
女人 - 0.7
==============================Result==============================
==>  Demo - 场景分析与标注  <==
See https://api-doc.productai.cn/doc/pai.html#场景分析与标注 for details.

==============================Result==============================
远景 - 1.0
无人 - 0.9
高大 - 0.8703244823178388
雪 - 0.8689684362425808
覆盖 - 0.8662902156771985
文化 - 0.8657077982295814
自然 - 0.8656587478372093
山脉 - 0.8650682160525572
景观 - 0.8637378768457716
山 - 0.8627540208869532
陡峭 - 0.8626938849104089
人 - 0.86180565725777
冰川 - 0.8617968049628218
山峰 - 0.8614103423066038
植物 - 0.8612867024172747
烘焙师 - 0.8598440496604483
山脊 - 0.8597859418744656
高处 - 0.8595588506783558
会议 - 0.8594949935362541
自然景观 - 0.8594875129065055
针叶林 - 0.8593195422314597
出口 - 0.8587760204811851
荒野 - 0.8587444704309969
雪山 - 0.8584800115148112
高原 - 0.8574969858482316
山谷 - 0.8566848133710758
尖峰 - 0.8566155825888446
山顶 - 0.8563550618763602
冰 - 0.8561408467819036
冷 - 0.8556875234184348
山坡 - 0.8554744004027746
宏伟 - 0.8547502765605207
缆车 - 0.8546510429636482
太阳 - 0.8537700516421486
地形 - 0.8535911305158104
喇嘛教 - 0.8532137726512261
原始主义 - 0.8530176804108046
岩面 - 0.8526627367758662
峭壁 - 0.8515125314773941
晴朗 - 0.8514084671450666
==============================Result==============================
==>  Demo - 通用图像搜索  <==
See https://api-doc.productai.cn/doc/pai.html#通用图像搜索 for details.

==============================Result==============================
http://www.softsew.com/images/Moved%20from%20Main/More_Clothes.jpg - 0.5171087086200714
http://test.waltercrow.co.nz/wp/wp-content/uploads/2010/06/muji-clothes.jpg - 0.4877193570137024
http://couponcodezone.com/wp-content/uploads/2012/08/target-baby-clothes-coupons-21.png - 0.47798019647598267
http://static.esobing.com/images/pai/4.jpg - 0.46116530895233154
http://static.esobing.com/images/pai/5.jpg - 0.45674431324005127
http://static.esobing.com/images/pai/6.jpg - 0.44710057973861694
http://static.esobing.com/images/pai/2.jpg - 0.4296668767929077
http://static.esobing.com/images/pai/3.jpg - 0.42474472522735596
http://static.esobing.com/images/pai/1.jpg - 0.4243323802947998
http://static.esobing.com/images/pai/7.jpg - 0.4092801809310913
==============================Result==============================
==>  Demo - 3C电器检测与定位  <==
See https://api-doc.productai.cn/doc/pai.html#3C电器检测与定位 for details.

==============================Result==============================
手机 - 0.9511141180992126
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
Done

Process finished with exit code 0

```
