package cn.productai.api.pai.entity.productset;

import cn.productai.api.core.attribute.ParaSignAttribute;
import cn.productai.api.core.base.ManagementAPIBaseRequest;
import cn.productai.api.core.enums.ServiceTypeId;

public class CreateServiceRequest extends ManagementAPIBaseRequest<CreateServiceResponse> {

    @ParaSignAttribute(Name = "name")
    public String name;

    @ParaSignAttribute(Name = "scenario")
    public String scenario;

    private String productSetId;

    public CreateServiceRequest() {
        super();
    }

    /**
     * @param name     the name of the dataset
     * @param scenario the scenario
     */
    public CreateServiceRequest(String productSetId, String name, String scenario) {
        this();
        this.setProductSetId(productSetId);
        this.setName(name);
        this.setScenario(scenario);
    }

    @Override
    public Class<CreateServiceResponse> getResponseClass() {
        return CreateServiceResponse.class;
    }

    @Override
    public String getApiUrl() {
        return String.format("https://%s/product_sets/%s/services", this.getHost(), ServiceTypeId.PRODUCT_SET);
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

    public String getProductSetId() {
        return productSetId;
    }

    public void setProductSetId(String productSetId) {
        this.productSetId = productSetId;
    }
}
