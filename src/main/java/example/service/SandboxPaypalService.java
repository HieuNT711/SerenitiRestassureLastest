package example.service;

import example.DataCommonAppRestService;
import example.dto.request.orderRequest.OrderRequest;
import example.dto.response.getOrder.GetOrderResponse;
import example.dto.response.orderResponse.OrderResponse;
import example.until.ConvertObjectUntil;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;

import static io.specto.hoverfly.junit.dsl.HttpBodyConverter.APPLICATION_JSON;

public class SandboxPaypalService extends BaseSandboxPaypalService {


    public static final String ORDER_PATH = DataCommonAppRestService.BASE_URL_SERVICE_APP + "/v2/checkout/orders/";
    public static final String OAUTH_2_TOKEN = DataCommonAppRestService.BASE_URL_SERVICE_APP + "/v1/oauth2/token";
    public static final String GET_ORDER = DataCommonAppRestService.BASE_URL_SERVICE_APP + "/v2/checkout";

    public OrderResponse createOrderResponse(OrderRequest orderRequest) {
        RequestSpecification specification = getDefaultRequestBuilder(ORDER_PATH)
                .body(ConvertObjectUntil.convertDtoToJson(orderRequest))
                .headers("Prefer", "return=representation")
                .headers("PayPal-Request-Id", "34b7b223-db52-4aaa-914c-66a583a3edbe")
                .contentType(APPLICATION_JSON);
        Response response = this.dispatchServiceRequest(specification, Method.POST);
        return response.as(OrderResponse.class);

    }

    public GetOrderResponse getOrderResponse() {
        RequestSpecification specification = getDefaultRequestBuilder(ORDER_PATH + Serenity.sessionVariableCalled("ORDER_ID"));
        Response response = this.dispatchServiceRequest(specification, Method.GET);
        return response.as(GetOrderResponse.class);
    }


}
