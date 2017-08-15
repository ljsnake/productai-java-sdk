# ProductAI® SDK for java

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
