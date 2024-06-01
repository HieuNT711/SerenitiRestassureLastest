package example.service;

import example.config.GlobalVariables;
import example.core.BaseService;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;

import java.util.HashMap;
import java.util.Map;

public class BaseSandboxPaypalService extends BaseService {

    @Override
    protected RequestSpecification getDefaultRequestBuilder(String apiPath) {
        RequestSpecification spec = super.getDefaultRequestBuilder(apiPath);
        spec.headers(generateHeaderCommon());
        return spec;
    }

    public Map<String, Object> generateHeaderCommon() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Authorization", scenarioContext.getContext(GlobalVariables.TOKEN_TYPE) + " " + scenarioContext.getContext(GlobalVariables.TOKEN));
        return headers;
    }

}
