package cn.productai.apiv2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestContentType {
    public static void main(String[] args) {
        testOne("/home/liujian/Downloads/test/a.txt");
        testOne("/home/liujian/Downloads/test/a.csv");
        testOne("/home/liujian/Downloads/test/a.jpeg");
    }

    static void testOne(String filePath) {
        Path path = Paths.get(filePath);
        String contentType = null;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(filePath + " content type is " + contentType);
    }
}
