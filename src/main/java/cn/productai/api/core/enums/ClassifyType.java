package cn.productai.api.core.enums;

import cn.productai.api.core.attribute.ServiceDescriptionAttribute;

/**
 * Created by Zhong Wang on 2017/6/30.
 *
 */

public enum ClassifyType {
    /*
    场景分析与标注
     */
    @ServiceDescriptionAttribute(Name = "场景分析与标注", ServiceType = "classify", ServiceId = "_0000039")
    Place,

    /*
    色情分析与标注
     */
    @ServiceDescriptionAttribute(Name = "色情分析与标注", ServiceType = "classify", ServiceId = "_0000024")
    Pornography,

    /*
    通用图片内容分析与标注
     */
    @ServiceDescriptionAttribute(Name = "通用图片内容分析与标注", ServiceType = "classify", ServiceId = "_0000056")
    General
}
