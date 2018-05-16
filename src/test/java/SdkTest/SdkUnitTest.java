package SdkTest;

import cn.productai.api.core.DefaultProductAIClient;
import cn.productai.api.core.DefaultProfile;
import cn.productai.api.core.IProfile;
import cn.productai.api.core.IWebClient;
import cn.productai.api.core.exceptions.ClientException;
import cn.productai.api.pai.entity.classify.ClassifyByImageUrlRequest;
import cn.productai.api.pai.entity.classify.ClassifyResponse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thinkpad on 2017/7/5.
 */
public class SdkUnitTest {

    IWebClient client = null;

    @Before
    public void setup() throws Exception {

        IProfile profile = new DefaultProfile();
        profile.setAccessKeyId("123");
        profile.setSecretKey("123");

        client = new DefaultProductAIClient(profile);
    }

    /**
     * test profile default values
     */
    @Test
    public void testProfile() {
        assertTrue(client.getProfile().getVersion().equals("1"));
        assertTrue(client.getProfile().getGlobalLanguage() == null);
    }

    /**
     * test classify request
     */
    @Test
    public void testClassify() {
        ClassifyByImageUrlRequest request = new ClassifyByImageUrlRequest("classify", "_0000039");

        try {
            ClassifyResponse response = client.getResponse(request);
        } catch (ClientException e) {
            assertEquals("1002", e.getErrorCode());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testRequestUrl() {
        ClassifyByImageUrlRequest request = new ClassifyByImageUrlRequest("classify", "_0000039");
        assertTrue(request.getApiUrl().equals("https://api.productai.cn/classify/_0000039"));
    }
}
