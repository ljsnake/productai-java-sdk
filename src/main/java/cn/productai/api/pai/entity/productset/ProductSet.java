package cn.productai.api.pai.entity.productset;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductSet {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
