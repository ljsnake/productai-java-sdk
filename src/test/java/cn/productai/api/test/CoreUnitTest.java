package java.cn.productai.api.test;

import cn.productai.api.core.ISearchTag;
import cn.productai.api.core.ITag;
import cn.productai.api.core.entity.AIService;
import cn.productai.api.core.entity.AndTag;
import cn.productai.api.core.entity.OrTag;
import cn.productai.api.core.entity.SearchTag;
import cn.productai.api.core.enums.ClassifyType;
import cn.productai.api.core.enums.LanguageType;
import cn.productai.api.core.helper.EnumHelper;
import cn.productai.api.core.helper.SignatureHelper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class CoreUnitTest
    extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CoreUnitTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CoreUnitTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testEnumHelper() {
        HashMap<Integer, AIService> dicts = EnumHelper.toServiceHashMap(ClassifyType.class);
        assertTrue(3 == dicts.size());
        assertTrue("_0000024".equals(dicts.get(ClassifyType.Pornography.ordinal()).getServiceId()));

        HashMap<Integer, String> langDicts = EnumHelper.toHashMap(LanguageType.class);
        assertTrue("zh-Hans-CN".equals(langDicts.get(LanguageType.Chinese.ordinal())));
    }

    /**
     * Signature algo test
     */
    public void testSignature(){
        String secretKey = "1234567";
        HashMap<String, String> dicts = new HashMap<>();
        dicts.put("loc","0-0-1-1");
        dicts.put("url","http://productai.cn");

        try {
            assertTrue("yXj0ZztzO02Ip0jBWel69YnsAjA=".equals(SignatureHelper.signature(secretKey, dicts)));
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    /**
     * tag test
     */
    public void testTag(){
        ISearchTag andTag = new AndTag();
        andTag.Add("上衣");
        andTag.Add("蓝色");

        ITag searchTag = new SearchTag();
        searchTag.setTag(andTag);

        assertEquals("{\"and\":[\"上衣\",\"蓝色\"]}", searchTag.toString());
    }

    /**
     * tag test
     */
    public void testTag2()
    {
        ISearchTag andTag = new AndTag();
        andTag.Add("上衣");

        ISearchTag orTag = new OrTag();
        orTag.Add("蓝色");
        orTag.Add("休闲");

        andTag.Add(orTag);
        ITag searchTag = new SearchTag();
        searchTag.setTag(andTag);

        assertEquals("{\"and\":[\"上衣\",{\"or\":[\"蓝色\",\"休闲\"]}]}", searchTag.toString());
    }
}
