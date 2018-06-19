package cn.productai.util;

import java.util.Arrays;
import java.util.List;

public class StrUtil {

    public static String join(String delimiter, List<String> list) {
        if (list == null) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(list.get(i));
        }

        return sb.toString();
    }

    public static String join(String delimiter, String[] list) {
        return join(delimiter, Arrays.asList(list));
    }
}