package cn.productai.api.pai.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Thinkpad on 2017/7/4.
 *
 */
public class DressingClassifyResult {

    @JsonProperty("textures")
    private String[] Textures;

    @JsonProperty("colors")
    private DressColor[] Colors;

    @JsonProperty("iteam")
    private String Iteam;

    public String[] getTextures() {
        return Textures;
    }

    public void setTextures(String[] textures) {
        Textures = textures;
    }

    public DressColor[] getColors() {
        return Colors;
    }

    public void setColors(DressColor[] colors) {
        Colors = colors;
    }

    public String getIteam() {
        return Iteam;
    }

    public void setIteam(String iteam) {
        Iteam = iteam;
    }
}
