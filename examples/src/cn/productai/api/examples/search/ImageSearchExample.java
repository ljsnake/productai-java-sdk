package cn.productai.api.examples.search;

import cn.productai.api.core.ISearchTag;
import cn.productai.api.core.ITag;
import cn.productai.api.core.IWebClient;
import cn.productai.api.core.entity.AndTag;
import cn.productai.api.core.entity.OrTag;
import cn.productai.api.core.entity.SearchTag;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.examples.IExample;
import cn.productai.api.pai.entity.search.ImageSearchByImageFileRequest;
import cn.productai.api.pai.entity.search.ImageSearchResponse;
import cn.productai.api.pai.response.SearchResult;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Zhong Wang on 2017/7/5.
 *
 */
public class ImageSearchExample implements IExample {

    @Override
    public void run(IWebClient client) {

        System.out.println("==>  Demo - 通用图像搜索  <==");
        System.out.println("See https://api-doc.productai.cn/doc/pai.html#通用图像搜索 for details.\r\n");

        // 复杂Tag查询示例
        ISearchTag andTag = new AndTag();
        andTag.Add("上衣");
        andTag.Add(new ArrayList<String>(){Boolean a = add("圆领"); Boolean b = add("无袖");});

        ISearchTag orTag = new OrTag();
        orTag.Add("蓝色");
        orTag.Add("休闲");

        andTag.Add(orTag);

        ITag searchTag = new SearchTag();
        searchTag.setTag(andTag);

        ImageSearchByImageFileRequest request = new ImageSearchByImageFileRequest("k7h9fail");
        request.setImageFile(new File(this.getClass().getResource("/").getPath() + "images/f10.jpg"));
        request.setLanguage(LanguageType.Chinese);
        request.setSearchTag(searchTag);
        request.setCount(50);

        // you can pass the extra paras to the request
        request.getOptions().put("para1", "1");
        request.getOptions().put("para2", "中文");
        request.getOptions().put("para3", "value3");

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
            System.out.println(String.format("%s occurred. ErrorMessage: %s", e.getClass().getName(), e.getMessage()));
            e.printStackTrace();
        }
    }
}
