package cn.productai.api.core.helper;

import java.util.ArrayList;

public class StringHelper {

    public static String join(String separator, ArrayList<String> list) {
        if (list == null || list.size() == 0)
            return "";
        else {
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(String.format("%s%s", s, separator));
            }
            String str = sb.toString();
            return str.substring(0, str.length() - separator.length());
        }
    }

}
