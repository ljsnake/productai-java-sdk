package cn.productai.api.core.enums;

import cn.productai.api.core.attribute.EnumDescriptionAttribute;

/**
 * Created by Zhong Wang on 2017/7/5.
 * ServiceType
 */
public enum ServiceType {

    @EnumDescriptionAttribute(Text = "filter")
    Filter,

    @EnumDescriptionAttribute(Text = "classify")
    Classify,

    @EnumDescriptionAttribute(Text = "detect")
    Detect,

    @EnumDescriptionAttribute(Text = "dressing")
    Dressing,

    @EnumDescriptionAttribute(Text = "color")
    Color
}
