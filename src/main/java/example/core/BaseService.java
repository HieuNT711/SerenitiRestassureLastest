package example.core;

import example.config.EnvironmentConstant;
import example.config.ScenarioContext;
import example.dto.request.tokenRequest.Oauth2TokenRequest;
import example.dto.response.tokeResponse.Oauth2TokenResponse;
import example.exception.TestContextException;
import example.service.SandboxPaypalService;
import example.until.LogUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class BaseService {
    private Map<String, String> headers = new HashMap<>();
    protected final ScenarioContext scenarioContext;

    public BaseService() {
        super();
        this.scenarioContext = ScenarioContext.getInstance();
    }


    protected RequestSpecification getDefaultRequestBuilder(String url) {
        return RestAssured.given()
                .relaxedHTTPSValidation()
                .baseUri(url)
                .headers(headers)
                .when();

    }

    public Response dispatchServiceRequest(RequestSpecification request, Method method) {
        LogUtils.logRequest(request);
        switch (method) {
            case POST:
                return logAndReturnResponse(request.post());
            case PUT:
                return logAndReturnResponse(request.put());
            case GET:
                return logAndReturnResponse(request.get());
            case DELETE:
                return logAndReturnResponse(request.delete());
            case HEAD:
                return request.head().thenReturn();
            case OPTIONS:
                ;
                return request.options().thenReturn();
            case PATCH:
                return request.patch().thenReturn();
            default:
                throw new TestContextException(
                        String.format("Not Support Request Method %s", method.name()));
        }
    }

    private Response logAndReturnResponse(Response response) {
        LogUtils.logResponse(response);
        return response;
    }

    // Chuyển đổi DTO class thành dữ liệu form-urlencoded
    public static String getDefaultRequestParamsBody(Object dto) throws Exception {
        // Khởi tạo map để lưu trữ các cặp key-value
        Map<String, String> formDataMap = new HashMap<>();

        // Sử dụng reflection để truy cập các trường của DTO class
        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            if (value != null) {
                // Mã hóa giá trị của trường và thêm vào map
                formDataMap.put(field.getName(), URLEncoder.encode(value.toString(), "UTF-8"));
            }
        }

        // Xây dựng chuỗi dữ liệu form-urlencoded từ map
        StringBuilder formDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : formDataMap.entrySet()) {
            if (formDataBuilder.length() > 0) {
                formDataBuilder.append("&");
            }
            formDataBuilder.append(entry.getKey()).append("=").append(entry.getValue());
        }

        return formDataBuilder.toString();
    }


    // Chuyển đổi DTO class thành Map chứa các cặp key-value
    public static Map<String, String> convertToMap(Object dto) throws IllegalAccessException {
        Map<String, String> formDataMap = new HashMap<>();
        Class<?> clazz = dto.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(dto);
            if (value != null) {
                formDataMap.put(field.getName(), value.toString());
            }
        }
        return formDataMap;
    }

    // Chuyển đổi Map thành chuỗi dữ liệu form-urlencoded
    public static String convertToUrlEncoded(Map<String, String> formDataMap) throws Exception {
        StringBuilder formDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : formDataMap.entrySet()) {
            if (formDataBuilder.length() > 0) {
                formDataBuilder.append("&");
            }
            formDataBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            formDataBuilder.append("=");
            formDataBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return formDataBuilder.toString();
    }

    // Chuyển đổi DTO class thành chuỗi dữ liệu form-urlencoded
    public static String getDefaultRequestParamsFormParams(Object dto) throws Exception {
        Map<String, String> formDataMap = convertToMap(dto);
        return convertToUrlEncoded(formDataMap);
    }

    @SneakyThrows
    public Oauth2TokenResponse oauth2TokenResponse(Oauth2TokenRequest oauth2TokenRequest) {
        RequestSpecification specification = getDefaultRequestBuilder(SandboxPaypalService.OAUTH_2_TOKEN)
                .headers("Authorization", String.format("basic %s", Base64.getEncoder().encodeToString((EnvironmentConstant.CLIENT_ID + ":" + EnvironmentConstant.CLIENT_SECRET).getBytes())))
                .contentType(ContentType.URLENC)
                .body(getDefaultRequestParamsBody(oauth2TokenRequest));
        Response response = this.dispatchServiceRequest(specification, Method.POST);
        return response.as(Oauth2TokenResponse.class);

    }


}
