package cn.productai.api.examples;

import cn.productai.api.core.IWebClient;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.pai.entity.filter.IntelligentFilterByImageFileRequest;
import cn.productai.api.pai.entity.filter.IntelligentFilterResponse;
import cn.productai.api.pai.response.IntelligentFilterResult;

import java.io.File;

/**
 * Created by Zhong Wang on 2017/7/5.
 *
 */
public class SmartFilterExample implements IExample {

    @Override
    public void run(IWebClient client) {

        System.out.println("==>  Demo - 智能滤镜  <==");
        System.out.println("See https://api-doc.productai.cn/doc/pai.html#智能滤镜 for details.\r\n");

        IntelligentFilterByImageFileRequest request = new IntelligentFilterByImageFileRequest();
        request.setImageFile(new File(this.getClass().getResource("/").getPath() + "images/f12.jpg"));
        request.setLanguage(LanguageType.Chinese);

        // you can pass the extra paras to the request
        request.getOptions().put("para1", "1");
        request.getOptions().put("para2", "中文");
        request.getOptions().put("para3", "value3");

        try {
            IntelligentFilterResponse response = client.getResponse(request);

            System.out.println("==============================Result==============================");

            for (IntelligentFilterResult r : response.getResults()) {
                // access the response directly
                System.out.println(String.format("%s - %s", r.getImageUrl(), r.getScore()));
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
