package cn.productai.apiv2;

import cn.productai.api.core.enums.HttpMethod;
import cn.productai.apiv2.exceptions.PAIException;
import cn.productai.apiv2.lib.Http;

import java.io.File;

public class TestOne {
    public static void main(String[] args) {
        test();
    }


    static void test() {
        try {
            HttpMethod httpMethod = HttpMethod.POST;
            String url = "http://localhost:8080/service/tagging/manual";
            String json = "{\"tags\":\"abcdefg\"}";
            String fileFieldName = "search";
            File file = new File("/home/liujian/Downloads/test/a.jpeg");

            String result = Http.request(httpMethod, url, null, json, fileFieldName, file);
            System.out.println(result);
        } catch (PAIException e) {
            System.out.println("Fail");
        }
    }
}
