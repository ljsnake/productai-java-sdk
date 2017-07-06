package cn.productai.api.core.enums;

import cn.productai.api.core.attribute.ServiceDescriptionAttribute;

/**
 * Created by Zhong Wang on 2017/6/30.
 *
 */

public enum DetectType {
    /*
    3C电器检测与定位
     */
    @ServiceDescriptionAttribute(Name = "3C电器检测与定位", ServiceType = "detect", ServiceId = "_0000027")
    ThreeCAndElectronics,

    /*
    交通工具检测与定位
     */
    @ServiceDescriptionAttribute(Name = "交通工具检测与定位", ServiceType = "detect", ServiceId = "_0000033")
    Vehicle,

    /*
    宠物检测与定位
     */
    @ServiceDescriptionAttribute(Name = "宠物检测与定位", ServiceType = "detect", ServiceId = "_0000031")
    Pet,

    /*
    家具与家居用品检测与定位
     */
    @ServiceDescriptionAttribute(Name = "家具与家居用品检测与定位", ServiceType = "detect", ServiceId = "_0000029")
    Furniture,

    /*
    常见商品检测与定位
     */
    @ServiceDescriptionAttribute(Name = "常见商品检测与定位", ServiceType = "detect", ServiceId = "_0000030")
    Ordinary,

    /*
    服装检测与定位
     */
    @ServiceDescriptionAttribute(Name = "服装检测与定位", ServiceType = "detect", ServiceId = "_0000025")
    Cloth,

    /*
    食品检测与定位
     */
    @ServiceDescriptionAttribute(Name = "食品检测与定位", ServiceType = "detect", ServiceId = "_0000028")
    Food
}
