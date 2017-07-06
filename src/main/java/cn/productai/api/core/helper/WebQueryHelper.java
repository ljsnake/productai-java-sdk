package cn.productai.api.core.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Thinkpad on 2017/7/3.
 */
public class WebQueryHelper {
    public static String urlEncode(String para) throws UnsupportedEncodingException {
        return URLEncoder.encode(para,"UTF-8");
    }

    public static String urlDecode(String encodedUrl) throws UnsupportedEncodingException{
        return URLDecoder.decode(encodedUrl, "UTF-8");
    }
}
