package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.attribute.ParaSignAttribute;
import cn.productai.api.core.base.ManagementAPIBaseRequest;

public class CreateServiceRequest extends ManagementAPIBaseRequest<CreateProductSetResponse> {

    @ParaSignAttribute(Name = "name")
    public String name;

    @ParaSignAttribute(Name = "scenario")
    public String scenario;

    public CreateServiceRequest() {
        super();
    }

    /**
     * @param name     the name of the dataset
     * @param scenario the scenario
     */
    public CreateServiceRequest(String name, String scenario) {
        this();
        this.setName(name);
        this.setScenario(scenario);
    }

    @Override
    public Class<CreateProductSetResponse> getResponseClass() {
        return CreateProductSetResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/_0000178/services", this.getHost());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
}
