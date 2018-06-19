package cn.productai.api.core.helper;

import cn.productai.api.core.attribute.EnumDescriptionAttribute;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Zhong Wang on 2017/6/30.
 */

public final class EnumHelper {
    public static HashMap<Integer, String> toHashMap(Class _enumType) {

        if (!_enumType.isEnum()) {
            throw new IllegalArgumentException("Only support enum type!");
        }
        HashMap<Integer, String> dic = new HashMap<Integer, String>();

        Field[] ps = _enumType.getFields();
        for (Field p : ps) {
            if (!p.getType().equals(_enumType)) {
                continue;
            }
            Enum _enum = Enum.valueOf(_enumType, p.getName());
            EnumDescriptionAttribute att = p.getAnnotation(EnumDescriptionAttribute.class);
            if (att != null) {
                dic.put(_enum.ordinal(), att.Text());
            } else {
                dic.put(_enum.ordinal(), p.getName());
            }
        }
        return dic;
    }
}
