package cn.productai.api.examples;

import cn.productai.api.core.IWebClient;
import cn.productai.api.core.enums.DetectType;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.enums.ServiceType;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.pai.entity.detect.DetectByImageUrlRequest;
import cn.productai.api.pai.entity.detect.DetectResponse;
import cn.productai.api.pai.response.DetectResult;

import java.io.File;

/**
 * Created by Zhong Wang on 2017/8/15.
 * 3C电器检测与定位
 * https://api-doc.productai.cn/doc/pai.html#宠物检测与定位
 */
public class DetectByUrlExample implements IExample {

    @Override
    public void run(IWebClient client) {

        System.out.println("==>  Demo - 宠物检测与定位  <==");
        System.out.println("See https://api-doc.productai.cn/doc/pai.html#宠物检测与定位 for details.\r\n");

        // DetectByImageUrlRequest request = new DetectByImageUrlRequest(DetectType.Pet);
        DetectByImageUrlRequest request = new DetectByImageUrlRequest(ServiceType.Detect,
                "_0000031",
                "http://static.esobing.com/images/dog.jpg",
                "0-0-1-1");
        request.setLanguage(LanguageType.Chinese);

        // you can pass the extra paras to the request
        request.getOptions().put("para1", "1");
        request.getOptions().put("para2", "中文");
        request.getOptions().put("para3", "value3");

        try {
            DetectResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            for (DetectResult r : response.getDetectedBoxes()) {
                // access the response directly
                System.out.println(String.format("%s - %s", r.getType(), r.getScore()));
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
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }
}
